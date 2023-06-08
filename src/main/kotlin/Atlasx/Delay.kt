package coroutines

import kotlinx.coroutines.Delay
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.runBlocking

@Suppress("UNREACHABLE_CODE")
@coroutines.InternalCoroutinesApi
class Delay {
    @OptIn(InternalCoroutinesApi::class)
    @coroutines.InternalCoroutinesApi
    lateinit var delay: Delay

    @OptIn(InternalCoroutinesApi::class)
    fun main() = runBlocking {
        delay.run {
            this.invokeOnTimeout(1L, Runnable { }, coroutineContext)
//            this.scheduleResumeAfterDelay(1L,)
        }
    }

}