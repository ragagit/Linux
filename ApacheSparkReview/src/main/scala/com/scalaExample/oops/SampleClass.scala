package com.scalaExample.oops

class Employee {
  var id: Int = 0; // All fields must be initialized
  var name: String = null;
}

object SampleClass {
  def main(args: Array[String]) {
    var s = new Employee() // Creating an object
    println(s.id + " " + s.name);
  }
}
