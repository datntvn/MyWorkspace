package com.inviqa.shoppingcart.impl

import com.inviqa.shoppingcart.api
import com.inviqa.shoppingcart.api.{RemoveFromCartRequest, AddToCartRequest, ShoppingcartService}
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.broker.TopicProducer
import com.lightbend.lagom.scaladsl.persistence.{EventStreamElement, PersistentEntityRegistry}
import akka.{Done, NotUsed}

/**
  * Implementation of the ShoppingcartService.
  */
class ShoppingcartServiceImpl(persistentEntityRegistry: PersistentEntityRegistry) extends ShoppingcartService {
  // BEGIN - method for shoppingcart
  def addToCart(id: String): ServiceCall[AddToCartRequest, Done] = ServiceCall { request => 
    val ref = persistentEntityRegistry.refFor[ShoppingcartEntity](id)
    ref.ask(AddToCartCommand(request.product))
  }
  def showCart(id: String): ServiceCall[NotUsed, List[String]] = ServiceCall { _ =>
    val ref = persistentEntityRegistry.refFor[ShoppingcartEntity](id)
    ref.ask(ShowCartCommand)
  }
  def removeFromCart(id: String): ServiceCall[RemoveFromCartRequest, Done] = ServiceCall { request =>
    val ref = persistentEntityRegistry.refFor[ShoppingcartEntity](id)

    ref.ask(RemoveFromCartCommand(request.product))
  }
  // END - method for shoppingcart
}
