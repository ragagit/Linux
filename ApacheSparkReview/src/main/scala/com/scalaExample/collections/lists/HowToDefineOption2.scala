package com.scalaExample.collections.lists

object HowToDefineOption2 {
  def main(args: Array[String]): Unit = {

    // List of Strings
    val fruit = "apples" :: ("oranges" :: ("pears" :: Nil))

    // List of Integers
    val nums = 1 :: (2 :: (3 :: (4 :: Nil)))

    // Empty List.
    val empty = Nil

    // Two dimensional list
    val dim = (1 :: (0 :: (0 :: Nil))) ::
      (0 :: (1 :: (0 :: Nil))) ::
      (0 :: (0 :: (1 :: Nil))) :: Nil

  }
}
