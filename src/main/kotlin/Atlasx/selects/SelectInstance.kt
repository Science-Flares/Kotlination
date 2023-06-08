@file:Suppress("UNREACHABLE_CODE")

package coroutines.selects

import coroutines.internal.AtomicDesc
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.selects.SelectInstance

interface SelectInstance {
    @InternalCoroutinesApi
     var selectInstance:SelectInstance<*>

    @InternalCoroutinesApi
    fun main() {
        selectInstance.run {
            this.completion
            this.isSelected
            this.disposeOnSelect( DisposableHandle {  })
            this.performAtomicTrySelect(AtomicDesc::atomicDesc.call())
            this.trySelect()
        }
    }

}