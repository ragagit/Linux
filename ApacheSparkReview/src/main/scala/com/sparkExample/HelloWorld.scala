package com.sparkExample

import org.apache.log4j.{Level, Logger}
import org.apache.spark.storage.StorageLevel
import org.apache.spark.{SparkConf, SparkContext}

object HelloWorld {

  def main(args: Array[String]) {

    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("HelloWorld").setMaster("local[1]")
    val sc = new SparkContext(conf)

    val lines = sc.textFile("in/word_count.txt")
    val numLines = lines.count()
    println("Num lines:" + numLines)
    val nonEmptyLines = lines.filter( line => line.length > 1)
    println("Non-Empty lines:" +  nonEmptyLines.count())
    nonEmptyLines.foreach(println)
    //val words = lines.flatMap(line => line.split(" "))//transformation

    //val wordCounts = words.countByValue() //action
    //for ((word, count) <- wordCounts) println(word + " : " + count)
  }

}
