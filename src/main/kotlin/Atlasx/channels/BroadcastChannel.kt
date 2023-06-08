package coroutines.channels

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.runBlocking

interface BroadcastChannel {
    @ExperimentalCoroutinesApi
     var broadcastChannel:BroadcastChannel<*>

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    fun main() {
        broadcastChannel.run {
            this.cancel()
            this.openSubscription()
            this.consume {  }
        }
        runBlocking {
            broadcastChannel.consumeEach {  }
        }
    }
}