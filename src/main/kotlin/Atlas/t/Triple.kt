package t

import kotlin.Triple

class Triple {
    /** Represents a triad of values */
    val tri : Triple<Any,Any,Any> = Triple(1,2,3)
    init {
        with(tri){
            first // return first value.
            second // return second value.
            third // return third value.
            toList() // Converts this triple into a list.
        }
    }
}
