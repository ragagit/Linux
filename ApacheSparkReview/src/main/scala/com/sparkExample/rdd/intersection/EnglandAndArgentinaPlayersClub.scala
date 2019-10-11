package com.sparkExample.rdd.intersection

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object EnglandAndArgentinaPlayersClub {

  def main(args: Array[String]): Unit = {

    /*

        Create a Spark program to read the player data from in/worldcupplayerinfo_20140701.tsv, find club information of England and Argentina players.
        Then output unique club names to out/england_argentina_club

        Each row of the input file contains the following columns:
        Group	Country	Rank	Jersey	Position	Age	Selections	Club	Player	Captain


     */

    Logger.getLogger("org").setLevel(Level.ERROR)

    //create spark context
    val sc = new SparkContext(new SparkConf().setAppName("EnglandAndArgentinaPlayersClub").setMaster("local[1]"))

    //Read data,skip header info
    val rawData = sc.textFile("in/worldcupplayerinfo_20140701.tsv").filter(line => !line.startsWith("Group"))

    val englandPlayers = rawData.filter(line => line.split("\\t")(1).equalsIgnoreCase("england"))
    val argentinaPlayers = rawData.filter(line => line.split("\\t")(1).equalsIgnoreCase("argentina"))

    val englandPlayersClub = englandPlayers.map(line => line.split("\\t")(7))
    val argentinaPlayersClib = argentinaPlayers.map(line => line.split("\\t")(7))
    englandPlayersClub.foreach(x => print(x +" "))
    println()
    argentinaPlayersClib.foreach(x => print(x +" "))
    println()
    val uniqueClubNames = englandPlayersClub.intersection(argentinaPlayersClib)
    uniqueClubNames.foreach(x => print(x +" "))
    println()
    uniqueClubNames.saveAsTextFile("out/england_argentina_club")

  }
}
