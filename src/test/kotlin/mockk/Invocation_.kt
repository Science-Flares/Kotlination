package mockk

import io.mockk.Invocation

class Invocation_ {
    lateinit var kk : Invocation
    init {
        with(kk){
            self
            stub
            method
            args
            timestamp
            callStack
            originalCall
            fieldValueProvider
//            this.substitute()
            this.copy()
        }
    }
 }