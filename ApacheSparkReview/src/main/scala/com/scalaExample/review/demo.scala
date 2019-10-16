package com.scalaExample.review

import java.io.{File, FileNotFoundException, FileReader, IOException}
import java.lang.Error
import java.util.Date


import scala.Array._
import scala.util.matching.Regex
import scala.util.{Failure, Success, Try}
import spray.json._

import scala.util.parsing.combinator._
import scala.collection.mutable.ArrayBuffer

/*

- Imperative programming
  modifying mutable variables
  Using assignments
  Using control structures, if-else, loop.
  Von Neuman bottleneck

- Functional programming
  Programming without mutable variables, assignments, loops
  Focused on functions, that can be values, that are produced, composed and consumed.

- scala, sbt console
- Evaluation (2 + 4 ) + 7
- Substitution model SumOfSquares( 2, 3 + 5)
  All evaluation does is reduce an expression to a value
  It can be applied to all expressions, as long as they have no side effects (d++)
  No all expressions reduce to a value def loop: Int = lopp
  call-by-value, call-by-name.

- Conditional used for Expressions not statements
  def abs(x : Int): Int = if( x > 0 ) x else -x

- Definitions by name or value
  def, val

- Blocks { }

- @tailrec

- Functional languages treat functions as a first class value. That means that like any
other value function can be passed as a parameter or as a result.
Functions that take other functions as parameters or return a function are called "high order functions"

- Anonymous functions.
(x: Int) => x * x

- Currying

- Classes
class Rationale( x:Int, y:Int){
  val numx = x
  val numy = y

  new Rationale(3.7)

  this

  data abstraction

  second constructor
  this( x: Int) = this(x, 1)

- infix notation

r add s equals r.add(s)

- Relaxed Identifiers
  for example instead of less name function we can use <

  with unary operators you have to use

  def unary_- : Rationale =  new Rationale(-number, denom)

- class hierarchy
}


abstract class IntSet {

  def incl(x: Int): IntSet
  def contains(x: Int): Boolean

}

class Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty(x, new Empty, new Empty)
}

object Hello{
 def main(args: Array[String]) = println("Hello World")
}


- packages
  import

  default package scala, java.lang, scala.predef
  scala-lang.org/api/current

-  trait == interface in Java

traits can have concrete methods and fields but can't have parameters like in classes.
     Any
AnyVal AnyRef

Nothing - Tp signal abnormal termination
As an element of empty collection

def error( msg: String) = throw new Error(msg)

- throw Exc

- null
for AnyRef not applicable to AnyVal

- type parameters
class[T]
val is evaluated when the object gets initialized, def is evaluatedf every time it gets referenced.
def singleton[T](element: T)


- Polymorphism
subtyping: instances of a subclass can be passed to a base class
generics: instance of a function or class are created by type parameterization

- Function types
A => B is the same as scala.Function1[A,B]

trait Function1[A,B]{
  def apply(X: A): B
}

- Function values

(x: Int) => x * x

{ class AnonFun extends Function[Int, Int] {
  def apply(x: Int) = x * x
}
  new AnonFun

}

or a shorter way

new Function1[Int, Int] {
  def apply(x: Int) = x * x
}

- Function calls

A function call, such as f(a,b) can be expand to:

f.apply(a,b)

val f = new Function1[Int, Int] {
  def apply(x: Int) = x * x
}

- bounds

def assertAllPos[ S <: IntSet](r: S): S = ....

Here "<: IntSet" is an upper bound of the type parameter S:

S <: T means: S is a subtype of T, and
S >: T means: S is a supertype of T, or T is a subtype of S.

[S >: NonEmpty] lower bound. So S could be one of NonEmpty, IntSet, AnyRef, ort Any

[S >: NonEmpty <: IntSet] Mixed Bounds

- variance

List[NonEmpty] <: List[IntSet]

We call types for which this relationship holds covariant because their
subtyping relationship varies with the type parameter.

- Liskov principle

if A <: B, then everything one can do with a value of type B
one should also be able to do with a value of type A.

- Variance
C[A] <: C[B]  C is covariant
C[A] >: C[B]  C is contravariant
neither C[A] nor C[B] is a subtype of the other C is not variant

Scala lets you declare the variance of a type by annotating the type parameter

class C[+A] { .... }  C is covariant
class C[-A] { .... }  C is contravariant
class C[A] { .... }   C is not variant

Functions are contravariant in their argument type(s) and and covariant in their result type

trait Function[-T, +U]{
  def apply(x: T): U
}

- Decomposition-Trying to find a general and convenient way to access objects in a extensible class hierarchy.
Scala
x.isInstanceOf
x.asInstanceOf

Java
x instanceOf
(T) x

- Functional Decomposition with pattern matching
trait Expr
case class Number(n: Int)

def eval(e: Expr): Int = e match {
  case Number(n) => n
  case Sum(e1, e2) => eval(e1) + eval(e2)

  variables always start wuth lower
  constants with Upper

- Collections
List Immutable, recurrsive, Array flats
val empty = List()
val fruit = List("apples", "oranges", "pears")
Construction operator :: (pronounce cons)
fruit = "apples" :: ("oranges" :: ("pears" :: Nil ))
head the first element of the list
tail the list of all elemts but the first
isEmpty
List Patterns
p :: ps A patter that matches a list with a head matching p and a tail matching ps

- Vectors
Of 32 elements. It is like ashort ttree
Faster access

val nums = Vector(1,2,3)
x +: xs
xs :+ x

xs = (1,2,3) zip ("Hello") (1,H) (2,e) (3,l)
xs.unzip (1,2,3) ("Hel")

"Hello" flatMap (c => (".", c) = .H.e.l.l.o

sum prod max min



- Seq


            Iterable

   Seq         Set      Map

List  Vector

exists, forall, zip, unzip

- for( s ) yield e

- Set
(1 to 8 by 2).toSet
Sets are unordered
sets don not duplicate elements
Fundamental operation on sets is contains

- Queries with for
case class Book(title: String, authors: List[String])

val books: List[Book] = List(
Book( title = "My Book", authors = List("John Mane", Thomas")

for( b <- books; a <- b.authors if a startsWith "Birds" ) yield b.title

for( b <- books; a <- b.title indexOf "Program" >= 0 ) yield b.title

{for{
  b1 <- books
  b2 <- books
  if b1 != b2 && b1.title < b2.title
  a1 <- b1.authors
  a2 <- b2.authors
  if a1 == a2

} yield a1}.distinct

- For-Expressions and Higher-Order Functions
The syntax of for is closely related to map, flatMap and filter

def mapFun[T, U](xs: List[T], f: T => U): List[U] =
  for(x <- xs) yield f(x)

def flatMap[T, U](xs: List[T], f: T => Iterable[U]): List[U] =
  for(x <- xs; y <- f(x)) yield y

def filter[T](xs: List[T], p: T => Boolean): List[T] =
  for( x<- xs if p(x)) yield x

for( x <- e1) yield e2 is equal e1.map(x => e2)

for( x<- e1 if f; s) yield e2
for( x <- e1.withFilter(x => f); s) yield e2

for( x <- e1; y <- e2; s) yield e3

e1.flatMap(x => for ( y <- e2; s) yield e3)

- Map[Key, Value]
val romanNUmerals = Map("I" -> 1, "V" -> 5, "X" -> 10 )
foldLeft

- import scala.io.Source
val in = Source.fromURL("http://lamp.epfl....")

- streams
Are similar to lists, but their tail is evaluated only on demand
Are defined from a constant Stream.empty and Stream.cons
val xs = Stream.cons(1, Stream.cons(2, Stream.empty))

Stream(1, 2, 3)
(1 to 1000).toStream

x :: xs always produces a list, never a stream
However

x #:: xs == Stream.cons(x, xs)

- Lazy evaluation
If tail is called several times, the corresponsing stream will be recouputed each time.
This problem can be avoided by storing the result of the first eval of tsil and reusesng
the stored result instead of recoumputing tail.
We call this schema lazy evaluation (as opposed to by-name evaluation where everything is computed and
strict evaluation for normal parameters and val definitions)

9.4
Streams, lazy



*/

