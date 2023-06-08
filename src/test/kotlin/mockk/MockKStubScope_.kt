package mockk

import io.mockk.CapturingSlot
import io.mockk.MockKGateway
import io.mockk.MockKStubScope

class MockKStubScope_<T,B> {
    lateinit var ao: MockKGateway.AnswerOpportunity<T>
    lateinit var cr: MockKGateway.CallRecorder
    lateinit var cs: CapturingSlot<Function<*>>

    init {
        MockKStubScope<T, B>(
            answerOpportunity = ao,
            callRecorder = cr,
            lambda = cs
        ).run {
//                this.answers()
//            this.coAnswers {  }
//            this.propertyType()
//            this.nullablePropertyType()
//            this.returns()
//            this.returnsArgument()
//            this.returnsMany()
//            this.throws()
        }
    }
}