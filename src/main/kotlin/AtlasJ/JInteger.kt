package AtlasJ

import java.lang.Integer.*

@Suppress("PLATFORM_CLASS_MAPPED_TO_KOTLIN")
class JInteger {
    init {
       // Integer.
        BYTES // 4
        MAX_VALUE // 2147483647
        MIN_VALUE // -2147483648
        SIZE // 32
        TYPE // todo

        bitCount(2) // Return ... the number one in the binary.
        decode("1") // Return integer value by string input.
        toBinaryString(2) // Convert the text to binary.
        toHexString(2) // Convert the text to the Hex.
        toOctalString(2) // Convert the text to the Octal.
        divideUnsigned(1, 2)
        parseInt("", 2)
        parseUnsignedInt("", 2)
        toUnsignedLong(2)
        toUnsignedString(2)
        remainderUnsigned(1, 2)
        getInteger("")
        highestOneBit(2)
        lowestOneBit(2)
        compareUnsigned(1, 2)
        max(1, 2) // Comparator.
        min(1, 2) // Comparator.
        numberOfLeadingZeros(2)
        numberOfTrailingZeros(2)
        reverse(2)
        reverseBytes(2)
        signum(2)
        sum(1, 2) // (+)

    }
}