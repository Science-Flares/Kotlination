@file:Suppress("UNREACHABLE_CODE")

package k

import kotlin.reflect.KMutableProperty2

interface KMutableProperty2 {
     var kMutableProperty2: KMutableProperty2<Int,Int,Int>
    fun main() {
       kMutableProperty2.set(1,2,3)
    }
}