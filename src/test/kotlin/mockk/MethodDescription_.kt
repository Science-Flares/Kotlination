package mockk

import io.mockk.MethodDescription

class MethodDescription_ {
    lateinit var kk : MethodDescription
    init {
        with(kk){
            name
            returnType
            returnTypeNullable
            returnsUnit
            returnsNothing
            isSuspend
            isFnCall
            declaringClass
            paramTypes
            varArgsArg
            privateCall
            this.argToStr(this::class)
            this.argsToStr()
            this.isEquals()
            this.isHashCode()
            this.isToString()
        }
    }
 }