object demo {

  def Caitals = {

    val capitalOfTheCountry = Map("Paris" -> "France", "Washington" -> "DC")

    def showCapitalOfTheCountry(country: String) = capitalOfTheCountry get country match {
      case Some(x) => println(x)
      case None => println("Not found")
    }

    val fruit = List("apple", "pear", "orange")

    fruit sortWith ( _.length < _.length)

    fruit sorted

    capitalOfTheCountry.toList.sorted.reverse

  }

  def myMapsFunc = {

    def mapFun[T, U](xs: List[T], f: T => U): List[U] =
      for (x <- xs) yield f(x)

    def flatMap[T, U](xs: List[T], f: T => Iterable[U]): List[U] =
      for (x <- xs; y <- f(x)) yield y

    def filter[T](xs: List[T], p: T => Boolean): List[T] =
      for (x <- xs if p(x)) yield x

  }

  case class WordFreq(word: String, count: Int) {
    override def toString = s"Word <$word> occurs with frequency $count"
  }

  class SimpleParser extends RegexParsers {
    def word: Parser[String]   = """[a-z]+""".r       ^^ { _.toString }
    def number: Parser[Int]    = """(0|[1-9]\d*)""".r ^^ { _.toInt }
    def freq: Parser[WordFreq] = word ~ number        ^^ { case wd ~ fr => WordFreq(wd,fr) }
  }

