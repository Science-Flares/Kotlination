package Variables

lateinit var shot :BooleanIterator

// The boolean variable using for confirm information by true or false.
const val boo :Boolean = true // or false

fun myBoolean() {
    /** If [true] | [false] mean 1 | -1 also mean the same operation calculating
      happened with integer also happened with boolean variable.
     */
    boo.run {
        and(this)
        not()
        or(this)
        xor(this)
    }

    // BooleanArray. It's like any Array but only for Boolean values.
    val boo = BooleanArray(3)

    // BooleanIterator.
    shot.nextBoolean() // Returns the next value in the sequence without boxing.

}
