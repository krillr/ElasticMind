package io.elasticmind.actors

import akka.actor.{Actor, ActorRef}
import akka.routing.ActorRefRoutee

/**
 * Created by akrill on 3/30/15.
 */
class BenchActor(actors: List[ActorRef], delay: Int) extends Actor {
   var received: Int = 0
   var total: Int = 0

   def excludedActors(actor: ActorRef): List[ActorRefRoutee] = {
     for (actorRef <- actors if actorRef != actor) yield ActorRefRoutee(actorRef)
   }

   def receive = {
     case x: Int =>
       received += 1
       total += x
       if (received == actors.length) {
         println(total/(delay/1000))
         System.exit(0)
       }

     case "Start" =>
       println("Organizing Orgy with " + actors.length + " participants")
       for (actor <- actors) {
         actor ! excludedActors(actor)
       }
       println("Let the games begin, with " + actors.length + " participants")
       for (actor <- actors) {
         actor ! None
       }
       Thread.sleep(delay)
       println("That's enough now...")
       for (actor <- actors) {
         actor ! "Stop"
       }
   }
 }
