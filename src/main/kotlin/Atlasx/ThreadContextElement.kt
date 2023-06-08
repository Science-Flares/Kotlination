@file:Suppress("UNREACHABLE_CODE")

import kotlinx.coroutines.ThreadContextElement

interface ThreadContextElement {
     var threadContextElement: ThreadContextElement<Any>

    fun main() {
        threadContextElement.run {
//            this.restoreThreadContext(,)
//            this.updateThreadContext()
        }
    }
}