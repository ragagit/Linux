package com.scalaExample.oops

object ConstructorOverloading {

  def main(args: Array[String]): Unit = {
    new Student(101)
    new Student(100, "India")
  }

  class Student(id: Int) {
    def this(id: Int, name: String) = {
      this(id)
      println(id + " " + name)
    }

    println(id)
  }


}
