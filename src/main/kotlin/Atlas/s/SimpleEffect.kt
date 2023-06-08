package s

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.SimpleEffect

interface SimpleEffect {
    @ExperimentalContracts
     var simpleEffect: SimpleEffect
    @ExperimentalContracts
    fun main() {
        simpleEffect.implies(true)
    }

}