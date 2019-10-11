package com.sparkExample.pairRdd.create

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object PairRddFromRegularRdd {

  def main(args: Array[String]) {

    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("PairRddFromRegularRdd").setMaster("local[1]")
    val sc = new SparkContext(conf)

    val inputStrings = List("Lily 23", "Jack 29", "Mary 29", "James 8")
    val regularRDDs = sc.parallelize(inputStrings)

    val pairRDD = regularRDDs.map(s => (s.split(" ")(0), s.split(" ")(1)))

    pairRDD.foreach(println)

  }
}
