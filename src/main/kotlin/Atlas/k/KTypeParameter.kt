package Atlas.k

import kotlin.reflect.KTypeParameter

interface KTypeParameter {
     var kTypeParameter: KTypeParameter
    fun main() {
        kTypeParameter.run {
            this.isReified
            this.name
            this.upperBounds
            this.variance
        }
    }
}