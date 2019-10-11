package com.sparkExample.advanced.accumulators

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object BangaloreStoreLogAnalysis {

  def main(args: Array[String]): Unit = {
    /*

      Write a Spark program to read in/bangalore_store.log and find out corrupted logs and distinguish them as
      1.invalid logs lines
      2.network issues.

      Dataset contains sample list of purchase transactions of chain of stores in Bangalore
      It has following fields
      Field 1 -> City
      Field 2 -> Locality
      Field 3 -> Category of item sold
      Field 4 -> Value of item sold

     */


    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("BangaloreStoreLogAnalysis").setMaster("local[1]")
    val sc = new SparkContext(conf)

    val lines = sc.textFile("in/bangalore_store.log")

    val totalCountOfTransactions = sc.longAccumulator("Total count of transactions")
    val invalidLogs = sc.longAccumulator("Invalid Logs")
    val emptyLines = sc.longAccumulator("Empty lines")

    lines.foreach(line => {
      totalCountOfTransactions.add(1)
      if (line.isEmpty) emptyLines.add(1)
      if (line.split("\t").length != 4) invalidLogs.add(1)
    })
    println("Total number of transactions : " + totalCountOfTransactions.value)
    println("Total number of empty lines : " + emptyLines.value)
    println("Total number of invalid logs lines : " + invalidLogs.value)
  }

}
