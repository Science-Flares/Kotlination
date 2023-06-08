package k

import kotlin.reflect.KProperty0

interface KProperty0 {
     var kProperty0: KProperty0<Int>
    fun main() {
        kProperty0.get()
        kProperty0.getDelegate()
//        kProperty0.isInitialized // todo
    }
}