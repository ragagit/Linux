package com.sparkExample.rdd.count

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object CountExample {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc = new SparkContext(new SparkConf().setMaster("local[1]").setAppName("CountExample"))

    val words = List("Hadoop","Spark","Spark","Kafka","Cassandra","Apache","Apache")

    val wordsRdd = sc.parallelize(words)
    wordsRdd.countByValue().foreach(word => println(word))

    for ((word, count) <- wordsRdd.countByValue()) println(word + " : " + count)
  }
}
