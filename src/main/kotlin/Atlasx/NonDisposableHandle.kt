package coroutines

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.NonDisposableHandle

@InternalCoroutinesApi
@Deprecated("this class don't have any attachment!")
@coroutines.InternalCoroutinesApi
object NonDisposableHandle {
    @OptIn(InternalCoroutinesApi::class)
    @coroutines.InternalCoroutinesApi
    lateinit var nonDisposableHandle: NonDisposableHandle

    init {
        nonDisposableHandle
    }

}