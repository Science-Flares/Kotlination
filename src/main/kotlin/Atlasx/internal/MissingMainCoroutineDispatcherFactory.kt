package coroutines.internal

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.MissingMainCoroutineDispatcherFactory
@Deprecated("this class don't have any attachment!")
@InternalCoroutinesApi
object MissingMainCoroutineDispatcherFactory {
    @InternalCoroutinesApi
    lateinit var missingMainCoroutineDispatcherFactory:MissingMainCoroutineDispatcherFactory

    init {
        missingMainCoroutineDispatcherFactory
    }
}