@file:Suppress("UNREACHABLE_CODE")

package coroutines.channels

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.runBlocking

interface SendChannel {
     var sendChannel:SendChannel<*>

    @ExperimentalCoroutinesApi
    fun main()= runBlocking {
        sendChannel.run {
            this.isClosedForSend
            this.onSend
            this.close()
            this.invokeOnClose {  }
//            this.offer()
//            this.send()
        }
    }

}