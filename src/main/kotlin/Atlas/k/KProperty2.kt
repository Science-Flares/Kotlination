package k

import kotlin.reflect.KProperty2
import kotlin.reflect.full.getExtensionDelegate

class KProperty2 {
    lateinit var kProperty2: KProperty2<Int,Int,Int>
    init {
        kProperty2.get(1,2)
        kProperty2.getDelegate(1,2)
        kProperty2.getExtensionDelegate(1)
    }
}