package coroutines.channels

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ticker

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
fun main() {
    BroadcastChannel<Any>(1)
    Channel<Any>(1)
    ticker(1)
}