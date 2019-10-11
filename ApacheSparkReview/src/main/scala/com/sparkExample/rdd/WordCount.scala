package com.sparkExample.rdd

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("Word Count").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("in/word_count.txt");
    val words = lines.flatMap(line=>line.split(" "))
    val wordCounts = words.countByValue();
    for((word,count)<-wordCounts){
      println(word + " - "+count)
    }
  }
}
