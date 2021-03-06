package com.scalaExample.oops

object SecondaryConstructorExample {

  def main(args: Array[String]): Unit = {
    var s = new Student(101, "Mohan", 20);
    s.showDetails()
  }

  class Student(id: Int, name: String) {
    var age: Int = 0

    def showDetails() {
      println(id + " " + name + " " + age)
    }

    def this(id: Int, name: String, age: Int) {
      this(id, name) // Calling primary constructor, and it is first line
      this.age = age
    }
  }


}
