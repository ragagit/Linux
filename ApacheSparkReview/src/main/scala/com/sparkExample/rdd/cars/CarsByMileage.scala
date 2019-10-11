package com.sparkExample.rdd.cars

import org.apache.spark.{SparkConf, SparkContext}

object CarsByMileage {

  def main(args: Array[String]): Unit = {

   /*

    Create a Spark program to read the car data from in/cars.csv,  find all the cars whose mileage is greater than 15.
    Then output the car's model and the car's year of manufacture to out/cars_by_mileage.

    Each row of the input file contains the following columns:
    Model,MPG,Cylinders,Engine Disp,Horsepower,Weight,Accelerate,Year,Origin
    Sample output:
    amc ambassador dpl,15.0,8,390,190,3850,8.5,70,American
     ...

    */

    val sc = new SparkContext(new SparkConf().setMaster("local[2]").setAppName("CarsByMileage"))
    val rawData = sc.textFile("in/cars.csv")

    //remove first line
    val validRows = rawData.filter(line => !line.startsWith("#"))

    //Get required data
    val filteredData = validRows.filter(car => car.split(",")(1).toDouble > 15)

    //get model and year
    val carsWithModelNameAndYear = filteredData.map(car => car.split(",")(0) + ",19" + car.split(",")(7))

    //save in text file
    carsWithModelNameAndYear.saveAsTextFile("out/cars_by_mileage")
  }
}
