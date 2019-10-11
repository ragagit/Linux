package com.sparkExample.advanced.broadcastvariables

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

import scala.io.Source

object StoresInEachUSRegion {

  def main(args: Array[String]): Unit = {

    /*
      Create Spark program to find out count of stores in each US region. Read input data from
      1. us_states.csv
          contains reference data about US states, including their abbreviation, full name, and the regional classification provided by the US Census Bureau.
          For example, Alabama is considered to be in the South.
      2.store_locations.csv
          contains the city, state, and zip code for the 475 Costco warehouses in the United States

      Sample Output:


     */

    Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setAppName("StoresInEachUSRegion").setMaster("local[1]")
    val sc = new SparkContext(conf)
    val stateToRegionMap = sc.broadcast(loadStateToRegionMapping)
    val lines = sc.textFile("in/store_locations.csv").filter(line => !line.startsWith("#"))
    val regions = lines.map(line => stateToRegionMap.value.get(line.split(",")(1)))
    for ((region, count) <- regions.countByValue()) println(region.get + " : " + count)
  }

  def loadStateToRegionMapping(): Map[String, String] = {
    Source
      .fromFile("in/us_states.csv")
      .getLines()
      .map(line => line.split(",")(0) -> line.split(",")(2))
      .toMap
  }
}
