import kotlinx.coroutines.CoroutineScope

interface CoroutineScope {
     var coroutineScope: CoroutineScope

    fun main() {
        coroutineScope.coroutineContext
    }
}