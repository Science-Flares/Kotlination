package s

import kotlin.reflect.KMutableProperty
@Deprecated("don't have attachment!")
interface Setter {
     var setter: KMutableProperty.Setter<*>
    fun main() {
        setter
    }
}