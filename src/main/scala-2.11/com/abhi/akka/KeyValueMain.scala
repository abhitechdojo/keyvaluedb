package com.abhi.akka

import akka.actor.{Props, ActorSystem}
import com.abhi.akka.actors.KeyValueActor

object Main extends App {
  val system = ActorSystem("keyvalue")
  system.actorOf(Props[KeyValueActor], name = "keyvalue-db")
}