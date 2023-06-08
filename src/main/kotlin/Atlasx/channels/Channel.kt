package coroutines.channels

import kotlinx.coroutines.channels.Channel

interface Channel {
     var channel:Channel<*>
    fun main() {
        channel

        Channel.Factory.CONFLATED
        Channel.Factory.RENDEZVOUS
        Channel.Factory.UNLIMITED
    }
}