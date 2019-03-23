name := "ScalaReview"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies += "io.spray" %%  "spray-json" % "1.3.4"
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.1.1"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.5" % "test"
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.19" % Test