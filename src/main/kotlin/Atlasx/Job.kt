@file:Suppress("UNREACHABLE_CODE")

package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job

interface Job {
     var job: Job

    @OptIn(InternalCoroutinesApi::class)
    @coroutines.InternalCoroutinesApi
    fun main() = runBlocking{
        job.run {
            this.children
            this.isActive
            this.isCancelled
            this.isCompleted
            this.onJoin
//            this.attachChild(child = )
            this.cancel()
            this.getCancellationException()
//            this.invokeOnCompletion(handler = )
            this.join()
            this.start()
            this.cancelAndJoin()
            this.cancelChildren()
//            this.cancelFutureOnCompletion()
        }
    }
}