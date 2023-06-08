package Variables

lateinit var longIterator:LongIterator

//
@ExperimentalStdlibApi
@ExperimentalUnsignedTypes
fun long(){
    val long = Long
    with(long){
        MAX_VALUE //  9223372036854775807
        MIN_VALUE // -9223372036854775808
        SIZE_BITS // 64
        SIZE_BYTES // 8
    }
    val uLong  = ULong
    with(uLong){
        MAX_VALUE // 18446744073709551615
    }

    // LongArray. It's like any Array but only for integer number.
    val longArray = LongArray(3)

    // LongIterator.
    longIterator.nextLong() // Returns the next value in the sequence without boxing.

    // LongRange.
    val longRange = 0..100L
    longRange.run {
        random() // get some char from the range randomly.
        randomOrNull()
        contains(0)
    }

    // LongProgression
    val longProgression = longRange as LongProgression
    longProgression.run {
        // look to intProgression in Int.kt
    }

}