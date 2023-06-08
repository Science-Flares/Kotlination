package k

import kotlin.reflect.KFunction
import kotlin.reflect.jvm.javaConstructor
import kotlin.reflect.jvm.javaMethod

interface KFunction{
     var kFunction: KFunction<*>
   fun main() {
    kFunction.run {
        this.isExternal
        this.isInfix
        this.isInline
        this.isOperator
        this.javaConstructor
        this.javaMethod
    }
}
}
