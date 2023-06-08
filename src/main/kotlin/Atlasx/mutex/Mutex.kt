package coroutines.mutex

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface Mutex  {
     var mutex: Mutex

    fun main() = runBlocking{
        mutex.run {
            this.isLocked
            this.onLock
            this.holdsLock(0)
            this.lock()
            this.tryLock()
            this.unlock()
            this.withLock(null) {this}
        }
    }



}