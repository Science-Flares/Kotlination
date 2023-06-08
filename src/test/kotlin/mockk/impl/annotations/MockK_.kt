package mockk.impl.annotations

import io.mockk.impl.annotations.MockK

annotation class MockK_ {
    companion object {
        init {
            MockK(
                name = "",
                relaxed = false,
                relaxUnitFun = false
            )
        }
    }
}
