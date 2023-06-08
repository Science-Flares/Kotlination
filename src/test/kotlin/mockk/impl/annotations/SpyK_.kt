package mockk.impl.annotations

import io.mockk.impl.annotations.SpyK

annotation class SpyK_ {
    companion object {
        init {
            SpyK(
                name = "",
                recordPrivateCalls = false
            )
        }
    }
}