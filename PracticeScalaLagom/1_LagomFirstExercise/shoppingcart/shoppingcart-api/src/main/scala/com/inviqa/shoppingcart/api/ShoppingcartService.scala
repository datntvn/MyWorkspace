package com.inviqa.shoppingcart.api

import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.api.broker.kafka.{KafkaProperties, PartitionKeyStrategy}
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}
import play.api.libs.json.{Format, Json}
import com.lightbend.lagom.scaladsl.api.transport.Method

/**
  * The ShoppingCart service interface.
  * <p>
  * This describes everything that Lagom needs to know about how to serve and
  * consume the ShoppingcartService.
  */
trait ShoppingcartService extends Service {

  def addToCart(id: String): ServiceCall[AddToCartRequest, Done]
  def showCart(id: String): ServiceCall[NotUsed, List[String]]
  def removeFromCart(id: String): ServiceCall[RemoveFromCartRequest, Done]

  override final def descriptor: Descriptor = {
    import Service._
    // @formatter:off
    named("shoppingcart")
      .withCalls(
        restCall(Method.POST, "/api/add-to-cart/:id", addToCart _),
        restCall(Method.GET, "/api/cart/:id", showCart _),
        restCall(Method.POST, "/api/cart/:id", removeFromCart _)
      )
      .withAutoAcl(true)
    // @formatter:on
  }
}

// BEGIN - for shopping cart AddToCartRequest
case class AddToCartRequest(product: String)
object AddToCartRequest {
  implicit val format: Format[AddToCartRequest] =
    Json.format[AddToCartRequest]
}
case class RemoveFromCartRequest(product: String)

object RemoveFromCartRequest {
  implicit val format: Format[RemoveFromCartRequest] = Json.format[RemoveFromCartRequest]
}
// END - for shopping cart AddToCartRequest
