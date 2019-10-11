package com.scalaExample.collections.assigments

object SortStudentList {

  case class Student(id: String, name: String, grade: Double)

  def main(args: Array[String]): Unit = {
    val student1 = Student("1001", "Jim", 3.4D)
    val student2 = Student("1002", "Cindy", 3.8D)
    val student3 = Student("2001", "Bill", 2.4D)
    val students = List(student1, student2, student3)

    //sort by grade, from lowest to highest
    println("Sort by grade, from lowest to highest :: " + students.sortBy(student => student.grade))

    //sort by grade, from highest to lowest or descending order(add a - (minus) sign)
    println("Sort by grade, from highest to lowest :: " + students.sortBy(student => -student.grade))

    //sort by name in ascending order
    println("Sort by name in ascending order :: " + students.sortBy(student => student.name))

    //sort by name in descending order, we will need to use sortWith
    println("Sort by name in descending order :: " + students.sortWith((s1, s2) => s1.name > s2.name))
  }

}
