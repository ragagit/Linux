package ScalaAkkaRecap

//import akka.actor.Status.{Failure, Success}
import scala.util.{Failure, Success}
import akka.actor.SupervisorStrategy.{Restart, Stop}
import akka.actor.{Actor, ActorLogging, ActorSystem, OneForOneStrategy, PoisonPill, Props, Stash, SupervisorStrategy}
import akka.util.Timeout

object AkkaRecap extends App {

  class SimpleActor extends Actor with ActorLogging with Stash {
    override def receive: Receive = {
      case "createChild" =>
        val childActor = context.actorOf(Props[SimpleActor], "myChild")
        childActor ! "hello"
      case "stashThis" =>
        stash()
      case "change handler NOW" =>
        unstashAll()
        context.become(anotherHandler)

      case "change" => context.become(anotherHandler)
      case message => {
        println(s"I received: $message")
        sender() ! "Hello there"
      }
    }

    def anotherHandler: Receive = {
      case message => {
        println(s"In another receive handler: $message")
        sender() ! "Hello there"
      }
    }

    override def preStart(): Unit = {
      log.info("I'm starting")
    }

    override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy() {
      case _: RuntimeException => Restart
      case _ => Stop
    }
  }

  // Principles when working with actors
  // actor encapsulation
  // You can create an actor by your own, you need an actor system
  val system = ActorSystem("AkkaRecap")
  // #1: you can only instantiate an actor through the actor system
  val actor = system.actorOf(Props[SimpleActor], "simpleActor")
  // The only way to communicate with an actor is through messages
  // #2: sending messages. ! tell. This sends an asynchronous message to the actor
  actor ! "hello"
  /*
    - messages are sent asynchronously
    - many actors (in the millions) can share a few dozen threads
    - each message is processed/handled ATOMICALLY
    - no need for locks
   */

  // changing actor behavior + stashing
  // stashing means that we stash the message till the actor becomes another message handle that
  // can process the message, then we unstash it. We need to add with Stash

  actor ! "stashThis"
  actor ! "change handler NOW"

  // actors can spawn other actors
  // Once that you create an ActorSystem Akka creates three parent actors for you, they are called guardians
  // guardians: /system, /user, / = root guardian

  // actors have a defined lifecycle: they can be started, stopped, suspended, resumed, restarted

  // stopping actors - We can use context.stop or send a PoisonPill message, this message are handled in a separate
  // mail box.
  //actor ! PoisonPill

  // logging. We can use logging by adding with ActorLogging
  // supervision is how parent actors are going to react to child actors failures
  // you can define the strategy as in supervisorStrategy

  // configure Akka infrastructure: dispatchers, routers, mailboxes

  // schedulers
  import scala.concurrent.duration._
  import system.dispatcher  // We need the execution context. This implements the EC interface
  import scala.language.postfixOps
  system.scheduler.scheduleOnce(2 seconds) {
    actor ! "delayed happy birthday!"
  }

  // Akka patterns including FSM + ask pattern
  import akka.pattern.ask
  implicit val timeout = Timeout(3 seconds)

  val future = actor ? "question"

  future.onComplete{
    case Success(value) => println("Future succeeded")
    case Failure(t) => println(t.getMessage)
  }

  // the pipe pattern
  import akka.pattern.pipe
  val anotherActor = system.actorOf(Props[SimpleActor], "anotherSimpleActor")
  future.mapTo[String].pipeTo(anotherActor)

  actor ! PoisonPill
  anotherActor ! PoisonPill

}