  object TestSimpleParser extends SimpleParser {
    def myparser = {
      parse(freq, "johnny 121") match {
        case Success(matched,_) => println(matched)
        case Failure(msg,_) => println(s"FAILURE: $msg")
        case Error(msg,_) => println(s"ERROR: $msg")
      }
    }
  }

  def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double ={
    (xs zip ys).map{ case (x,y) => x * y}.sum
    // (for( (x,y) <- xs zip ys ) yield x * y).sum
  }

  def  doCurr() {

    //def sum( f:Int=>Int)( a: Int, b: Int ) : Int =
    //function is type (Int => Int) => (Int, Int) => Int
    def sum(f: Int => Int): (Int, Int) => Int = {

      def sumF(a: Int, b: Int): Int =
        if (a > b) 0
        else f(a) + sumF(a + 1, b)

      sumF

    }

    def cube = (x: Int) => (x * x * x)

    def sumInts = sum(x => x)

    def sumCubes = sum(x => x * x * x)

    val r = sum(cube)(5, 7)

    println(r)

    sumInts(3, 5)
    sumCubes(2, 7)


  }

  def doStringInterpolation: Unit ={

    //String inerpolation
    val name = "mark"
    val age = 18.5
    println("Hello, world!")
    println( name + " is " + age + " years old")
    println(s"$name is $age years old")
    println(f"$name%s is $age%f years old")
    println(s"$name  is\n$age years old")
    println(raw"$name  is\n$age years old")
  }

  def doIfElse: Unit ={

    // **** if else ****
    val x = 20;
    val y = 10;

    if( x == 20 ){
      println("X == 20")
    }else{
      println("x != 20 ")
    }

    var res = if( x == 20 && y == 10) "x == 20" else "x != 20"
    println(res)

  }

  def doWhile: Unit ={

    // **** While loop ****
    var w = 0;

    while( w < 10){
      println(s"w:$w");
      w+=1;
    }

    w = 0
    do{
      println(s"w:$w")
      w+=1
    }while(w < 0)

  }


  def doFor{
    // ***** for loop *****
    for( i <- 1 to 5){
      println(s"i using $i")
    }

    // <- the arrow is called generator
    // 1.to?(5) is called range
    for( i <- 1.to(5)){
      println(s"i using $i")
    }

    for( i <- 1 until 6){
      println(s"i until $i")
    }

    for( i <- 1.until(6)){
      println(s"i until $i")

    }

    for( i <- 1 to 5; j <- 1 to 2){
      println(s"i=$i, j=$j")
    }

    val lst = List(1,2,3,4,5,6,7,8,9,11,12)

    for( i <- lst ){
      println(s"i=$i")
    }

    for( i <- lst; if i < 6 ){
      println(s"i=$i")
    }

    var res1 = for{ i <- lst; if i < 6 } yield { i * i }
    println("result:" + res1)

  }

  def doMatch: Unit ={
    // **** match ****
    val edad = 18

    edad match {
      case 10 => println(edad)
      case 18 => println(edad)
      case 20 => println(edad)
      case _  => println("default")
    }

    val res2 = edad match {
      case 10 => edad
      case 18 => edad
      case 20 => edad
      case _  => println("default")
    }

    println(res2)

    var k = 1
    k match {
      case 1 | 3 | 5 | 7 => println("odd number")
      case 2 | 4 | 6 | 8 => println("even number")
    }
  }

  def doString: Unit ={

    // Strings
    val str1 : String = "Hello Scala"
    val str2 : String = " Max"
    val num1 = 75;
    val num2 = 100.25
    println(str1.length)
    println(str1.concat(str2))
    println(str1 + str2)

    val res3 = printf( "%d -- %f -- %s", num1, num2, str1)
    println(res3)
    println("%d -- %f -- %s".format(num1, num2, str1))
    var str3 : String = "%d -- %f -- %s".format(num1, num2, str1)
    println("str3:" + str3)

  }

