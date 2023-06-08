@file:Suppress("UNREACHABLE_CODE")

package r

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface ReadWriteProperty {
     var readWriteProperty: ReadWriteProperty<Any,Any>
     val pro :KProperty<String>
    fun main() {
        readWriteProperty.getValue("",pro)
        readWriteProperty.setValue("",pro,"")
    }
}