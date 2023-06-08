package Atlas.i

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind

class InvocationKind {
    @ExperimentalContracts
    lateinit var invocationKind: InvocationKind

    @ExperimentalContracts
    fun some() {
        invocationKind
        InvocationKind.AT_LEAST_ONCE
        InvocationKind.AT_MOST_ONCE
        InvocationKind.EXACTLY_ONCE
        InvocationKind.UNKNOWN
        InvocationKind.values()
    }
}