  def doArrays: Unit ={
    // **** Arrays ****
    var myarray1 : Array[Int] = new Array[Int](4)
    var myarray2 = new Array[Int](3)
    val myarray3 = Array(1, 2, 3, 4, 5)


    myarray1(0)=10
    myarray1(1)=20
    myarray1(2)=30
    myarray1(3)=40

    myarray2(0)=50
    myarray2(1)=60
    myarray2(2)=70

    val conres = concat(myarray1, myarray2)

    for( i <- myarray1 ){
      println(i)
    }

    for( x <- 0 to myarray2.length - 1){
      println(myarray2(x))
    }

    for( i <- myarray3 ){
      println(i)
    }

    for( i <- conres ){
      println("conres:" + i)
    }

  }

  def doLists: Unit ={
    // **** List ****
    val numList : List[Int] =  List(1,2,3)
    val nameList : List[String] = List("One", "Two", "Three")

    println(numList)
    println(nameList)

    println(0 :: numList)
    println( 1 :: 2 :: 3 :: Nil )

    println(numList.head)
    println(numList.tail)
    println(nameList.reverse)
    println(List.fill(4)(7))
    println(numList.max)

    numList.foreach(println)
    var sum = 0;
    numList.foreach( sum+=_)
    println("sum:" + sum)

    for( name <- nameList){
      println(name)
    }
  }

  def doSets: Unit ={
    // **** Sets ****

    val myset1 : Set[Int] = Set(1,2,3,4,4)
    val myset2 : Set[Int] = Set(4,5,7,8,9)

    //concatenate
    println(myset1 ++ myset2)
    println(myset1.++(myset2))
    var myset3 = scala.collection.mutable.Set(5,6,7,8)
    myset3.add(10)

    println(myset3)

    //intersaction
    println(myset1.&(myset2))
    println(myset1.intersect(myset2))

    myset1.foreach(println)

    for( num <- myset1){
      println(num)
    }

  }

  def doMaps: Unit ={
    // **** Maps ****
    val myMap : Map[Int,String] = Map(801 -> "max",
      802 -> "Tom",
      803-> "Mary")

    val myMap1 : Map[Int,String] = Map(805 -> "ma",
      806-> "To",
      807-> "Mar")
    println(myMap)
    println((myMap(802)))
    println(myMap.keys)
    println(myMap.values)
    println(myMap.isEmpty)

    myMap.keys.foreach{ k =>
      println("key:" + k )
      println("Val:" + myMap(k))
    }
    println(myMap.contains(2233))

    val str = myMap get 801
    println( str )

    println( myMap ++ myMap1)

  }

  def doTuples: Unit ={
    // **** Tuples ****
    val myTuples = ( 1, 2, "hello", true )
    println(myTuples)

    val myTuples1 = new Tuple3(1, 2, "hello")
    val myTuples2 = new Tuple2(1, 2)
    val myTuples3 = ( 1, "hello", (2,3))

    println(myTuples._3)
    println(myTuples1._1)

    myTuples.productIterator.foreach(
      i => println(i)
    )

    //The next line only creates a tuple of two
    println( 1 -> "Tom")

    println(myTuples3._3._2)
  }

  def doOption: Unit ={
    // **** Options ****
    val lst5 = List(1, 2, 3)
    val map5 = Map( 1-> "Tom", 2 -> "Max", 3 -> "John")

    println(lst5.find(_ > 2).getOrElse("Number not found"))
    println(map5.get(2).getOrElse("Key not found"))

    val opt : Option[Int] = Some(55)
    val opt1 : Option[Int] = None

    println(opt.get)
    println(opt1.isEmpty)
  }

  def doRegExp: Unit ={
    // **** Regular Expressions

    val pattern = new Regex("(S|s)cala")
    val str7 = "Scala is scalable and cool"

    println((pattern findAllIn str7).mkString(","))

    val pattern1 = new Regex("abl[ae]\\d+")
    val str8 = "ablaw is able1 and cool"

    println((pattern1 findAllIn str8).mkString(","))
  }

