package com.scalaExample.oops

object CompanionClass {

  def main(args: Array[String]): Unit = {
    var aMain: Main = new Main()
    aMain.sayHelloWorld() // You can instantiate Main and call
    Main.sayHi() // sayHelloWorld or Call sayHi() on Companion Object

  }

  class Main {
    def sayHelloWorld() {
      println("Hello World")
    }
  }

  object Main {
    def sayHi() {
      println("Hi!")
    }
  }


}
