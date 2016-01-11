package com.abhi.akka.test

import akka.actor.{Props, ActorSystem}
import akka.testkit.TestActorRef
import akka.util.Timeout
import com.abhi.akka.actors.CalculatorActor
import com.abhi.akka.messages._
import org.scalatest.{Matchers, BeforeAndAfterEach, FunSpecLike}
import akka.pattern.ask
import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by abhishek.srivastava on 1/10/16.
  */
class CalculatorActorTest extends FunSpecLike with Matchers with BeforeAndAfterEach {
  implicit val system = ActorSystem.create()
  val calcRef = system.actorOf(Props(classOf[CalculatorActor]))
  implicit val timeout= Timeout(5)

  describe("A calculator") {
    describe("when sent a message add") {
      import scala.concurrent.ExecutionContext.Implicits.global

      it("will add up the numbers") {
        (calcRef ? Add(10, 10)).onSuccess({
          case x : Result => assert(x.i == 20)
        })
      }

      it ("will multiply two numbers") {
        (calcRef ? Multiply(10, 10)).onSuccess({
          case x : Result => assert(x.i == 100)
        })
      }

      it ("will subtract two numbers") {
        (calcRef ? Subtract(20, 10)).onSuccess({
          case x: Result => assert(x.i == 10)
        })
      }

      it("will throw exception if bad messgae is passed") {
        (calcRef ? Ping()).onFailure({
          case e : Exception => assert(e.getMessage == "Unknown Message")
        })
      }
    }
  }

}
