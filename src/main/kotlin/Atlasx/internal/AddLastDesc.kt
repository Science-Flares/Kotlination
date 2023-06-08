package coroutines.internal

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.LockFreeLinkedListNode

@InternalCoroutinesApi
class AddLastDesc {
    @InternalCoroutinesApi
    lateinit var addLastDesc:LockFreeLinkedListNode.AddLastDesc<*>

    init {
        addLastDesc.run {
            this.node.run {
            }
            this.queue
        }

    }
}