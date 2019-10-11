package com.scalaExample.flowcontrols.loops.forloop

object MultipleRanges {
  def main(args: Array[String]) {
    var a = 0;
    var b = 0;

    // for loop execution with a range
    for (a <- 1 to 3; b <- 1 to 3) {
      println("Value of a: " + a);
      println("Value of b: " + b);
    }
  }
}
