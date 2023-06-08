package coroutines

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Suppress("UNREACHABLE_CODE")
@coroutines.InternalCoroutinesApi
class CoroutineStart {
    lateinit var coroutineStart: CoroutineStart

    init {
        coroutineStart.run {
            this.isLazy
//            this.invoke(suspend {  }, Continuation()
        }
    }

}