package com.scalaExample.functions

object NamedArgumentExample {
  def main(args: Array[String]) {
    printInt(b = 5, a = 7);
  }

  def printInt(a: Int, b: Int) = {
    println("Value of a : " + a);
    println("Value of b : " + b);
  }
}
