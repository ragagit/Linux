name := "ApacheSparkReview"

version := "0.1"

scalaVersion := "2.11.4"

libraryDependencies ++= {
  val sparkVer = "2.1.0"
  Seq(
    "org.apache.spark" %% "spark-core" % sparkVer withSources(),
    "org.apache.spark" %% "spark-sql" % sparkVer withSources(),
    "org.apache.spark" %% "spark-streaming" % sparkVer withSources(),
    "org.apache.bahir" %% "spark-streaming-twitter" % "2.0.1" withSources()
  )
}