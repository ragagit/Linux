name := "AkkaStreams"

version := "0.1"

scalaVersion := "2.12.8"


libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.22",
  "com.typesafe.akka" %% "akka-stream" % "2.5.22",
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.22" % Test,
  "org.scalatest" % "scalatest_2.12" % "3.0.5" % "test"
)
