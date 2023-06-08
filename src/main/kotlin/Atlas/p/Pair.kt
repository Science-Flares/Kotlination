package p

import kotlin.Pair

private class Pair {
    /** Represents a generic pair of two values. */
    val pai : Pair<Any,Any> = Pair(1,2)
    init {
        with(pai){
            first // return first value.
            second // return second value.
            toList() // Converts this pair into a list.
        }
    }
}