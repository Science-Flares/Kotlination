package k

import kotlin.reflect.KMutableProperty
import kotlin.reflect.jvm.javaSetter

interface KMutableProperty {
     var kMutableProperty: KMutableProperty<*>
    fun main() {
        kMutableProperty.setter
        kMutableProperty.javaSetter
    }
}
