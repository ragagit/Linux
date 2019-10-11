package com.scalaExample.traits

object TraitExamples {

  //Trait can have abstract method
  trait Flyable {
    def fly()
  }

  //Trait may not have any abstract method
  trait Speakable {
    def makeNoice() {
      println("Generic Sound...")
    }
  }

  //Trait can extand one or more traits
  trait Quackable extends Speakable {

    //have to override method of parent
    override def makeNoice() {
      println("Quack Quack...")
    }

    def quack() {
      makeNoice()
    }
  }

  //one class can extand multiple traits
  class Duck extends Quackable with Flyable {
    def swim() {
      println("Duck is Swimming...")
    }

    //have to override method of parent
    def fly() {
      println("Duck is Flying...")
    }
  }

  //Trait can have abstract and non-abstract methods
  trait Barkable extends Speakable {

    override def makeNoice() {
      println("Bow Bow...")
    }

    //non-abstract method
    def bark() {
      makeNoice()
    }

    //abstract method to be implemented by concrete class
    def wagTail()
  }

  class Dog extends Barkable {
    def wagTail() {
      println("tail is wagging")
    }
  }

  def main(args: Array[String]) {
    val duck = new Duck()
    duck.swim()
    duck.fly()
    duck.makeNoice()
    duck.quack()
  }
}
