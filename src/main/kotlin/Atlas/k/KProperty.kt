package k

import kotlin.reflect.KProperty
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaGetter

interface KProperty {
     var kpro: KProperty<Int>
    fun main(){
        kpro.run {
            this.getter
            this.isConst
            this.isLateinit
            this.javaField
            this.javaGetter
        }
       val acc : KProperty.Accessor<Int>
       val gett : KProperty.Getter<Int>
    }
}