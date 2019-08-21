import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.Props

object SimpleActorExample extends App {
  class SimpleActor extends Actor {
    def receive = {
      case s: String => println("Hi:: "+ s )
    }
    def foo=println("Normal method")
  }
  val system = ActorSystem("SimpleSystem")
  val actor = system.actorOf(Props[SimpleActor], "SimpleActor")

  actor ! "Hi there"
}