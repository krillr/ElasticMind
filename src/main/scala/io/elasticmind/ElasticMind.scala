package io.elasticmind

import akka.actor._
import io.elasticmind.actors.{PingPong, BenchActor}
import io.elasticmind.actors.tcp.Server


object ElasticMind {
  var system: ActorSystem = null

  def main(args: Array[String]) = {
    system = ActorSystem("ElasticMind")
    /*println(system.settings)
    val actors = List.fill(32) { system.actorOf(Props(new PingPong())) }
    val benchActor = system.actorOf(Props(new BenchActor(actors = actors, delay = 60000)), name="roflmao")
    benchActor ! "Start"*/
    val serverActor = system.actorOf(Props[Server])
  }
}