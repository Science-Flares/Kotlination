@file:Suppress("CAST_NEVER_SUCCEEDS")

package Variables

import kotlin.math.absoluteValue
import kotlin.math.sign
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

lateinit var intIterator: IntIterator
// Note: The integer variable has correct number.

@ExperimentalStdlibApi
@ExperimentalTime
@ExperimentalUnsignedTypes
private fun some(){

    val int = Int
    with(int){
        MAX_VALUE // 2147483647
        MIN_VALUE // -2147483648
        SIZE_BITS // 32
        SIZE_BYTES // 4
    }
    val uint = UInt
    with(uint){
        MAX_VALUE // 4294967295
        MIN_VALUE // 0
        SIZE_BITS // 32
        SIZE_BYTES // 4
    }

    // IntProgression. The Progression work with the range variables.
    (0..100).run {
        first // The first element in the progression.
        last // The last element in the progression.
        step // The step of the progression.
        isEmpty()
        reversed()
        step(0)
    }

    // IntIterator.
    intIterator.nextInt() // Returns the next value in the sequence without boxing.

    // IntArray. It's like any Array but only for integer number.
    val myIntArray : IntArray = intArrayOf(1,2,3)

    // IntRange.
    (0..100).run {
        random() // get some char from the range randomly.
        randomOrNull()
        contains(0)
    }

    // Int Attachment:
    with(100){
        dec() // Increments this value.
        inc() // Decrements this value.
        div(2) // divide
        inv() // inverts the bit pattern. Changing every 0 to 1, and every 1 to 0.
        this and 1 // compares corresponding bits of two values. If both bits are 1, it is evaluated to 1. If either of the bits is 0, it is evaluated to 0.
        this or 1 // compares corresponding bits of two values. If either of the bits is 1, it gives 1. If not, it gives 0.
        this xor 2 // compares corresponding bits of two values. If corresponding bits are different, it gives 1. If corresponding bits are same, it gives 0.
        this shl 1 // (shift left) the binary.
        this shr 1 // (shift right) the binary.
        this ushr 1 // function shifts zero into the leftmost position.
        rem(1) // (Remainder) equal to a-(b Q), where Q is the quotient of a/b.
        times(2) // Multiplies this value by the other value.
        minus(1) // Subtracts the other value from this value.
        plus(1) // Adds the other value to this value.
        rangeTo(200) // Creates a range from this value to the specified [other] value.
        until(200) // 100..200.
        downTo(1) // 100..1.
        unaryPlus() // Returns this value.
        unaryMinus() // Return the negative of this value.
        countOneBits() // sum number 1 of binary.
        countLeadingZeroBits() // return sum most important zero's bits.
        countTrailingZeroBits() // work revers countLeadingZeroBits.
        this.coerceIn(1..2)
        this.coerceAtLeast(1)
        this.coerceAtMost(1)
        takeHighestOneBit()
        takeLowestOneBit()
        rotateRight(0) // like shr().
        rotateLeft(0) // like shl().
        toDuration(DurationUnit.DAYS) // Returns a [Duration] equal to this [Int] number of the specified [unit].
        toBigDecimal().run {
            /** @see AtlasJ.BigDecimal */
        }
        toBigInteger().run {
            /** @see AtlasJ.BigInteger */
        }
        sign /* Returns the sign of this value:
                     `-1` if the value is negative,
                   `0` if the value is zero,
                    `1` if the value is positive. */
        absoluteValue // |-1| = 1.
        digitToChar() // Returns the Char that represents this decimal digit. exception if the value !in 0..9.
        floorDiv(1) // Divides this value by the other value, flooring the result to an integer that is closer to negative infinity.
        mod(1) // (Modulo division) Calculates the remainder of flooring division of this value by the other value.
    }

}