  def doFunctions {
    // **** Functions ****
    //With default values
    def add(x: Int = 10, y: Int = 5): Int = {
      return x + y;
    }

    //last line of the function is the return value
    def substract(x: Int, y: Int): Int = {
      x - y;
    }

    //WWhen the function is too short we can use one line
    def multiply(x: Int, y: Int): Int = x * y;

    //When we know the return value
    def devide(x: Int, y: Int) = x / y;

    println("Calling add function:" + add(1, 7))
    println("Calling sub function:" + substract(1, 7))
    println("Calling mul function:" + multiply(1, 7))
    println("Calling dev function:" + devide(1, 7))

    //A function that doesn't return any value
    def printme(x:Int, y:Int): Unit = {
      println(x+y)
    }

    // **** Anonymous functions ****
    var func = (x:Int, y:Int) => x + y

    // **** High order functions ****
    def foo(x:Double, y:Double, f:(Double, Double)=>Double):Double = f(x,y)

    def foo1(x:Double, y:Double, z:Double, f:(Double, Double)=>Double):Double = f(f(x,y),z)

    // **** Partially applied functions ****
    var sum = (x:Int, y:Int, z:Int) => x + y + z

    var f = sum( 24, 10, _:Int)

    val log = (date:Date, message:String) => {
      println(date + " " + message)
    }

    val newLog = log( new Date(), _:String)

    //Anonymous functions call
    println(func(100,200))

    //High order functions
    println( foo(12,14, (x:Double,y:Double)=>x*y))

    println( foo1(12,14, 10, (x:Double,y:Double)=>x*y))

    //A different form to write the anonymous function
    println( foo(12,14, _*_))
    println( foo1(12,14,10, _*_))

    println(f(30))

    newLog("Message One")
    newLog("Message Two")
    newLog("Message Three")

  }

  def doClosure: Unit ={
    // **** Closures ****
    // Pure closure when the external variable is val (immuntable)
    // unpure closure when the external variable is var
    var number = 10
    val ad = (x:Int) => {
      number = x + number
      number
    }
    println(ad(20))
  }

  def doCurrying: Unit ={
    // **** Currying ****
    //It is a technique of transforming a function that takes multiple arguments into a function into
    //a function that takes a single argument
    def add0(x:Int, y:Int) = x + y
    def add1(x:Int) = (y:Int)=> x+y
    def add3(x:Int)(y:Int) = x + y

    val sum1 = add3(20)_
    println("sum1:" + sum1(20))
    println(add1(10)(20))

  }

  object Math{

    def add(x:Int, y:Int):Int={
      return x + y;
    }

    def square(x:Int) = x * x

    def +(x:Int, y:Int):Int={
      return x + y;
    }

    def **(x:Int) = x * x

  }

  def doOper: Unit ={
    println(Math.add(1,7))

    //Sugar notation, when there is only one argument
    //similar to 1 to 5, 1 until 5
    println(Math square 3)

    println(Math ** 3)

    println(Math.+(3,2))
  }

  def doIterator: Unit ={

    val it = Iterator("a", "number", "of", "words")

    while (it.hasNext){
      println(it.next())
    }

    val ita = Iterator(20,40,2,50,69, 90)
    val itb = Iterator(20,40,2,50,69, 90)

    println("Maximum valued element " + ita.max )
    println("Minimum valued element " + itb.min )

  }

  trait Equal {
    def isEqual(x: Any): Boolean
    def isNotEqual(x: Any): Boolean = !isEqual(x)
  }

  class Point1(xc: Int, yc: Int) extends Equal {
    var x: Int = xc
    var y: Int = yc

    def isEqual(obj: Any) = obj.isInstanceOf[Point] && obj.asInstanceOf[Point].x == y
  }

  def doTraits: Unit ={
    val p1 = new Point1(2, 3)
    val p2 = new Point1(2, 4)
    val p3 = new Point1(3, 3)

    println(p1.isNotEqual(p2))
    println(p1.isNotEqual(p3))
    println(p1.isNotEqual(2))
  }

  def doRegularExp: Unit ={
    val pattern = new Regex("(S|s)cala")
    val str = "Scala is scalable and cool"

    println((pattern findAllIn str).mkString(","))

    val pattern1 = new Regex("abl[ae]\\d+")
    val str1 = "ablaw is able1 and cool"

    println((pattern findAllIn str).mkString(","))
  }

  def doYield: Unit ={

    val l1 = List(1,2)
    val l2 = List(3,4)

    val res = for( f1 <- l1; f2 <- l2) yield { f1*f1*f2 }

    println(res)

  }

