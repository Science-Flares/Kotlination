package mockk

import io.mockk.CapturingSlot
import io.mockk.MockKGateway
import io.mockk.MockKMatcherScope

class MockKMatcherScope_ {
    lateinit var mc : MockKGateway.CallRecorder
    init {
//        MockKMatcherScope.DynamicCall
//        MockKMatcherScope.MockKVarargScope
//        MockKMatcherScope.DynamicCallLong
//        MockKMatcherScope.DynamicSetProperty
        MockKMatcherScope(mc, CapturingSlot())
            .run {
            this.lambda
//                this.any()
                this.anyByteVararg()
//                this.anyVararg()
//                this.captureLambda()
//                this.capture()
//                this.cmpEq()
//                this.coInvoke()
//                this.invoke()
//                this.less()
//                this.more()
//                this.range()
//                this.varargAll {  }
//                this.varargAny {  }
//                this.eq()
        }
    }
 }