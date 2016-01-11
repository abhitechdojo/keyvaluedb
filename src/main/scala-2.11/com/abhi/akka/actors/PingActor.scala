package com.abhi.akka.actors

import akka.actor._
import akka.event.Logging
import com.abhi.akka.messages.{Ping, Pong, Stop}

/**
  * Created by abhishek.srivastava on 1/10/16.
  */
class PingActor(pongActor : ActorRef) extends Actor {

  override def receive = {
    case Ping() => log.info("got ping message. will send pong message")
      if (count < 100) {
        count = count + 1
        pongActor ! Pong()
      }
      else
        self ! Stop()
    case Stop() =>
      log.info(s"got stop message. value of count is ${count}")
      pongActor ! Stop()
      //context.stop(self)
    case _ => sender() ! Status.Failure(new Exception("Unknown message"))
  }

  val log = Logging(context.system, this)
  var count = 0
}
