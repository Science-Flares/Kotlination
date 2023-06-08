@file:Suppress("UNREACHABLE_CODE")

package k

import kotlin.reflect.KProperty1
import kotlin.reflect.full.getExtensionDelegate

interface KProperty1 {
     var kProperty1: KProperty1<Int,Int>
    fun main() {
        kProperty1.get(1)
        kProperty1.getDelegate(2)
        kProperty1.getExtensionDelegate()
    }
}