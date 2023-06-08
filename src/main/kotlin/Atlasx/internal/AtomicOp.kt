package coroutines.internal

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.AtomicOp

@OptIn(InternalCoroutinesApi::class)
@Suppress("UNREACHABLE_CODE")
class AtomicOp {
    @OptIn(InternalCoroutinesApi::class)
    lateinit var atomicOp:AtomicOp<*>

    init {
        atomicOp.run {
            this.isDecided
//            this.complete(,0)
//            this.prepare()
//            this.tryDecide()
        }
    }

}