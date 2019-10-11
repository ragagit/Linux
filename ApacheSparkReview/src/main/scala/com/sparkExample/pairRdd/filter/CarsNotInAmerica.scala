package com.sparkExample.pairRdd.filter

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object CarsNotInAmerica {

  def main(args: Array[String]): Unit = {

    /*

      Create a Spark program to read the car data from in/cars.csv;
      generate a pair RDD with car model name being the key and country name being the value.
      Then remove all the cars which are manufactured in America and output the pair RDD to out/cars_not_in_america_pair_rdd

      Each row of the input file contains the following columns:
      Model,MPG,Cylinders,Engine Disp,Horsepower,Weight,Accelerate,Year,Origin
      Sample output:
      amc ambassador dpl,15.0,8,390,190,3850,8.5,70,American
      ...

     */

    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc = new SparkContext(new SparkConf().setAppName("CarsNotInAmerica").setMaster("local[1]"))

    //get Raw Data
    val rawData = sc.textFile("in/cars.csv").filter(line => !line.startsWith("Model"))

    //convert normal RDD to Pair RDD
    val carsPairRDD = rawData.map(line => (line.split(",")(0),line.split(",")(8)))

    //filter data
    val filteredRDD = carsPairRDD.filter(key => !key._2.equalsIgnoreCase("American"))

    filteredRDD.foreach(println)
    //filteredRDD.saveAsTextFile("out/cars_not_in_america_pair_rdd")
  }
}
