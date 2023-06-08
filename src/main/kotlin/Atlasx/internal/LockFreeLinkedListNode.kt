package coroutines.internal

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.LockFreeLinkedListNode

@InternalCoroutinesApi
class LockFreeLinkedListNode {
    @InternalCoroutinesApi
    lateinit var lockFreeLinkedListNode:LockFreeLinkedListNode

    init {
        with(lockFreeLinkedListNode){
            this.isRemoved
            this.next
            this.nextNode
            this.prevNode
            this.addLast(this)
            this.addLastIf(this) { true }
            this.addLastIfPrev(this) { true }
            this.addLastIfPrevAndIf(this,{ true },{true})
            this.addOneIfEmpty(this)
            this.describeAddLast(this)
            this.describeRemoveFirst()
            this.helpRemove()
            this.remove()
            this.removeFirstOrNull()
//            this.removeFirstIfIsInstanceOf< >() // TODO
//            this.removeFirstIfIsInstanceOfOrPeekIf<> { it} // TODO
        }
    }

}