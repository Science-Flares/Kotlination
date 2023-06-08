package coroutines

import kotlinx.coroutines.MainCoroutineDispatcher

@coroutines.ExperimentalCoroutinesApi
class MainCoroutineDispatcher {
    lateinit var mainCoroutineDispatcher: MainCoroutineDispatcher

    init {
        mainCoroutineDispatcher.immediate
    }

}