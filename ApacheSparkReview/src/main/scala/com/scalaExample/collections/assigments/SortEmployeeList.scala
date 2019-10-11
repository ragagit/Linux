package com.scalaExample.collections.assigments

object SortEmployeeList {

  case class Employee(id: Int, name: String)

  def main(args: Array[String]): Unit = {
    val e1 = new Employee(1, "John")
    val e2 = new Employee(2, "Bruce")
    val e3 = new Employee(3, "Sandra")

    val list = List(e2, e3, e1)

    println(list.sortBy(e => e.name))
  }
}
