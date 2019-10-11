package com.sparkExample.DataFrames

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object SimpleDataFrame {

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark = SparkSession
      .builder()
      .appName("DataFrame App")
      .config("spark.master", "local")
      .getOrCreate();
    import spark.implicits._

    // Create a DataFrame from Spark Session read csv
    // Technically known as class Dataset
    val df = spark.read.option("header","true").option("inferSchema","true").csv("in/CitiGroup2006_2008")

    // Get first 5 rows
    df.head(5) // returns an Array
    println("\n")
    for(line <- df.head(10)){
      println(line)
    }

    //Get column names
    df.columns

    // Find out DataTypes
    // Print Schema
    df.printSchema()

    // Describe DataFrame Numerical Columns
    df.describe().show()

    // Select columns .transform().action()
    df.select("Volume").show()

    // Multiple Columns
    df.select($"Date",$"Close").show(2)

    // Creating New Columns
    val df2 = df.withColumn("HighPlusLow",df("High")+df("Low"))
    // Show result
    df2.columns
    df2.printSchema()

    // Recheck Head
    df2.head(5)

    // Renaming Columns (and selecting some more)
    df2.select(df2("HighPlusLow").as("HPL"),df2("Close")).show()

    // That is it for now! We'll see these basic functions
    // a lot more as we go on.


  }

}
