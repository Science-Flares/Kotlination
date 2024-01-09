@file:Suppress("UNREACHABLE_CODE")

package coroutines.selects

import Atlasx.internal.AtomicDesc
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.selects.SelectInstance

interface SelectInstance {
    @InternalCoroutinesApi
     var selectInstance:SelectInstance<*>

    @InternalCoroutinesApi
    fun main() {
        selectInstance.run {
            // TODO:
//            this.trySelect()
        }
    }

}