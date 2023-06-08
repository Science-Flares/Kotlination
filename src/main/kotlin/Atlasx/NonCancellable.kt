package coroutines

import kotlinx.coroutines.NonCancellable

@Deprecated("this class don't have any attachment!")
object NonCancellable {
    lateinit var nonCancellable: NonCancellable

    init {
        nonCancellable
    }
}