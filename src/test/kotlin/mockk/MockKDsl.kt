package mockk

import io.mockk.MockKDsl

class MockKDsl {
    init {
        MockKDsl.run {
//            this.internalClearMocks()
            this.internalClearAllMocks()
            this.internalCoEvery {  }
//            this.internalIsMockKMock()
            this.internalCoVerify {  }
            this.internalClearConstructorMockk()
            this.internalClearObjectMockk()
            this.internalClearStaticMockk()
            this.internalCoExcludeRecords {  }
            this.internalCoVerifyAll {  }
            this.internalCoVerifyOrder {  }
            this.internalCoVerifySequence {  }
            this.internalConfirmVerified()
//            this.internalConstructorMockk()
            this.internalRegisterInstanceFactory {  }
//            this.internalMockk()
            this.internalMockkObject()
            this.internalMockkStatic()
//            this.internalStaticMockk<>()
//            this.internalSlot()
            this.internalEvery {  }
            this.internalExcludeRecords {  }
//            this.internalInitAnnotatedMocks()
            this.internalUnmockkAll()
            this.internalUnmockkConstructor()
            this.internalUnmockkObject()
            this.internalUnmockkStatic()
            this.internalVerify {  }
            this.internalVerifyOrder {  }
            this.internalCoVerify {  }
            this.internalCoVerifyOrder {  }
            this.internalConfirmVerified()
            this.internalCoVerifyAll {  }
            this.internalCoVerifySequence {  }
        }
    }
 }