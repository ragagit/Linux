package com.scalaExample.oops

class Student(id: Int, name: String) { // Primary constructor
  def show() {
    println(id + " " + name)
  }
}

object SampleClassWithConstructor {
  def main(args: Array[String]) {
    var s = new Student(100, "Mohan") // Passing values to constructor
    s.show() // Calling a function by using an object
  }
}

