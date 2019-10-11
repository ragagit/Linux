package com.scalaExample.oops

object MethodOverloading {

  def main(args: Array[String]): Unit = {
    val calculator = new Calculator()
    println(calculator.add(10, 20))
    println(calculator.add(10, 20, 30))

    //Overloading - Different data type
    println(calculator.add(10.0, 20.1))
  }

  class Calculator {

    def add(a: Int, b: Int): Int = {
      val sum = a + b
      return sum
    }

    def add(a: Double, b: Double): Double = {
      val sum = a + b
      return sum
    }

    def add(a: Int, b: Int, c: Int): Int = {
      val sum = a + b + c
      return sum
    }

  }

}
