package Atlas.k

import kotlin.reflect.KVariance

class KVariance {
    lateinit var kVariance: KVariance
    init {
        KVariance.IN
        KVariance.OUT
        KVariance.INVARIANT
    }
}