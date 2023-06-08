package mockk

import io.mockk.MockKSettings

class MockKSettings {
    init {
        MockKSettings.run {
            this.recordPrivateCalls
            this.relaxed
            this.relaxUnitFun
            this.stackTracesAlignment
            this.stackTracesOnVerify
            this.setRecordPrivateCalls(false)
            this.setRelaxUnitFun(false)
            this.setRelaxed(false)
            this.setStackTracesAlignment("")
        }
    }
 }