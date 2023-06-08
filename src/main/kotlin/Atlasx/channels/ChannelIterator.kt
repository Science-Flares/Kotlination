package coroutines.channels

import kotlinx.coroutines.channels.ChannelIterator
import kotlinx.coroutines.runBlocking

interface ChannelIterator {
     var channelIterator: ChannelIterator<*>

    fun main()= runBlocking {
        channelIterator.run {
            this.next()
            this.hasNext()
        }
    }
}