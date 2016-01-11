package com.abhi.akka.actors

import java.util

import akka.actor.{Actor, Status}
import akka.event.Logging
import com.abhi.akka.messages.{KeyValue, GetRequest, SetRequest}

/**
  * Created by abhishek.srivastava on 1/10/16.
  */
class KeyValueActor extends Actor {
  val map = new util.HashMap[String, Object]
  val log = Logging(context.system, this)

  override def receive = {
    case SetRequest(key, value) => {
      log.info(s"received set request key ${key} value ${value}")
      map.put(key, value)
      sender() ! Status.Success
    }
    case GetRequest(key) => log.info(s"recieved get request ${key}")
      sender() ! KeyValue(map.get(key))
    case _=> log.info("unknown message")
  }
}
