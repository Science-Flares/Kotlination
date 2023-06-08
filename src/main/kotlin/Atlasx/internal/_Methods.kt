package coroutines.internal

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.artificialFrame

@InternalCoroutinesApi
fun main() {
    artificialFrame("")
    synchronized(0) { }
}