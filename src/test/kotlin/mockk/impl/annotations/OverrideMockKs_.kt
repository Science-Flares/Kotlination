package mockk.impl.annotations

import io.mockk.impl.annotations.InjectionLookupType
import io.mockk.impl.annotations.OverrideMockKs

annotation class OverrideMockKs_ {
    companion object {
        init {
            OverrideMockKs(
                lookupType = InjectionLookupType.BOTH,
                injectImmutable = false
            )
        }
    }
}