  def doFlat: Unit = {
    val re = List( List(1,2), List(3,4), List(5,6)).flatten.flatMap{x=>List(x,x+1)}
    List(1,4,9).flatMap { x => List(x,x+1) }
  }

  def doMapMatch: Unit = {
    Map("key1" -> 1, "key2" -> 2).map { keyValue:(String,Int) =>
      keyValue match { case (key, value) => (key, value*2) }
    }
    "Hello".map(c => c.toUpper)
    "Hello".map(_.toLower)
  }

  class Point(xc: Int, yc: Int) {
    val x: Int = xc
    val y: Int = yc
    def move(dx: Int, dy: Int): Point =
      new Point(x + dx, y + dy)
  }
  class ColorPoint(u: Int, v: Int, c: String) extends Point(u, v) {
    val color: String = c
    def compareWith(pt: ColorPoint): Boolean =
      (pt.x == x) && (pt.y == y) && (pt.color == color)
    override def move(dx: Int, dy: Int): ColorPoint =
      new ColorPoint(x + dy, y + dy, color)
  }

  def doException: Unit ={
    try {
      val f = new FileReader("input.txt")
    } catch {
      case ex: FileNotFoundException => {
        println("Missing file exception")
      }

      case ex: IOException => {
        println("IO Exception")
      }
    } finally {
      println("Exiting finally...")
    }

  }

  class Rational(x: Int, y: Int) {

    require( y != 0, "denominator must be nonzero")

    //second constructor
    def this(x: Int) = this(x, 1)
    private def gcd(a:Int,b:Int): Int = if(b==0) a else gcd(b, a%b)
    private val g = gcd(x,y)
    val numer = x /g
    val denom = y / g
    def add(r: Rational) =
      new Rational(numer * r.denom + r.numer * denom, denom * r.denom)

    def less(that: Rational) = numer * that.denom < that.numer * denom

    //Operators can be identifiers
    def < (that: Rational) = numer * that.denom < that.numer * denom

    def max(that: Rational) = if(this.less(that)) that else this
    def neg: Rational = new Rational(-numer, denom)
    def unary_- : Rational = new Rational(-numer, denom)


    override def toString = s"$numer/$denom"
  }

  def doFractions: Unit ={
    val frac1 = new Rational(1,2)
    val frac2 = new Rational(2,3)

    val fracRes = frac1.add(frac2)
    println(fracRes.toString)

  }

  abstract class IntSet{
    def incl(x :Int): IntSet
    def contains(x: Int): Boolean
  }

  //  class Empty extends IntSet{
  //
  //    def contains(x:Int): Boolean = false
  //    def incl(x:Int): IntSet = new NonEmpty(x, new Empty, new Empty)
  //
  //  }

  object FileMatcher{
    def fileList = (new File(".")).listFiles()

    def filesMatching(matcher: (String)=>Boolean) ={
      for( file<-fileList; if matcher(file.getName) ) yield file
    }

    def filesEnding(query: String) =
      filesMatching( _.endsWith(query))

    def filesEnding1( query: String) = filesMatching( c => c.endsWith(query))

    def filesContaining(query: String) =
      filesMatching( (fileName: String)=>fileName.contains(query))

  }


  def doJson(): Unit = {
    case class meminfo( total: Int, free: Int, buffers: Int)
    case class host(device_id: String, system_id: String, platform: String, uptime: String, time: String, utime: String, hostname: String, fwversion: String, loadavg: List[Double], meminfo: meminfo, cfgcrc: String)
    case class status( host: host )
    case class Version( host: String, device_id: String, uptime: String, fwversion: String)
    case class Config( status: status)
    object MyJsonProtocol extends DefaultJsonProtocol{
      implicit val memFormat: RootJsonFormat[meminfo] = jsonFormat3(meminfo)
      implicit val hostFormat: RootJsonFormat[host] = jsonFormat11(host)
      implicit val statFormat: RootJsonFormat[status] = jsonFormat1(status)
      implicit val configFormat: RootJsonFormat[Config] = jsonFormat1(Config)
    }



    val jstr = """{ "status": {
                      "host" : {
                          "device_id": "04:18:D6:07:89:09",
                          "system_id": "0xe702",
                          "platform": "EdgeSwitch 8XP",
                          "uptime": "5945235",
                          "time": "2018-05-23 08:26:09",
                          "utime": "1527063969",
                          "hostname": "AGT3WBG-SW02",
                          "fwversion": "SW.ar7240.v1.4.1.32323.180315.1259",
                          "loadavg": [ 0.02, 0.01, 0.00 ],
                          "meminfo": {
                            "total": 62036,
                            "free": 49836,
                            "buffers": 276
                          },
                          "cfgcrc": "61898825"
                      }
                  }
              }"""

    import MyJsonProtocol._
    //val verStr = Version("12234", "33454", "sw.4.5.3").toJson
    //val v = verStr.convertTo[Version]
    //println(v.fwversion)

    val jsonAst = JsonParser(jstr)
    val statuss = jsonAst.convertTo[Config]
    println(statuss)

    //val json = jsonAst.prettyPrint
    //val verObj = jsonAst.asJsObject.convertTo[Version]
    //println(verObj.fwversion)

  }

