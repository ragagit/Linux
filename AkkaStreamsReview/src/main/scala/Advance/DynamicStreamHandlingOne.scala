package Advance

import akka.actor.ActorSystem
import akka.stream.scaladsl.{BroadcastHub, Keep, MergeHub, Sink, Source}
import akka.stream.{ActorMaterializer, KillSwitches}
import scala.concurrent.duration._
import scala.language.postfixOps

object DynamicStreamHandlingOne extends App {

  implicit val system = ActorSystem("DynamicStreamHandling")
  implicit val materializer = ActorMaterializer()
  import system.dispatcher

  // #1: Kill Switch.
  // killSwitchFlow kill one stream
  val killSwitchFlow = KillSwitches.single[Int]
  val counter = Source(Stream.from(1)).throttle(1, 1 second).log("counter")
  val counter1 = Source( 1 to 10).throttle(1, 1 second).log("counter1")
  val sink = Sink.ignore

//    val killSwitch = counter
//      .viaMat(killSwitchFlow)(Keep.right)
//      .to(sink)
//      .run()
//
//    system.scheduler.scheduleOnce(4 seconds) {
//      killSwitch.shutdown()
//    }


  // shared kill switch
  val anotherCounter = Source(Stream.from(1)).throttle(2, 1 second).log("anotherCounter")
  val sharedKillSwitch = KillSwitches.shared("oneButtonToRuleThemAll")

//  counter.via(sharedKillSwitch.flow).runWith(Sink.ignore)
//  anotherCounter.via(sharedKillSwitch.flow).runWith(Sink.ignore)
//
//  system.scheduler.scheduleOnce(3 seconds) {
//    sharedKillSwitch.shutdown()
//  }

  // MergeHub
  val dynamicMerge = MergeHub.source[Int]
  val materializedSink = dynamicMerge.to(Sink.foreach[Int](println)).run()

  // use this sink any time we like
  Source(100 to 120).runWith(materializedSink)
//  counter1.runWith(materializedSink)

  // BroadcastHub

  val dynamicBroadcast = BroadcastHub.sink[Int]
  val materializedSource = Source(1 to 100).runWith(dynamicBroadcast)

//  materializedSource.runWith(Sink.ignore)
//  materializedSource.runWith(Sink.foreach[Int](println))

  /**
    * Challenge - combine a mergeHub and a broadcastHub.
    *
    * A publisher-subscriber component
    */
  val merge = MergeHub.source[String]
  val bcast = BroadcastHub.sink[String]
  val (publisherPort, subscriberPort) = merge.toMat(bcast)(Keep.both).run()

  subscriberPort.runWith(Sink.foreach(e => println(s"I received: $e")))
  subscriberPort.map(string => string.length).runWith(Sink.foreach(n => println(s"I got a number: $n")))

  Source(List("Akka", "is", "amazing")).runWith(publisherPort)
  Source(List("I", "love", "Scala")).runWith(publisherPort)
  Source.single("STREEEEEEAMS").runWith(publisherPort)

}
