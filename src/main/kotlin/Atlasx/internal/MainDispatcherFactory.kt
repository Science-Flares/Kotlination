package coroutines.internal

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.MainDispatcherFactory
import kotlinx.coroutines.internal.tryCreateDispatcher

@InternalCoroutinesApi
class MainDispatcherFactory {
    @InternalCoroutinesApi
    lateinit var mainDispatcherFactory: MainDispatcherFactory

    init {
        mainDispatcherFactory.run {
            this.loadPriority
            this.createDispatcher(listOf<MainDispatcherFactory>())
            this.hintOnError()
            this.tryCreateDispatcher(listOf<MainDispatcherFactory>())
        }
    }

}