package io.elasticmind.actors

import akka.actor.{Actor, ActorRef}
import akka.routing.{ActorRefRoutee, Router, SmallestMailboxRoutingLogic}

/**
 * Created by akrill on 3/30/15.
 */
class PingPong extends Actor {
   var y: Int = 0
   var router: Router = null
   def receive = {
     case None =>
       this.y += 1
       context.actorSelection(self.path)
       router.route(None, ActorRef.noSender)
     case "Stop" =>
       sender() ! this.y
       context stop self
     case x: List[ActorRefRoutee] =>
       router = Router(SmallestMailboxRoutingLogic(), x.to[Vector])
   }
 }
