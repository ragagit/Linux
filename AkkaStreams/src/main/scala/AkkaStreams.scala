import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.RunnableGraph
import akka.stream.scaladsl.{Keep, Sink, Source}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}

object AkkaStreams extends App {
  implicit val system = ActorSystem("AkkaStreams")

  //The Materializer is a factory for stream execution engines, it is the thing that makes streams run
  implicit val materializer = ActorMaterializer()


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
