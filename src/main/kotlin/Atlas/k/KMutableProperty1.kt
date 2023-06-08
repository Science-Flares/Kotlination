@file:Suppress("UNREACHABLE_CODE")

package k

import kotlin.reflect.KMutableProperty1

interface KMutableProperty1 {
     var kMutableProperty1: KMutableProperty1<Int,Int>
    fun main() {
     kMutableProperty1.set(1,2)
    }
}