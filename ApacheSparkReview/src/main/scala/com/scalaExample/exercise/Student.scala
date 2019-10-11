package com.scalaExample.exercise

import scala.collection.mutable._

object Student extends App {

  case class Alumno(id: Int, name: String)

  val alumnList = List(
    Alumno(1, "Mary Pacheco"),
    Alumno(2, "Bruce Jane"),
    Alumno(3, "Anna Holmes"),
    Alumno(4, "Zeth Adams")

  )
    println(alumnList.sortBy(al => al.name ))

}
