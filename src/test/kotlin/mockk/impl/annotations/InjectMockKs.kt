package mockk.impl.annotations

import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.InjectionLookupType

annotation class InjectMockKs {
    companion object {
        init {
            InjectMockKs(
                lookupType = InjectionLookupType.BOTH,
                injectImmutable = false,
                overrideValues = false
            )
        }
    }
 }