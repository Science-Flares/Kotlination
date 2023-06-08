package coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Suppress("UNREACHABLE_CODE")
class CoroutineDispatcher {
    lateinit var coroutineDispatcher: CoroutineDispatcher

    init {
        coroutineDispatcher.run {
//            this.dispatch(, Runnable {  })
//            this.dispatchYield(, Runnable {  })
//            this.isDispatchNeeded()
        }
    }
}