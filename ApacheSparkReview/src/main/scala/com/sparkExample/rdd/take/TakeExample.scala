package com.sparkExample.rdd.take

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object TakeExample {

  def main(args: Array[String]): Unit = {

    /*
      "nasa_accesslogs_1995-Jul-01.tsv" file contains log lines from one of NASA's apache server for July 1st, 1995

       Create a Spark program to generate a new RDD which contains the hosts which are accessed on July 1st, 1995.
       Print 3 hosts to console

     */

    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc = new SparkContext(new SparkConf().setMaster("local[1]").setAppName("TakeExample"))

    //create RDD and remove header line
    val rawData = sc.textFile("in/nasa_accesslogs_1995-Jul-01.tsv").filter(line => !line.startsWith("host"))

    val allHosts = rawData.map(line => line.split("\\t")(0))

    val threeHosts = allHosts.take(3)
    for (host <- threeHosts) {
      println(host)
    }
  }
}
