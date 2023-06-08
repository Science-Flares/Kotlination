package Atlas.c

import kotlin.contracts.ContractBuilder
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind

private interface ContractBuilder {
    var function: Function<*>
    @ExperimentalContracts
    var contractBuilder: ContractBuilder

    @ExperimentalContracts
    fun main() {
        contractBuilder.callsInPlace(function,InvocationKind.UNKNOWN)
        contractBuilder.returns()
        contractBuilder.returnsNotNull()
    }
}
