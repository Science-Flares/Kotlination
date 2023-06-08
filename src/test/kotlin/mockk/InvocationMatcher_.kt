package mockk

import io.mockk.InvocationMatcher

class InvocationMatcher_ {
   lateinit var kk : InvocationMatcher
    init {
        with(kk){
            this.allAny
            this.args
            this.self
            this.method
//            this.match()
//            this.captureAnswer()
//            this.substitute()
            this.copy()
        }
    }
 }