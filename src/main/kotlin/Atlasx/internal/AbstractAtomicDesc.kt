package coroutines.internal

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.LockFreeLinkedListNode
@Deprecated("this class don't have any attachment!")
 @InternalCoroutinesApi
 class AbstractAtomicDesc {
    @InternalCoroutinesApi
    lateinit var abstractAtomicDesc:LockFreeLinkedListNode.AbstractAtomicDesc

    init {
        abstractAtomicDesc
    }

}