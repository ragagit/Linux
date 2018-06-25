package complex

case class Complex(real:Double, imag:Double) {
  def this(x:Double) = this(x,0)

  def +(c:Complex) = Complex(real+c.real,imag+c.imag)
  def -(c:Complex) = Complex(real-c.real,imag-c.imag)
  def *(c:Complex) = Complex(real*c.real-imag*c.imag,real*c.imag+imag*c.real)
  def /(c:Complex) = {
    val denom = c.real*c.real+c.imag*c.imag
    Complex((real*c.real+imag*c.imag)/denom,(imag*c.real-real*c.imag)/denom)
  }
  def magnitude = math.sqrt(real*real+imag*imag)
}

object Complex extends App {
  implicit def convert(x:Double) = Complex(x,0)

  val a = Complex(1,2)
  val b = Complex(3,4)

  println(a+b)
  println(a-b)
  println(a*b)
  println(a/b)
  println(3*a)

}