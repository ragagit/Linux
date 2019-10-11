package com.scalaExample.collections.sets

object PlusPlusOperator {

  def main(args: Array[String]) {
    val fruits1 = Set("apples", "oranges", "pears")
    val fruits2 = Set("mangoes", "banana","apples")

    // use two or more sets with ++ as operator
    var fruits = fruits1 ++ fruits2
    println("fruits1 ++ fruits2 : " + fruits)

    // use two sets with ++ as method
    fruits = fruits1.++(fruits2)
    println("fruits1.++(fruit2) : " + fruits)
  }

}
