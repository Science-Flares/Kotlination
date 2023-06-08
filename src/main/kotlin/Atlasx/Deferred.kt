package coroutines

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking

interface Deferred {
     var deferred: Deferred<*>

    @OptIn(ExperimentalCoroutinesApi::class)
    @coroutines.ExperimentalCoroutinesApi
    fun main() = runBlocking{
        deferred.run {
            this.onAwait
            this.await()
            this.getCompleted()
            this.getCompletionExceptionOrNull()
        }
    }

}