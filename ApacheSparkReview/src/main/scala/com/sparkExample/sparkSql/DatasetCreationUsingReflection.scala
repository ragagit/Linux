package com.sparkExample.sparkSql

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object DatasetCreationUsingReflection {

  case class Person(name: String, age: Long)

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)

    val sparkSession = SparkSession
      .builder()
      .appName("Spark SQL basic example")
      .master("local[1]")
      .getOrCreate()

    import sparkSession.implicits._

    // Create an RDD of Person objects from a text file, convert it to a Dataframe
    val peopleDF = sparkSession.sparkContext
      .textFile("in/people.txt")
      .map(_.split(","))
      .map(attributes => Person(attributes(0), attributes(1).trim.toInt))
      .toDF()

    // Register the DataFrame as a temporary view
    peopleDF.createOrReplaceTempView("people")

    // SQL statements can be run by using the sql methods provided by Spark
    val teenagersDF = sparkSession.sql(
      "SELECT name, age FROM people WHERE age BETWEEN 13 AND 19")

    // The columns of a row in the result can be accessed by field index
    teenagersDF.map(teenager => "Name: " + teenager(0)).show()

    // or by field name
    teenagersDF
      .map(teenager => "Name: " + teenager.getAs[String]("name"))
      .show()
  }
}
