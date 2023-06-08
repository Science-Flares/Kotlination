package mockk

import io.mockk.*

private inline fun <reified T:Any> anions() {
    clearMocks("","")
    clearAllMocks()
    clearConstructorMockk()
    clearStaticMockk()
    coEvery {
    }
    coVerify {
    }
    coJustRun {
    }
    coExcludeRecords {
    }
    coVerifyAll {
    }
    coVerifyOrder {
    }
    coVerifySequence {
    }
    confirmVerified()
    every {
    }
    excludeRecords {
    }
    mockk<T>()
    mockkClass(T::class)
    mockkObject()
    mockkStatic("")
    mockkConstructor()
    isMockKMock("")
    registerInstanceFactory {}
    slot<T>()
    spyk<T>()
    stackTracesAlignmentValueOf("")
    unmockkAll()
    unmockkConstructor()
    unmockkObject()
    unmockkStatic("")
    withInstanceFactory ({},{})
}