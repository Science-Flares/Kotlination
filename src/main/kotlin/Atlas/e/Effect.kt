package e

import kotlin.contracts.Effect
import kotlin.contracts.ExperimentalContracts
@Deprecated("don't have attachment!")
interface Effect {
    @ExperimentalContracts
     var effect: Effect
    @ExperimentalContracts
    fun main() {
        effect
    }
}
