package com.abhi.akka.test

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import com.abhi.akka.actors.{PingActor, PongActor, KeyValueActor}
import com.abhi.akka.messages.{Ping, SetRequest}
import org.scalatest.{FunSpecLike, BeforeAndAfterEach, Matchers}

/**
  * Created by abhishek.srivastava on 1/10/16.
  */
class PingPongTest extends FunSpecLike with Matchers with BeforeAndAfterEach{
  describe("A Ping Actor") {
    describe("should play with Pong Actor") {
      it("a 100 times") {
        implicit val system = ActorSystem()
        val pongActorRef = TestActorRef(new PongActor)
        val pingActorRef = TestActorRef(new PingActor(pongActorRef))
        pingActorRef ! Ping()
        val underlyingActor = pingActorRef.underlyingActor
        underlyingActor.count should equal (100)
      }
    }
  }

}
