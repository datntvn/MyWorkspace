package com.inviqa.shoppingcart.impl

import java.time.LocalDateTime

import akka.Done
import com.lightbend.lagom.scaladsl.persistence.{AggregateEvent, AggregateEventTag, PersistentEntity}
import com.lightbend.lagom.scaladsl.persistence.PersistentEntity.ReplyType
import com.lightbend.lagom.scaladsl.playjson.{JsonSerializer, JsonSerializerRegistry}
import play.api.libs.json.{Format, Json}

import scala.collection.immutable.Seq

/**
  * This is an event sourced entity. It has a state, [[ShoppingcartState]], which
  * stores what the greeting should be (eg, "Hello").
  *
  * Event sourced entities are interacted with by sending them commands. This
  * entity supports two commands, a [[UseGreetingMessage]] command, which is
  * used to change the greeting, and a [[Hello]] command, which is a read
  * only command which returns a greeting to the name specified by the command.
  *
  * Commands get translated to events, and it's the events that get persisted by
  * the entity. Each event will have an event handler registered for it, and an
  * event handler simply applies an event to the current state. This will be done
  * when the event is first created, and it will also be done when the entity is
  * loaded from the database - each event will be replayed to recreate the state
  * of the entity.
  *
  * This entity defines one event, the [[GreetingMessageChanged]] event,
  * which is emitted when a [[UseGreetingMessage]] command is received.
  */
class ShoppingcartEntity extends PersistentEntity {

  override type Command = ShoppingcartCommand[_]
  override type Event = ShoppingcartEvent
  override type State = ShoppingcartState

  override def initialState: ShoppingcartState = ShoppingcartState(List.empty)

  /**
    * An entity can define different behaviours for different states, so the behaviour
    * is a function of the current state to a set of actions.
    */
  // BEGIN: DATNT REMOVED
  /*
  override def behavior: Behavior = {
    case ShoppingcartState(message, _) => Actions().onCommand[UseGreetingMessage, Done] {

      // Command handler for the UseGreetingMessage command
      case (UseGreetingMessage(newMessage), ctx, state) =>
        // In response to this command, we want to first persist it as a
        // GreetingMessageChanged event
        ctx.thenPersist(
          GreetingMessageChanged(newMessage)
        ) { _ =>
          // Then once the event is successfully persisted, we respond with done.
          ctx.reply(Done)
        }

    }.onReadOnlyCommand[Hello, String] {

      // Command handler for the Hello command
      case (Hello(name), ctx, state) =>
        // Reply with a message built from the current message, and the name of
        // the person we're meant to say hello to.
        ctx.reply(s"$message, $name!")

    }.onEvent {

      // Event handler for the GreetingMessageChanged event
      case (GreetingMessageChanged(newMessage), state) =>
        // We simply update the current state to use the greeting message from
        // the event.
        ShoppingcartState(newMessage, LocalDateTime.now().toString)

    }
  }*/
  // END: DATNT REMOVED
  // BEGIN: define handler for AddToCartCommand
  override def behavior: Behavior = {
    case ShoppingcartState(products) => Actions()
    .onCommand[AddToCartCommand, Done] {
      case (AddToCartCommand(product), context, state) =>
      context.thenPersist(
        AddedToCartEvent(product)
        ) { _ =>
        context.reply(Done)
        }
      }.onCommand[RemoveFromCartCommand, Done] {
        case (RemoveFromCartCommand(product), context, state) =>
          context.thenPersist(
            RemovedFromCartEvent(product)
          ) { _ =>
            context.reply(Done)
          }
      }.onReadOnlyCommand[ShowCartCommand.type, List[String]] {
        case (ShowCartCommand, context, state) => context.reply(state.products)
      }.onEvent {
        case (AddedToCartEvent(product), state) =>
        ShoppingcartState(product :: state.products)
        case (RemovedFromCartEvent(product), state) =>
        ShoppingcartState(state.products.filterNot(_ == product))
      }
    }
  }
  // END: define handler for AddToCartCommand

/**
  * The current state held by the persistent entity.
  */
// case class ShoppingcartState(message: String, timestamp: String) // DATNT: removed
// BEGIN: define event for AddToCartCommand
case class AddedToCartEvent(product: String) extends ShoppingcartEvent
case class RemovedFromCartEvent(product: String) extends ShoppingcartEvent
// END: define event for AddToCartCommand

object ShoppingcartState {
  /**
    * Format for the hello state.
    *
    * Persisted entities get snapshotted every configured number of events. This
    * means the state gets stored to the database, so that when the entity gets
    * loaded, you don't need to replay all the events, just the ones since the
    * snapshot. Hence, a JSON format needs to be declared so that it can be
    * serialized and deserialized when storing to and from the database.
    */
  implicit val format: Format[ShoppingcartState] = Json.format
}

/**
  * This interface defines all the events that the ShoppingcartEntity supports.
  */
sealed trait ShoppingcartEvent extends AggregateEvent[ShoppingcartEvent] {
  def aggregateTag: AggregateEventTag[ShoppingcartEvent] = ShoppingcartEvent.Tag
}

object ShoppingcartEvent {
  val Tag: AggregateEventTag[ShoppingcartEvent] = AggregateEventTag[ShoppingcartEvent]
}

/**
  * This interface defines all the commands that the ShoppingcartEntity supports.
  */
sealed trait ShoppingcartCommand[R] extends ReplyType[R]

/**
  * A command to switch the greeting message.
  *
  * It has a reply type of [[Done]], which is sent back to the caller
  * when all the events emitted by this command are successfully persisted.
  */
case class UseGreetingMessage(message: String) extends ShoppingcartCommand[Done]

//BEGIN add to cart
final case class AddToCartCommand(product: String) extends ShoppingcartCommand[Done]
case object ShowCartCommand extends ShoppingcartCommand[List[String]]
case class RemoveFromCartCommand(product: String) extends ShoppingcartCommand[Done]
//END add to cart


object UseGreetingMessage {

  /**
    * Format for the use greeting message command.
    *
    * Persistent entities get sharded across the cluster. This means commands
    * may be sent over the network to the node where the entity lives if the
    * entity is not on the same node that the command was issued from. To do
    * that, a JSON format needs to be declared so the command can be serialized
    * and deserialized.
    */
  implicit val format: Format[UseGreetingMessage] = Json.format
}

/**
  * Akka serialization, used by both persistence and remoting, needs to have
  * serializers registered for every type serialized or deserialized. While it's
  * possible to use any serializer you want for Akka messages, out of the box
  * Lagom provides support for JSON, via this registry abstraction.
  *
  * The serializers are registered here, and then provided to Lagom in the
  * application loader.
  */
object ShoppingcartSerializerRegistry extends JsonSerializerRegistry {
  override def serializers: Seq[JsonSerializer[_]] = Seq(
    JsonSerializer[UseGreetingMessage],
    JsonSerializer[ShoppingcartState]
  )
}

// BEGIN: add to shopping cart
case class ShoppingcartState(products: List[String])
// END: add to shopping cart