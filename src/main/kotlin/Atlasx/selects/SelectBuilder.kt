package coroutines.selects

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.selects.SelectBuilder

interface SelectBuilder {
     var selectBuilder:SelectBuilder<*>

    @ExperimentalCoroutinesApi
    fun main() {
//        selectBuilder.onTimeout(1L, suspend{})
    }
}