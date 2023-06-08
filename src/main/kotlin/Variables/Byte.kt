package Variables

lateinit var byteIte : ByteIterator
lateinit var byteArr : ByteArray

// The Byte gave correct number

@ExperimentalUnsignedTypes
fun myByte() {
    val byte = Byte
    with(byte) {
        MAX_VALUE // 127
        MIN_VALUE // -128
        SIZE_BITS // 8
        SIZE_BYTES // 1
    }
    val uByte = UByte
    with(uByte) {
        MAX_VALUE // 255
        MIN_VALUE // 0
        SIZE_BITS // 8
        SIZE_BYTES // 1
    }

    // ByteArray. It's like any Array but only for Digit values.
    with(byteArr){
        this[0]
        this.size
        this.iterator()
//        this.set()
    }

    // ByteIterator.
    byteIte.nextByte() // Returns the next value in the sequence without boxing.



}