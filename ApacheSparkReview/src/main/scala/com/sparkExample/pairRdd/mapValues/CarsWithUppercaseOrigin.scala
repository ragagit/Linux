package com.sparkExample.pairRdd.mapValues

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object CarsWithUppercaseOrigin {

  def main(args: Array[String]): Unit = {

    /*

        Create a Spark program to read the car data from in/cars.csv, generate a pair RDD with model name
        being the key and country name being the value. Then convert the country name to uppercase and
        output the pair RDD to out/cars_uppercase

        Each row of the input file contains the following columns:
        Model,MPG,Cylinders,Engine Disp,Horsepower,Weight,Accelerate,Year,Origin
        Sample output:
        amc ambassador dpl,15.0,8,390,190,3850,8.5,70,American
         ...

     */


    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc = new SparkContext(new SparkConf().setMaster("local[1]").setAppName("CarsWithUppercaseOrigin"))

    val rawData = sc.textFile("in/cars.csv").filter(line => !line.startsWith("Model"))

    val pairRDD = rawData.map(line => (line.split(",")(0),line.split(",")(8)))

    val rddWithUppercaseCountryNames = pairRDD.mapValues(modelName => modelName.toUpperCase)

    rddWithUppercaseCountryNames.saveAsTextFile("out/cars_uppercase")

  }

}
