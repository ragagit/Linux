package com.scalaExample.collections.tuples

object TuplesOperations {
  def main(args: Array[String]) {
    val t = (4, 3, 2, 1)
    val sum = t._1 + t._2 + t._3 + t._4
    println("Sum of elements: " + sum)

    //Iterate over tuple
    t.productIterator.foreach { i => println("Value = " + i) }

    //Converting to String
    println("Concatenated String: " + t.toString())

    val t1 = new Tuple2("Scala", "hello")
    //Swap the elements
    println("Swapped Tuple: " + t1.swap)
  }
}