  def doJason1: Unit = {
    case class brmacs(bridge:  String, port: String, hwaddr: String, ageing: String)
    case class addrs(brmacs: List[brmacs])
    object LoaderProtocol extends DefaultJsonProtocol {
      implicit val brmacsFormat: RootJsonFormat[brmacs] = jsonFormat4(brmacs)
      implicit val configFormat: RootJsonFormat[addrs] = jsonFormat1(addrs)
    }

    val jstr =
      """
        |{ "brmacs": [
        |{"bridge":"br0","port":"eth1","hwaddr":"00:0c:29:0b:4c:cf","ageing":"0.61"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:0c:29:50:6c:1a","ageing":"24.97"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:0c:29:53:d6:e6","ageing":"0.00"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:0c:29:75:bc:ac","ageing":"0.17"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:0c:29:cd:e9:24","ageing":"40.43"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:0c:29:d7:2a:26","ageing":"2.37"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:0c:29:eb:28:8a","ageing":"0.96"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:0c:29:f6:50:9d","ageing":"20.92"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:0c:29:f7:f7:d5","ageing":"5.35"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:0c:29:ff:d1:62","ageing":"0.81"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:12:d9:86:9a:b4","ageing":"29.86"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:15:5d:28:c6:00","ageing":"0.03"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:15:5d:28:c6:08","ageing":"10.93"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:17:c5:18:9d:1d","ageing":"17.61"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:1b:0d:49:cb:b4","ageing":"15.82"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:1b:0d:59:08:b4","ageing":"2.99"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:1b:ed:7c:e1:44","ageing":"1.28"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:1d:45:dc:b7:b4","ageing":"8.96"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:1d:aa:89:55:08","ageing":"59.20"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:50:56:a0:49:10","ageing":"2.61"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:50:56:a0:db:13","ageing":"0.96"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:a0:c8:2a:ba:84","ageing":"298.72"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:c0:b7:f2:8c:db","ageing":"50.99"},
        |{"bridge":"br0","port":"eth1","hwaddr":"00:c0:b7:f2:8f:bb","ageing":"61.13"},
        |{"bridge":"br0","port":"eth1","hwaddr":"18:b1:69:86:36:71","ageing":"59.47"},
        |{"bridge":"br0","port":"eth1","hwaddr":"18:ef:63:8a:d8:97","ageing":"4.22"},
        |{"bridge":"br0","port":"eth1","hwaddr":"2c:56:dc:92:48:94","ageing":"3.71"},
        |{"bridge":"br0","port":"eth1","hwaddr":"80:e8:6f:a5:c7:c4","ageing":"0.18"},
        |{"bridge":"br0","port":"eth1","hwaddr":"e0:55:3d:d1:73:f0","ageing":"2.23"},
        |{"bridge":"br0","port":"eth1","hwaddr":"e0:55:3d:d1:73:f3","ageing":"0.37"},
        |{"bridge":"br0","port":"eth1","hwaddr":"ec:b1:d7:ac:bb:51","ageing":"1.95"},
        |{"bridge":"br0","port":"eth1","hwaddr":"fc:a8:41:5b:48:01","ageing":"3.47"},
        |{"bridge":"br0","port":"eth1","hwaddr":"fc:a8:41:5b:48:42","ageing":"112.93"}]}
    """.stripMargin

    import LoaderProtocol._

    val jsonAst =  JsonParser(jstr)
    //val statuss = jsonAst.convertTo[addrs]
    val statuss = Try { JsonParser(jstr).convertTo[addrs] }.toOption

    //println(statuss)
    statuss match {
      case Some(stat) =>
        stat.brmacs.foreach( addr => println(addr.hwaddr) )
        for{ addr <- stat.brmacs} yield { println(addr.hwaddr)}
      case _ =>
        println("Nothing to parse")
    }


  }

