package mockk

import io.mockk.CompositeMatcher

interface CompositeMatcher_<T> {

        val <T> CompositeMatcher<T>.kk : T?
        get() {
            subMatchers
            operandValues
            return null
        }
 }