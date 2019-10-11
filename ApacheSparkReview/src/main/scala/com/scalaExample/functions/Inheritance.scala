package com.scalaExample.functions

object Inheritance {

  def main(args: Array[String]): Unit = {
    new Programmer()
  }

  class Employee {
    var salary: Float = 10000
  }

  class Programmer extends Employee {
    var bonus: Int = 5000
    println("Salary = " + salary)
    println("Bonus = " + bonus)
  }

}
