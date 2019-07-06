import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Keep, RunnableGraph, Sink, Source}
import akka.NotUsed

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

  // Create a source from an Iterable
  val srcList = Source(List(1, 2, 3))

  // Create a source from a Future
  val srcFut = Source.fromFuture(Future.successful("Hello Streams!"))

  // Create a source from a single element
  val srcSingle = Source.single("only one element")

  // an empty source
  val srcEmpty = Source.empty

  // Sink that folds over the stream and returns a Future
  // of the final result as its materialized value
  val sinkFold = Sink.fold[Int, Int](0)(_ + _)

  // Sink that returns a Future as its materialized value,
  // containing the first element of the stream
  val sinkHead = Sink.head

  // A Sink that consumes a stream without doing anything with the elements
  val sinkIgn = Sink.ignore

  // A Sink that executes a side-effecting call for every element of the stream
  val sinkForStr = Sink.foreach[String](println(_))
  val sinkForInt = Sink.foreach[Int](println(_))
  val flow = Source(1 to 6).via(Flow[Int].map(_ * 2)).to(Sink.foreach(println(_)))
  val res5 = flow.run()
  println("res5: " + res5)

  val resList = srcList.runWith(sinkFold)

  for{
    res0 <- srcList.runWith(sinkFold)
    res1 <- srcFut.runWith(sinkForStr)
    res2 <- srcList.runWith(sinkIgn)
    res3 <- srcList.runWith(sinkForInt)

  }yield{
    println("res0: " + res0)
    println("res1: " + res1)
    println("res2: " + res2)
    println("res3: " + res2)
  }

  // == Ways to wiring ==
  // Explicitly creating and wiring up a Source, Sink and Flow
  Source(1 to 6).via(Flow[Int].map(_ * 2)).to(Sink.foreach(println(_)))

  // Starting from a Source
  val source = Source(1 to 6).map(_ * 2)
  source.to(Sink.foreach(println(_)))

  // Starting from a Sink
  val sink: Sink[Int, NotUsed] = Flow[Int].map(_ * 2).to(Sink.foreach(println(_)))
  Source(1 to 6).to(sink)

  // Broadcast to a sink inline
  val otherSink: Sink[Int, NotUsed] =
    Flow[Int].alsoTo(Sink.foreach(println(_))).to(Sink.ignore)
  Source(1 to 6).to(otherSink)

  //system.terminate()
}
