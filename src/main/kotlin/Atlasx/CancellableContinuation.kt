@file:Suppress("UNREACHABLE_CODE")

package coroutines

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.InternalCoroutinesApi

interface CancellableContinuation {
     var cancellableContinuation: CancellableContinuation<Any>

    @OptIn(InternalCoroutinesApi::class)
    @coroutines.InternalCoroutinesApi
    fun main() {
        cancellableContinuation.run {
            this.isActive
            this.isCancelled
            this.isCompleted
            this.cancel()
            this.completeResume(0)
            this.invokeOnCancellation {  }
            this.tryResume("",null)
            this.tryResumeWithException(Throwable())
//            this.cancelFutureOnCancellation("")
        }
    }

}