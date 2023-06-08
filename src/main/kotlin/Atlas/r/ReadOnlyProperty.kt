@file:Suppress("UNREACHABLE_CODE")

package r

import kotlin.properties.ReadOnlyProperty

interface ReadOnlyProperty {
     var readOnlyProperty: ReadOnlyProperty<Int,Any>
    fun main() {
//        readOnlyProperty.getValue(1,kProperty)
    }
}