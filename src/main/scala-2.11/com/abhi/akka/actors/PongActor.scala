package com.abhi.akka.actors

import akka.actor.{Status, Actor}
import akka.event.Logging
import com.abhi.akka.messages.{Ping, Pong, Stop}

/**
  * Created by abhishek.srivastava on 1/10/16.
  */
class PongActor extends Actor {
  override def receive = {
    case Pong() => log.info("got pong message. will send ping message to sender")
      sender() ! Ping()
    case Stop() => context.stop(self)
    case _ => sender() ! Status.Failure(new Exception("Unknown message"))
  }
  val log = Logging(context.system, this)
}
