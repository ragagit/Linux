package com.sparkExample.rdd.cars

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object CarsInAmerica {

  def main(args: Array[String]): Unit = {

    /*
        Create a Spark program to read the car data from in/cars.csv,  find all the cars whose origin is America.
        Then output the car's model and the car's year of manufacture to out/cars_in_america.

        Each row of the input file contains the following columns:
        Model,MPG,Cylinders,Engine Disp,Horsepower,Weight,Accelerate,Year,Origin
        Sample output:
        amc ambassador dpl,15.0,8,390,190,3850,8.5,70,American
         ...
    */

    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext(new SparkConf().setAppName("CarsInAmerica").setMaster("local[1]"))

    val rawData = sc.textFile("in/cars.csv").filter(car => !car.startsWith("#"));

    val filteredData = rawData.filter(car => car.split(",")(8).equalsIgnoreCase("American"))

    val carsInAmerica = filteredData.map(car => car.split(",")(0) + ",19" + car.split(",")(7))

    carsInAmerica.saveAsTextFile("out/cars_in_america")
  }
}
