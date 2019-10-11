package com.sparkExample.pairRdd.sort

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object SortedWordCountProblem {

  def main(args: Array[String]): Unit = {

    /*

    Create a Spark program to read the an article from in/word_count.txt,
    output the number of occurrence of each word in descending order.

    Sample output:

    ball : 200
    cat : 193
    ant : 176
    ...

   */


    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("wordCounts").setMaster("local[3]")
    val sc = new SparkContext(conf)

    val lines = sc.textFile("in/word_count.txt")
    val wordRdd = lines.flatMap(line => line.split(" "))

    val wordPairRdd = wordRdd.map(word => (word, 1))
    val wordToCountPairs = wordPairRdd.reduceByKey((x, y) => x + y)

    val countToWordParis = wordToCountPairs.map(wordToCount => (wordToCount._2, wordToCount._1))

    val sortedCountToWordParis = countToWordParis.sortByKey(ascending = false)

    val sortedWordToCountPairs = sortedCountToWordParis.map(countToWord => (countToWord._2, countToWord._1))

    for ((word, count) <- sortedWordToCountPairs.collect()) println(word + " : " + count)
  }
}
