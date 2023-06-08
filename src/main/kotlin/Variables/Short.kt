package Variables

lateinit var shortIterator: ShortIterator

//
@ExperimentalUnsignedTypes
fun short(){
    val short = Short
    with(short){
        MAX_VALUE // 32767
        MIN_VALUE // -32768
        SIZE_BITS // 16
        SIZE_BYTES // 2
    }
    val uShort = UShort
    with(uShort){
        MAX_VALUE // 65535
        MIN_VALUE // 0
        SIZE_BITS // 16
        SIZE_BYTES // 2
    }

    // ShortArray. It's like any Array but only for integer number.
    val shortArray = ShortArray(3)

    // ShortIterator.
    shortIterator.nextShort() // Returns the next value in the sequence without boxing.

}