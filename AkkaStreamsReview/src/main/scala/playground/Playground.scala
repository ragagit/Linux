package playground

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, ClosedShape, FanOutShape, FanOutShape2}
import akka.stream.scaladsl.{Broadcast, Flow, GraphDSL, RunnableGraph, Sink, Source}

import scala.collection.immutable.List

object Playground extends App{
  implicit val actorSystem = ActorSystem("Playground")
  implicit val materializer = ActorMaterializer()

  case class Transaction(name: String, account: String, amount: Long, id: String)

  val accounts = List(
    Transaction("Amanda Pitt", "122345", 50000, "122211"),
    Transaction("John Smith", "333322", 10000, "777885"),
    Transaction("Mary Poppings", "222111", 70000, "888997"),
    Transaction("Edward Thomson", "2211221", 50000, "8899889")
  )

  val tranSource = Source(accounts)
  val tranFlow = Flow[Transaction].filter(transaction => transaction.amount > 10000)
  val bankProcessor = Sink.foreach[Transaction](println)
  val suspTransactionService = Sink.foreach[String]( idTran => println(s"Suspiscious transaction $idTran"))


  val graphSusp = GraphDSL.create(){ implicit builder  =>
      import GraphDSL.Implicits._

      val broadcast = builder.add(Broadcast[Transaction](2))
      val suspTransFilter = builder.add(Flow[Transaction].filter(transaction => transaction.amount > 10000))
      val idExtractor = builder.add(Flow[Transaction].map[String](transaction => transaction.id))

      broadcast.out(0) ~> suspTransFilter ~> idExtractor


      new FanOutShape2(broadcast.in, broadcast.out(1), idExtractor.out)
    }


  val graph = RunnableGraph.fromGraph(
    GraphDSL.create() { implicit builder =>
      import GraphDSL.Implicits._
      val suspTxShape = builder.add(graphSusp)

      tranSource ~> suspTxShape.in
      suspTxShape.out0 ~> bankProcessor
      suspTxShape.out1 ~> suspTransactionService

      ClosedShape


    })
  graph.run()
      //Source.single("Hello, Streams!").to(Sink.foreach(println)).run()

}
