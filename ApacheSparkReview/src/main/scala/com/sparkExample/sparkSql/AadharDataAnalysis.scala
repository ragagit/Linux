package com.sparkExample.sparkSql

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object AadharDataAnalysis {

  def main(args: Array[String]): Unit = {

    /*
      Create Spark program to read aadhar dataset from in/UIDAI-ENR-DETAIL-20171128.csv

      Perform following operations
      1.Count for number of participants and count for each gender
      2.Count the number of identities(Aadhaar) generated by each Enrollment Agency and get Top 3
      3.Top 10 districts with maximum identities generated for both Male and Female
      4.Bottom 10 districts with maximum identities generated for both Male and Female

     */


    Logger.getLogger("org").setLevel(Level.ERROR)
    val session = SparkSession.builder().appName("AadharDataAnalysis").master("local[1]").getOrCreate()
    val rawData = session.read
      .option("header", "true")
      .option("inferSchema", value = true)
      .csv("in/UIDAI-ENR-DETAIL-20171128.csv")

    rawData.printSchema()

    //Show only selected columns
    val responsesWithSelectedColumns = rawData.select("State", "Age", "Aadhaar generated", "Enrolment Rejected")

    println("======== Only Selected Columns ========")
    responsesWithSelectedColumns.show(5)

    //print results only from Karnataka
    println("======== Results only from Karnataka ========")
    rawData.filter(rawData.col("State").===("Karnataka")).show(5)

    //print count of states involved
    println("======== Count of states involved ========")
    rawData.groupBy("State").count().show(5)

    //Count for number of participants and count for each gender
    println("======== Number of participants and count for each gender ========")
    rawData.groupBy("Gender").count().show(5)

    //Count the number of identities(Aadhaar) generated by each Enrollment Agency and get Top 3
    println("======== Number of identities(Aadhaar) generated by each Enrollment Agency ========")
    rawData.groupBy("Enrolment Agency").sum("Aadhaar generated").show(3)

    //Top 10 districts with maximum identities generated for both Male and Female
    println("======== Top 10 districts with maximum identities generated for both Male and Female ========")
    rawData.groupBy("District").sum("Aadhaar generated").orderBy(desc("sum(Aadhaar generated)")).show(10)

    //Bottom 10 districts with maximum identities generated for both Male and Female
    println("======== Bottom 10 districts with maximum identities generated for both Male and Female ========")
    rawData.groupBy("District").sum("Aadhaar generated").orderBy(asc("sum(Aadhaar generated)")).show(10)


  }

}
