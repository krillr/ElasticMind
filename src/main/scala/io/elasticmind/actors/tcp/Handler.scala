package io.elasticmind.actors.tcp

import akka.actor.Actor
import akka.io.{ IO, Tcp }
import akka.util.ByteString

/**
 * Created by akrill on 3/30/15.
 */
class Handler extends Actor {

  import Tcp._
  var response: ByteString = ByteString.fromString(":1\r\n")

  def receive = {
    case Received(data) =>
      sender() ! Write(response, Tcp.NoAck)
    case PeerClosed     => context stop self
    case Closed         => context stop self
  }

}
