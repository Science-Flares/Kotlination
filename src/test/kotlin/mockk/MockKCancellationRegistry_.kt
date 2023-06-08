package mockk

import io.mockk.MockKCancellationRegistry

class MockKCancellationRegistry_ {
    init {
        MockKCancellationRegistry.Type.OBJECT // { OBJECT, STATIC, CONSTRUCTOR }
        MockKCancellationRegistry.run {
            this.popCancellation()
            this.cancelAll()
            this.pushCancellation {  }
            this.subRegistry(MockKCancellationRegistry.Type.OBJECT)
        }
    }
 }