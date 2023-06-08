package mockk

import io.mockk.MockKAnswerScope

class MockKAnswerScope_<T,B> {
    lateinit var kk : MockKAnswerScope<T, B>
    init {
        with(kk) {
            this.args
            this.call
            this.nothing
            this.invocation
            this.matcher
            this.method
            this.nArgs
            this.self
            this.value
            this.fieldValue
            this.valueAny
            this.fieldValueAny
            this.callOriginal()
//            this.arg()
//            this.coroutine()
//            this.lambda()
//            this.firstArg()
//            this.secondArg()
//            this.thirdArg()
//            this.lastArg()
        }
    }
 }