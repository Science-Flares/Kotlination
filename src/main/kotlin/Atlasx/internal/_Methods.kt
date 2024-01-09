package coroutines.internal

import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
fun main() {
    synchronized(0) { }
}