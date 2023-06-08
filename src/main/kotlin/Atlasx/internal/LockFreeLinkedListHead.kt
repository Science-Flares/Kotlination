package coroutines.internal

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.LockFreeLinkedListHead
import kotlinx.coroutines.internal.LockFreeLinkedListNode

@InternalCoroutinesApi
class LockFreeLinkedListHead {
    lateinit var lockFreeLinkedListHead:LockFreeLinkedListHead

    init {
        lockFreeLinkedListHead.run {
            this.isEmpty
            this.forEach<LockFreeLinkedListNode> {  }
        }
    }

}