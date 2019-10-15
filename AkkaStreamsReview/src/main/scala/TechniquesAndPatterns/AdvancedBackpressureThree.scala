package TechniquesAndPatterns

import java.util.Date

import akka.actor.ActorSystem
import akka.stream.scaladsl.{Flow, Sink, Source}
import akka.stream.{ActorMaterializer, OverflowStrategy}
import scala.language.postfixOps

object AdvancedBackpressureThree extends App {

  implicit val system = ActorSystem("AdvancedBackpressure")
  implicit val materializer = ActorMaterializer()

  // control backpressure
  val controlledFlow = Flow[Int].map(_ * 2).buffer(10, OverflowStrategy.dropHead)

  case class PagerEvent(description: String, date: Date, nInstances: Int = 1)
  case class Notification(email: String, pagerEvent: PagerEvent)

  val events = List(
    PagerEvent("Service discovery failed", new Date),
    PagerEvent("Illegal elements in the data pipeline", new Date),
    PagerEvent("Number of HTTP 500 spiked", new Date),
    PagerEvent("A service stopped responding", new Date),
    PagerEvent("Low memory", new Date)
  )
  val eventSource = Source(events)

  val oncallEngineer = "daniel@rockthejvm.com" // a fast service for fetching oncall emails

  def sendEmail(notification: Notification) =
    println(s"Dear ${notification.email}, you have an event: ${notification.pagerEvent}") // actually send an email

  val notificationSink = Flow[PagerEvent].map(event => Notification(oncallEngineer, event))
    .to(Sink.foreach[Notification](sendEmail))

  // standard
  //eventSource.to(notificationSink).run()

  /*
    un-backpressurable source
   */

  def sendEmailSlow(notification: Notification) = {
    Thread.sleep(1000)
    println(s"Dear ${notification.email}, you have an event: ${notification.pagerEvent}") // actually send an email
  }

  val aggregateNotificationFlow = Flow[PagerEvent]
    .conflate((event1, event2) =>{
      val nInstances = event1.nInstances + event2.nInstances
      PagerEvent(s"You have $nInstances events that require your attention", new Date, nInstances)
    })
    .map(resultingEvent => Notification(oncallEngineer, resultingEvent))

  // alternative to backpressure
  //  eventSource.via(aggregateNotificationFlow).async.to(Sink.foreach[Notification](sendEmailSlow)).run()


  /*
    Slow producers: extrapolate/expand
   */
  import scala.concurrent.duration._
  val slowCounter = Source(Stream.from(1)).throttle(1, 1 second)
  val hungrySink = Sink.foreach[Int](println)

  // extrapolate only when there is demand
  //val extrapolator = Flow[Int].extrapolate(element => Iterator.from(element))
  val repeater = Flow[Int].extrapolate(element => Iterator.continually(element))

  //slowCounter.via(repeater).to(hungrySink).run()

  //expand extrapolate all the time
  val expander = Flow[Int].expand(element => Iterator.from(element))

  slowCounter.via(expander).to(hungrySink).run()
}