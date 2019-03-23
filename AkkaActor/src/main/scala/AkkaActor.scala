import MyActor.{Goodbye, Greeting}
import MyFutureActor.DoOperation
import scala.util.{Failure, Success}
import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.{Await, Future}
import akka.pattern.ask
import akka.util.Timeout
import akka.pattern.{ ask, pipe }

import scala.concurrent.duration._
import scala.language.postfixOps

object MyActor {
  case class Greeting(from: String)
  case object Goodbye
}

class MyActor extends Actor with ActorLogging {
  import MyActor._

  def receive = {
    case Greeting(greeter) ⇒ log.info(s"I was greeted by $greeter.")
    case Goodbye           ⇒ log.info("Someone said goodbye to me.")
  }

}

object MyFutureActor {
  case class DoOperation(addr: String)
}


class MyFutureActor extends Actor with ActorLogging {

  def doOperation(addr: String) = {
    val str = "Operation done on " + addr
    log.info("Doing operation")
    str
  }

  override def receive: Receive = {
    case DoOperation(addr) =>
      log.info("DoOperation message received with addr: " + addr)
      val resp = doOperation(addr)
      log.info("doOperation returned: " + resp)
      sender() ! resp
    case _ =>
      log.info("Unknow message")
  }
}

object AkkaActor {

  def callFirstActor(system: ActorSystem) = {
        val myActor = system.actorOf(Props[MyActor], "myactor")

        myActor ! Greeting("Main")
        myActor ! Goodbye
  }


  def doFuture(system: ActorSystem) = {

    implicit val timeout = Timeout(5 seconds)

    val myFutureActor = system.actorOf(Props[MyFutureActor], "myfutureactor")

    //    val f: Future[String] = Future {
    //      //myFutureActor ? DoOperation("myaddress")
    //      "Hello"
    //    }
    //
    //    f onComplete {
    //      case Success(str) => for (post <- str) println(post)
    //      case Failure(t) => println("An error has occurred: " + t.getMessage)
    //    }
    val future = myFutureActor ? DoOperation("myaddress") // enabled by the “ask” import

    val result = Await.result(future, timeout.duration).asInstanceOf[String]

    future onComplete {
      case Success(str) =>
        println("Success:" + str)
      case Failure(f) =>
        println("Failure:" + f)
    }

    println(result)

  }

  def retaFuture( n: Int )  = Future {

    val l = List( 1 ,2 , 3, 4)
    val n = l.fold(0)((i1,i2) => i1+i2+1)
    println("n: " +  n)
    val donuts = Seq("Vanilla", "Strawberry", "Chocolate")

    val res = donuts.fold("")((s1,s2) => s1 + s2 + "Donuts")
    println(res)
    //val concatDonuts = (s1: String, s2: String) => s1 + s2 + "Donuts"

//    for{
//
//      i <- n
//
//    }yield{
//
//      i
//
//    }


  }

  def main(args: Array[String]): Unit = {

    val system = ActorSystem("mySystem")

    //callFirstActor(system)

    //doFuture(system)

    val fut = retaFuture(7)
    //println("fut: " + fut)

//    fut foreach {
//      msg => println(msg)
//    }



  }

}
