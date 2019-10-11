package com.sparkExample.pairRdd.join

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object JoinOperations {

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("JoinOperations").setMaster("local[1]")
    val sc = new SparkContext(conf)

    val ages = sc.parallelize(List(("Mohan", 19), ("Kiran", 42)))
    val addresses = sc.parallelize(List(("Karthik", "India"), ("Kiran", "Srilanka")))

    val join = ages.join(addresses)
    println(join.collect().mkString(","))

    val leftOuterJoin = ages.leftOuterJoin(addresses)
    println(leftOuterJoin.collect().mkString(","))

    val rightOuterJoin = ages.rightOuterJoin(addresses)
    println(rightOuterJoin.collect().mkString(","))

    val fullOuterJoin = ages.fullOuterJoin(addresses)
    println(fullOuterJoin.collect().mkString(","))

  }

}
