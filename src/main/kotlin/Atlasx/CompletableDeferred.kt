@file:Suppress("UNREACHABLE_CODE")

package coroutines

import kotlinx.coroutines.CompletableDeferred

interface CompletableDeferred {
     var completableDeferred: CompletableDeferred<Any>

    fun main() {
        completableDeferred.run {
            this.complete("")
            this.completeExceptionally(Throwable())
        }
    }
}