package com.abhi.akka.test

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import com.abhi.akka.actors.KeyValueActor
import org.junit.Test
import org.scalatest.{BeforeAndAfterEach, Matchers, FunSpecLike}
import com.abhi.akka.messages.SetRequest

/**
  * Created by abhishek.srivastava on 1/10/16.
  */

class KeyValueActorTest extends FunSpecLike with Matchers with BeforeAndAfterEach {
  describe("keyvalueactor") {
    describe("given SetRequest") {
      it("should store key/value in a map") {
        implicit val system = ActorSystem()
        val actorRef = TestActorRef(new KeyValueActor)
        actorRef ! SetRequest("foo", "bar")
        val underlyingActor = actorRef.underlyingActor
        underlyingActor.map.get("foo") should equal ("bar")
      }
    }
  }
}