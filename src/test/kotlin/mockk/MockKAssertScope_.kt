package mockk

import io.mockk.MockKAssertScope
import io.mockk.checkEquals

class MockKAssertScope_ {
    init {
        MockKAssertScope(null).checkEquals("",null)
    }
 }