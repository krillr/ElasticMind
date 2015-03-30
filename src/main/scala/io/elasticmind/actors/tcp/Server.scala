package io.elasticmind.actors.tcp

import akka.actor.{Props, Actor}
import akka.io.{ IO, Tcp}
import akka.util.ByteString
import java.net.InetSocketAddress

class Server extends Actor {

  import Tcp._
  import context.system

  IO(Tcp) ! Bind(self, new InetSocketAddress("localhost", 6380))

  def receive = {
    case b @ Bound(localAddress) =>
      println(b)
    case CommandFailed(_: Bind) =>
      println("Could not bind, stopping")
    case c @ Connected(remote, local) =>
      val handler = context.actorOf(Props[Handler])
      sender() ! Register(handler)
  }

}
