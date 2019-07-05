import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.RunnableGraph
import akka.stream.scaladsl.{Keep, Sink, Source}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

object AkkaStreams extends App {
  implicit val system = ActorSystem("AkkaStreams")

  //The Materializer is a factory for stream execution engines, it is the thing that makes streams run
  //Materialization is the process of allocating all resources needed to run the computation described
  //by a Graph (in Akka Streams this will often involve starting up Actors)
  implicit val materializer = ActorMaterializer()

  // Flows are simply a description of the processing pipeline
  // Every stream processing stage can produce a materialized value, and it is the responsibility of the user to combine them to a new type.
  // In the above example we used toMat to indicate that we want to transform the materialized value of the source and sink,
  // and we used the convenience function Keep.right to say that we are only interested in the materialized value of the sink.
  // a stream can expose multiple materialized values, but it is quite common to be interested in only the value of the Source or the Sink in the stream.
  // For this reason there is a convenience method called runWith() available for Sink, Source or Flow requiring,

  // ExecutionContexts, responsible for executing computations in a new thread,
  // in a pooled thread or in the current thread
  //implicit val ec = ExecutionContext.global
  implicit val ec = system.dispatcher

  val source = Source( 1 to 100)
  val sink = Sink.fold[Int, Int](0)(_+_)

  val runnable: RunnableGraph[Future[Int]] = source.toMat(sink)(Keep.right)
  val sum: Future[Int] = runnable.run()

  def delay(n: Int) = Future{
    for( i <- 1 to n){
      println(s"Message $i")
      Thread.sleep(1000)
    }
  }

  sum.onComplete {
    case Success(value) =>
      println("Res:" + value)
    case Failure(exception) =>
      println(exception.getMessage)
  }

  val runnable1: Future[Int] = source.runWith(sink)

  val res = delay(5)
  res.onComplete {
    case Success(_) =>
      println("Success")
      system.terminate()
    case Failure(exception) =>
      println(exception.getMessage)
      system.terminate()
  }

  //system.terminate()
}

