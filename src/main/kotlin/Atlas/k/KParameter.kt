package k

import kotlin.reflect.KParameter

interface KParameter {
     var kParameter: KParameter
    fun main(){
        kParameter.run {
            this.index
            this.isOptional
            this.isVararg
            this.kind
            this.name
            this.type
        }
    }
}