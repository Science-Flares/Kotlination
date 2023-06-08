package coroutines

import kotlinx.coroutines.DisposableHandle

interface DisposableHandle {
     var dh: DisposableHandle

    fun main() {
        dh.dispose()
    }
}