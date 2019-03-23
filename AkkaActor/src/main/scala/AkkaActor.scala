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

  def main(args: Array[String]): Unit = {

    val system = ActorSystem("mySystem")

    //callFirstActor(system)

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

//    future onComplete {
//      case Success(str) =>
//        println("Success:" + str)
//      case Failure(f) =>
//        println("Failure:" + f)
//    }

    val result = Await.result(future, timeout.duration).asInstanceOf[String]

    println(result)

  }

}
