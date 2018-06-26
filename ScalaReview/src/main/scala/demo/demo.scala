package demo
import java.io.{File, FileNotFoundException, FileReader, IOException}
import java.util.Date

import scala.Array._
import scala.util.matching.Regex

object demo {

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

  def main(args: Array[String]) {

    doStringInterpolation
    doIfElse
    doWhile
    doFor
    doMatch
    doString
    doArrays
    doLists
    doSets
    doMaps
    doTuples
    doOption
    doFunctions
    doCurrying
    doOper
    doIterator
    doTraits
    doRegularExp
    doException
    doFractions


  }

}
