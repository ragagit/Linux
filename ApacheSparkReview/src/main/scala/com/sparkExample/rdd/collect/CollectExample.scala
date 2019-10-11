package com.sparkExample.rdd.collect

import org.apache.spark.{SparkConf, SparkContext}

object CollectExample {

  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setAppName("CollectExample").setMaster("local[2]"))

    val fruits = List("Banana", "Mango", "Strawberry", "Cherry")
    val fruitsRdd = sc.parallelize(fruits)

    val collectedFruits = fruitsRdd.collect()
    collectedFruits.foreach(fruit => println(fruit))


  }
}
