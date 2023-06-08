package coroutines.channels

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ProducerScope

interface ProducerScope {
    @ExperimentalCoroutinesApi
     var producerScope:ProducerScope<*>

    @ExperimentalCoroutinesApi
    fun main() {
        producerScope.channel
    }
}