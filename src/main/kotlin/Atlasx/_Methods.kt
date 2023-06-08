@file:Suppress("UNREACHABLE_CODE")

package Atlasx

import kotlinx.coroutines.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi

@OptIn(DelicateCoroutinesApi::class)
@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
@InternalCoroutinesApi
suspend fun coroutinesMethods(){

    DEBUG_PROPERTY_NAME
    DEBUG_PROPERTY_VALUE_AUTO
    DEBUG_PROPERTY_VALUE_OFF
    DEBUG_PROPERTY_VALUE_ON
    IO_PARALLELISM_PROPERTY_NAME
    delay(10)
    yield()
    Job()
    coroutineScope {  }
    CompletableDeferred(Any())
    CoroutineExceptionHandler { coroutineContext, throwable -> Unit }
    CoroutineScope(currentCoroutineContext())
    DisposableHandle {  }
    MainScope()
    Runnable {  }
    SupervisorJob()

//    awaitAll() // TODO
//    handleCoroutineException(, Throwable(),null)
//    handleExceptionViaHandler(, Throwable())
    joinAll()
    newFixedThreadPoolContext(10,"")
    newSingleThreadContext("")
    processNextEventInCurrentThread()
    runBlocking {  }
    supervisorScope {  }
    suspendCancellableCoroutine<Any> {  }
//    withContext() { this }
    withTimeout(10) {}
    withTimeoutOrNull(10) {}

}