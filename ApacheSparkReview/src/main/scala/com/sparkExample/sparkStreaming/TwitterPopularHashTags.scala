package com.sparkExample.sparkStreaming

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object TwitterPopularHashTags {

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)
    val sparkConf = new SparkConf().setAppName("TwitterSentimentAnalysis").setMaster("local[*]")
    val ssc = new StreamingContext(sparkConf, Seconds(30))

    // Set the system properties so that Twitter4j library used by twitter stream
    // can use them to generat OAuth credentials
    System.setProperty("twitter4j.oauth.consumerKey", "")
    System.setProperty("twitter4j.oauth.consumerSecret", "")
    System.setProperty("twitter4j.oauth.accessToken", "")
    System.setProperty("twitter4j.oauth.accessTokenSecret", "")

    val stream = TwitterUtils.createStream(ssc, None)

    // Split the stream on space and extract hashtags
    val hashTags = stream
      .flatMap(status =>
        status.getText.split(" ")
          .filter(tag => tag.startsWith("#")))

    // Get the top hashtags over the previous 60 sec window
    val topCounts60 = hashTags
      .map((word => (word, 1)))
      .reduceByKeyAndWindow((x, y) => (x + y), Seconds(60))
      .map { case (topic, count) => (count, topic) }
      .transform(statuses => statuses.sortByKey(false))

    // print tweets in the currect DStream
    stream.print()

    // Print popular hashtags
    topCounts60.foreachRDD(rdd => {
      val topList = rdd.take(10)
      println("\nPopular topics in last 60 seconds (%s total):".format(rdd.count()))
      topList.foreach { case (count, tag) => println("%s (%s tweets)".format(tag, count)) }
    })

    ssc.start()
    ssc.awaitTermination()
  }
}
