package com.sparkExample.sparkSql

import org.apache.log4j.{Level, Logger}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SQLContext


object EbayAuctionDataAnalaysis {

  def main(args: Array[String]): Unit = {

    /*
      Create Spark program to read eBay online auction dataset from "in/ebay_auction_data.csv"

      It has the following data fields:
      auctionid - unique identifier of an auction
      bid - the proxy bid placed by a bidder
      bidtime - the time (in days) that the bid was placed, from the start of the auction
      bidder - eBay username of the bidder
      bidderrate - eBay feedback rating of the bidder
      openbid - the opening bid set by the seller
      price - the closing price that the item sold for (equivalent to the second highest bid + an increment)

      Answer following questions

      1.How many auctions were held?
      2.How many bids were made per item?
      3.What's the minimum, maximum, and average number of bids per item?
      4.Show the bids with price > 100

     */

    Logger.getLogger("org").setLevel(Level.ERROR)
    val sc = new SparkContext(new SparkConf().setMaster("local[1]").setAppName("EbayAuctionDataAnalaysis"))

    val session = SparkSession.builder().appName("EbayAuctionDataAnalaysis").master("local[1]").getOrCreate()
    val sqlContext = session.sqlContext
    val rawData = sc.textFile("in/ebay_auction_data.csv")

    val datamap = rawData.map(rec => rec.split(","))
    val auctionData = datamap.map(rec => Auction(rec(0), rec(1).toFloat, rec(2).toFloat, rec(3), rec(4).toInt, rec(5).toFloat, rec(6).toFloat, rec(7), rec(8).toInt))

    import sqlContext.implicits._

    val dataDF = auctionData.toDF()

    dataDF.createOrReplaceTempView("auction")

    //How many auctions were held?
    sqlContext.sql("Select count(*) as totalAuctions from auction").show()

    //How many bids were made per item
    sqlContext.sql("Select item,count(bid) from auction group by item").show()

    //What's the minimum, maximum, and average number of bids per item?
    sqlContext.sql("Select item,max(bid) from auction group by item").show()
    sqlContext.sql("Select item,min(bid) from auction group by item").show()
    sqlContext.sql("Select item,avg(bid) from auction group by item").show()

    //Show the bids with price > 100
    sqlContext.sql("Select * from auction where bid > 100")
  }


  case class Auction(auctionid: String, bid: Float, bidtime: Float, bidder: String, bidderrate: Integer, openbid: Float, price: Float, item: String, daystolive: Integer)

}
