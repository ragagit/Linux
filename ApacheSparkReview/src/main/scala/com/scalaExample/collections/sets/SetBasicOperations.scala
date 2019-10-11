package com.scalaExample.collections.sets

object SetBasicOperations {
  def main(args: Array[String]) {
    val fruits = Set("apples", "oranges", "pears")
    val nums: Set[Int] = Set()

    println("Head of fruit : " + fruits.head)
    println("Tail of fruit : " + fruits.tail)
    println("Check if fruit is empty : " + fruits.isEmpty)
    println("Check if nums is empty : " + nums.isEmpty)
  }
}

