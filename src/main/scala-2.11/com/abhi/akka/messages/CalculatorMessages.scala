package com.abhi.akka.messages

/**
  * Created by abhishek.srivastava on 1/10/16.
  */
case class Add(i : Int, j: Int)
case class Result(i: Int)
case class Subtract(i: Int, j: Int)
case class Multiply(i: Int, j: Int)