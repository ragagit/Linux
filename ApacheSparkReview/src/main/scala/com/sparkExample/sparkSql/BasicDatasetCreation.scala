package com.sparkExample.sparkSql

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object BasicDatasetCreation {

  case class Person(name: String, age: Long)

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)


    val spark = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .master("local[1]")
      .getOrCreate()


    import spark.implicits._

    // Encoders are created for case classes
    val caseClassDS = Seq(Person("Andy", 32)).toDS()
    caseClassDS.show()

    // Encoders for most common types are automatically provided by importing spark.implicits._
    val primitiveDS = Seq(1, 2, 3).toDS()
    primitiveDS.map(_ + 1).collect().foreach(x => print(x + " ")) // Returns: Array(2, 3, 4)
    println()

    // DataFrames can be converted to a Dataset by providing a class. Mapping will be done by name
    val path = "in/people1.json"
    val peopleDS = spark.read.json(path).as[Person]
    peopleDS.show()

  }
}
