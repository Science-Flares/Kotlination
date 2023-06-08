package coroutines

import kotlinx.coroutines.ExecutorCoroutineDispatcher

class ExecutorCoroutineDispatcher {
    lateinit var executorCoroutineDispatcher: ExecutorCoroutineDispatcher

    init {
        executorCoroutineDispatcher.executor
    }

}