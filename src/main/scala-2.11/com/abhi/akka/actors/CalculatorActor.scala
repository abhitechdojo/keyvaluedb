package com.abhi.akka.actors

import akka.actor.{Status, Actor}
import akka.event.Logging
import com.abhi.akka.messages.{Result, Multiply, Subtract, Add}

/**
  * Created by abhishek.srivastava on 1/10/16.
  */
class CalculatorActor extends Actor {

  override def receive = {
    case Add(i, j) => log.info(s"got add message $i $j")
      sender() ! Result(i + j)
    case Subtract(i, j) => log.info(s"got subtract message $i $j")
      sender() ! Result(i - j)
    case Multiply(i, j) =>  log.info(s"got multiply message $i $j")
      sender() ! Result(i * j)
    case o => log.info("got unknown messge throwing exception")
      sender() ! Status.Failure(new Exception("Unknown Message"))
  }

  val log = Logging(context.system, this)
}
