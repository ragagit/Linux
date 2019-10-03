package ScalaAkkaRecap

import scala.concurrent.Future
import scala.util.{Failure, Success}

object ScalaRecap extends App {

  val aCondition: Boolean = false
  def myFunction(x: Int) = {
    // code
    if (x > 4) 42 else 65
  }
  // instructions vs expressions
  // types + type inference

  // OO features of Scala
  class Animal
  trait Carnivore {
    def eat(a: Animal): Unit
  }

  object Carnivore

  // Companion objects can access each other members

  // generics
  abstract class MyList[+A]

  // method notations
  1 + 2 // infix notation. Here + is the method
  1.+(2) // syntactic sugar notation

  //apply methos allow classes instances to be called as if they were methods

  // Functional Programming
  val anIncrementer1: Function1[Int,Int] = (x: Int) => x + 1
  //the above function can be rewritten using syntactic sugar.
  //methods are instances of traits in this case Function1
  val anIncrementer: Int => Int = (x: Int) => x + 1
  anIncrementer(1)

  //Functional programming is about passing or returning functions
  List(1,2,3).map(anIncrementer)

  //High order functions (HOF) are functions that take or return functions
  // HOF: flatMap, filter
  // for-comprehensions

  // Monads: Option, Try
  // Pseudo collections that expose Maps, flatMaps, filters over them
  // A monad is a mechanism for sequencing computations.
  //

  // Pattern matching!
  val unknown: Any = 2
  val order = unknown match {
    case 1 => "first"
    case 2 => "second"
    case _ => "unknown"
  }

  try {
    // code that can throw an exception
    throw new RuntimeException
  } catch {
    case e: Exception => println("I caught one!")
  }

  /**
    * Scala advanced
    */

  // multithreading
  // For Futures in scala we need an execution context which is a manager of threads
  import scala.concurrent.ExecutionContext.Implicits.global
  val future = Future {
    // long computation here
    // executed on SOME other thread
    42
  } // here we would need a second paramter but we are using the implicit ExecutionContext
  // map, flatMap, filter + other niceties e.g. recover/recoverWith

  future.onComplete {
    case Success(value) => println(s"I found the meaning of life: $value")
    case Failure(exception) => println(s"I found $exception while searching for the meaning of life!")
  } // on SOME thread

  //partial functions are based on pattern mathcin if the input Int matches any of the cases then it returns
  //it otherwise returns the default
  val partialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 65
    case _ => 999
  }
  // based on pattern matching!

  // type aliases
  type AkkaReceive = PartialFunction[Any, Unit]
  def receive: AkkaReceive = {
    case 1 => println("hello!")
    case _ => println("confused...")
  }

  // Implicits!
  implicit val timeout = 3000
  def setTimeout(f: () => Unit)(implicit timeout: Int) = f()

  setTimeout(() => println("timeout"))// other arg list injected by the compiler

  // conversions
  // 1) implicit methods
  case class Person(name: String) {
    def greet: String = s"Hi, my name is $name"
  }

  implicit def fromStringToPerson(name: String) = Person(name)
  "Peter".greet
  // fromStringToPerson("Peter").greet

  // 2) implicit classes
  implicit class Dog(name: String) {
    def bark = println("Bark!")
  }
  "Lassie".bark
  // new Dog("Lassie").bark

  // implicit organizations
  // 1.- Local scope
  // 2.- imported scope
  // 3.- companion objects

  // local scope
  implicit val numberOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _)
  List(1,2,3).sorted //(numberOrdering) => List(3,2,1)

  // imported scope

  // companion objects of the types involved in the call
  object Person {
    implicit val personOrdering: Ordering[Person] = Ordering.fromLessThan((a, b) => a.name.compareTo(b.name) < 0)
  }

  List(Person("Bob"), Person("Alice")).sorted // (Person.personOrdering)
  // => List(Person("Alice"), Person("Bob"))


}

