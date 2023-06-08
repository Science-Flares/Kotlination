package Atlas.g

import kotlin.collections.Grouping

interface Grouping {
    val grouping: Grouping<Any, Any>

    /** A [Grouping] structure serves as an intermediate step in group-and-fold operations:
     * they group elements by their keys and then fold each group with some aggregating operation.
     */
    fun some() {
        with(grouping) {
            keyOf(0)
            sourceIterator() // Returns an Iterator over the elements of the source of this grouping.
            aggregate { key, accumulator: Int?, element, first -> 0 } // Apply an operation to all the elements in each group and return the result.
            aggregateTo(mutableMapOf()) { key, accumulator: Int?, element, first -> 0 }
            eachCount() // return a [Map] associating the key of each group with the count of elements in the group.
            eachCountTo(mutableMapOf())
            fold(1) { accumulator, element -> 1 }
            foldTo(mutableMapOf(), 1) { accumulator, element -> 1 }
            reduce { key, accumulator, element -> accumulator }
            reduceTo(mutableMapOf()) { key, accumulator, element -> accumulator }
        }
    }
}