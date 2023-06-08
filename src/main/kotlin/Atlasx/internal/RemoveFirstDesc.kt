package coroutines.internal

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.LockFreeLinkedListNode

@InternalCoroutinesApi
class RemoveFirstDesc {
    @InternalCoroutinesApi
    lateinit var removeFirstDesc:LockFreeLinkedListNode.RemoveFirstDesc<*>

    init {
        removeFirstDesc.run {
            this.queue
            this.result
        }
    }

}