  def doParsing(): Unit ={
    case class IntVersion(majorV: Int, minorV: Int, releaseCode: Int)

    def parseVersionFromFirmware(firmware: String): Option[IntVersion] = {
      val r = """.+\.v(\d+)\.(\d+)\.(\d+)\..+""".r
      firmware match {
        case r(major, minor, release) => Some(IntVersion(major.toInt, minor.toInt, release.toInt))
        case _                        => None
      }
    }
    val ver = parseVersionFromFirmware("SW.ar7240.v1.4.1.32323.180315.1259")
    print(ver.get.majorV)

  }

  def expandPorts( portsLine: Option[String] ): Option[String] = {

    def parsePort(portStr: String): String = {
      val re = """(\w+)\/(\d+)-(\d+)""".r
      portStr match {
        case re(name, min, max) => expandPort(name, min.toInt, max.toInt)
        case _ => portStr
      }
    }
    def expandPort( name: String, min: Int, max: Int): String = {
      val ep = for( i <- min to max) yield { "%s/%d".format(name,i) }
      ep.mkString(",")
    }

    portsLine match {
      case Some(ports) => {
        val ep = for( port <- ports.split(" ") ) yield { parsePort(port) }
        Some(ep.mkString(","))}
      case None => None
    }
  }

  def expandPortsMap( portsLine: String ): String = {

    def parsePort(portStr: String): String = {
      val re = """(\w+)\/(\d+)-(\d+)""".r
      portStr match {
        case re(name, min, max) => expandPort(name, min.toInt, max.toInt)
        case _ => portStr
      }
    }
    def expandPort( name: String, min: Int, max: Int): String = {
      val ep = for( i <- min to max) yield { "%s/%d".format(name,i) }
      ep.mkString(",")
    }

    val res = portsLine.split(" ").map( port => parsePort(port)).mkString(",")

    res
  }



  trait User {
    def name: String
    def score: Int
  }
  class FreeUser(val name: String, val score: Int, val upgradeProbability: Double)
    extends User
  class PremiumUser(val name: String, val score: Int) extends User

  object FreeUser {
    def unapply(user: FreeUser): Option[(String, Int, Double)] =
      Some((user.name, user.score, user.upgradeProbability))
  }
  object PremiumUser {
    def unapply(user: PremiumUser): Option[(String, Int)] = Some((user.name, user.score))
  }

  trait Host{
    //    def tenant: Option[String]
    //    def device : Option[Long]
    //    def devport : Option[Int]
    //    def system: String
    //    def port : Option[String]
    def tenant: String
    def device : Long
    def devicePort : Int
    def system: String
    def port : String
  }

  class Hostname(val tenant: String, val device: Long, val devicePort: Int, val system: String, val port: String) extends Host

  object Hostname{

    def apply(tenant: String, device: Long, devicePort: Int, system: String, port: String): Unit = {
      new Hostname(tenant, device, devicePort, system, port)
    }

    def unapply( hostname: Hostname ) : Option[(String, Long, Int, String, String)] =
      Some(hostname.tenant, hostname.device, hostname.devicePort, hostname.system, hostname.port)

  }

  def doApp = {
    //    val hostname = Hostname("tes1", 123456789, 4343, "my.auvik.com", "9000")
    //
    //    val Hostname(t, d, dp, s, p) = hostname
    //
    //    println( t )
    //    println( d )
    //    println( dp)
    //    println( s )
    //    println( p )
  }

  def main(args: Array[String]) {

    //    doStringInterpolation
    //    doIfElse
    //    doWhile
    //    doFor
    //    doMatch
    //    doString
    //    doArrays
    //    doLists
    //    doYield
    //    doSets
    //    doMaps
    //    doTuples
    //    doOption
    //    doFunctions
    //    doCurrying
    //    doCurr
    //    doOper
    //    doIterator
    //    doTraits
    //    doRegularExp
    //    doException
    //    doFractions
    //    doParsing
    //    doJson
    //    doJason1
    //println(expandPorts(Some("FE1/0-3 FE1/6 GE1/1-8")))
    //println(expandPortsMap("FE1/0-3 FE1/6 GE1/1-8"))

    //TestSimpleParser.myparser
    doApp
  }

}

