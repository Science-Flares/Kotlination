package Variables

lateinit var floatIterator: FloatIterator
//
@ExperimentalUnsignedTypes
fun flo(){
    val float = Float
    with(float){
        MAX_VALUE // 3.4028235 E 38
        MIN_VALUE // 1.4 E -45
        POSITIVE_INFINITY // Infinity
        NEGATIVE_INFINITY // -Infinity
        NaN // NaN
        fromBits(1) // TODO: 29/05/2021 Returns the Float value corresponding to a given bit representation.
    }
    //
    floatIterator.nextFloat() // Returns the next value in the sequence without boxing.

    // FloatArray. It's like any Array but only for decimal number.
    val floatArray = FloatArray(3)

    //
    val flo = 11.0F
    flo.run {
        /* Same double Attachment. */
    }
}
