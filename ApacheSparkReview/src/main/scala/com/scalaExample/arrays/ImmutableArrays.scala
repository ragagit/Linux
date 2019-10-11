package com.scalaExample.arrays

object ImmutableArrays {

  def main(args: Array[String]): Unit = {

    //If you know all your array elements initially
    val games = Array("Football", "Cricket", "Hockey")
    for (i <- 0 until games.length) {
      println(i + " : " + games(i))
    }

    //If you don't know the strings that you want in your array initially, but know the size of your array
    val fruits = new Array[String](3)

    fruits(0) = "Apple"
    fruits(1) = "Orange"
    fruits(2) = "Kiwi"

    for (fruit <- fruits) {
      print(fruit + " ")
    }


  }
}
