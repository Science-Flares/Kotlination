package Atlas.c

import kotlin.contracts.ConditionalEffect
import kotlin.contracts.ExperimentalContracts
@Deprecated("don't have attachment!")
private interface ConditionalEffect {
    @ExperimentalContracts
    var conditionalEffect: ConditionalEffect
}
