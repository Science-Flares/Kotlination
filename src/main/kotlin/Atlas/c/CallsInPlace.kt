package Atlas.c

import kotlin.contracts.CallsInPlace
import kotlin.contracts.ExperimentalContracts
@Deprecated("don't have attachment!")
private class CallsInPlace {
    @ExperimentalContracts
    lateinit var callsInPlace: CallsInPlace

    @ExperimentalContracts
    fun main() {
        callsInPlace
    }
}
