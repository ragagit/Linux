package com.sparkExample.rdd.reduce

import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.apache.spark.{SparkConf, SparkContext}

object SumOfNumbersSolution {

  def main(args: Array[String]) {


    /* Create a Spark program to read the first 100 prime numbers from in/prime_nums.txt,
       print the sum of those numbers to console.

       Each row of the input file contains 10 prime numbers separated by spaces.
     */

    Logger.getLogger("org").setLevel(Level.OFF)

    val conf = new SparkConf().setAppName("primeNumbers").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val lines = sc.textFile("in/prime_nums.txt")

    val numbers = lines.flatMap(line => line.split("\\s+"))

    val validNumbers = numbers.filter(number => !number.isEmpty)

    val intNumbers = validNumbers.map(number => number.toInt)

    println("Sum is: " + intNumbers.reduce((x, y) => x + y))
  }
}
