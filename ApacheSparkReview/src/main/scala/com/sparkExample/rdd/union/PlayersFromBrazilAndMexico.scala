package com.sparkExample.rdd.union

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}

object PlayersFromBrazilAndMexico {

  def main(args: Array[String]): Unit = {

    /*

          Create a Spark program to read the player data from in/worldcupplayerinfo_20140701.tsv,  find all the players who belong to Brazil And Mexico.
          Then output the player's name and country to out/players_in_brzil_and_mexico.

          Each row of the input file contains the following columns:
          Group	Country	Rank	Jersey	Position	Age	Selections	Club	Player	Captain
          Sample output:
          A	Brazil	3	1	Goalie	31	9	Botafogo   	Jefferson	0
           ...

     */

    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext(new SparkConf().setAppName("PlayersFromBrazilAndMexico").setMaster("local[2]"))

    //read data. Filter header row
    val rawData = sc.textFile("in/worldcupplayerinfo_20140701.tsv").filter(line => !line.startsWith("Group"))

    val brazilPlayers = rawData.filter(line => line.split("\\t")(1).equalsIgnoreCase("brazil"))
    val mexicoPlayers = rawData.filter(line => line.split("\\t")(1).equalsIgnoreCase("mexico"))

    val brazilAndMexicoPlayers = brazilPlayers.union(mexicoPlayers)

    val playerInfo = brazilAndMexicoPlayers.map(line=> line.split("\\t")(8) + "," + line.split("\\t")(1))
    playerInfo.foreach(word => println(word))
    playerInfo.saveAsTextFile("out/players_in_brzil_and_mexico")
  }
}
