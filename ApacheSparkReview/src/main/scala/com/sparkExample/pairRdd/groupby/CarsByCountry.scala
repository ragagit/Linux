package com.sparkExample.pairRdd.groupby

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object CarsByCountry {

  def main(args: Array[String]): Unit = {
    /*

      Create a Spark program to read the car data from in/cars.csv,
      output the the list of the names of the cars manufactured in each country.

      Each row of the input file contains the following columns:
      Model,MPG,Cylinders,Engine Disp,Horsepower,Weight,Accelerate,Year,Origin
      Sample input:
      amc ambassador dpl,15.0,8,390,190,3850,8.5,70,American
       ...

     */

    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("CarsByCountry").setMaster("local[1]")
    val sc = new SparkContext(conf)

    //get Raw Data
    val rawData = sc.textFile("in/cars.csv").filter(line => !line.startsWith("Model"))

    //convert normal RDD to Pair RDD
    val carsPairRDD = rawData.map(line => (line.split(",")(8), line.split(",")(0)))

    val carsByCountry = carsPairRDD.groupByKey()

    for ((country, cars) <- carsByCountry.collectAsMap()) {
      println(country + " = " + cars.toList)
    }

  }
}
