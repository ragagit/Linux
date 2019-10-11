package com.scalaExample.collections.maps

object PlusOperator {

  def main(args: Array[String]): Unit = {
    var tasks: Map[Int, String] = Map()
    tasks += (1 -> "Get up from bed")
    tasks += (2 -> "Eat Breakfast")
    println("Map Contents : " + tasks)

  }
}
