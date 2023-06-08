package coroutines.channels

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel

@ExperimentalCoroutinesApi
class ConflatedBroadcastChannel {
    @ExperimentalCoroutinesApi
    lateinit var conflatedBroadcastChannel:ConflatedBroadcastChannel<*>

    init {
        conflatedBroadcastChannel.run {
            this.value
            this.valueOrNull
        }
    }
}