package com.sparkExample.pairRdd.reducebykey

import java.text.DecimalFormat

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object AverageFundingByCity {

  def main(args: Array[String]): Unit = {

    /*

      Create Spark program to read data from in/startup_funding_info.csv
      This data set contains information about Indian startups which have got funding.
      Output average funding received by each city

      Date,Startup Name,Vertical,Sub-Vertical,City,Investor,Type,Amount (in USD),Remarks


     */

    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("AverageFundingByCity").setMaster("local[1]")
    val sc = new SparkContext(conf)

    val lines = sc.textFile("in/startup_funding_info.csv")
    val cleanedLines = lines.filter(line => !line.contains("Date"))
    val validLines = cleanedLines.filter(line => !(line.split(",")(6).trim.equals("N/A") ||
                                                    line.split(",")(4).trim.equals("")) )

    val pairRdd = validLines.map(line => (line.split(",")(4), (1, (line.split(",")(6)).toDouble)))
    //val res = pairRdd.foreach(println)

    val totalFunding = pairRdd.reduceByKey((x, y) => (x._1 + y._1, x._2 + y._2))
    //totalFunding.foreach(println)

    val finalResult = totalFunding.mapValues(avg => avg._2 / avg._1)
    val formatter = new DecimalFormat("#.###")
    for ((city, avgFunding) <- finalResult.collect()) println(city + " : " + formatter.format(avgFunding))


  }

}
