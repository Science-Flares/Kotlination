package coroutines.channels

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.runBlocking

interface ReceiveChannel {
    var receiveChannel: ReceiveChannel<*>

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    fun main() = runBlocking {
        with(receiveChannel) {
            this.isClosedForReceive
            this.isEmpty
            this.onReceive
            this.cancel()
            this.iterator()
            this.tryReceive().getOrNull()
            this.receive()
            this.receiveCatching().getOrNull()
//            this.toChannel() // TODO
            this // and others like string attachment.
        }
    }
}