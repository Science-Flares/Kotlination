package Variables

import kotlin.math.*
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

lateinit var doubleIterator:DoubleIterator
//
@ExperimentalUnsignedTypes
@ExperimentalTime
fun myDouble(){
    val double = Double
    with(double){
        MAX_VALUE // 1.7976931348623157 E 308
        MIN_VALUE // 4.9E -324
        POSITIVE_INFINITY // +Infinity
        NEGATIVE_INFINITY // -Infinity
        NaN // NaN => Not a Number.
        fromBits(1) // TODO: 29/05/2021 Returns the Double value corresponding to a given bit representation.
    }
        val dou = 10.0
        with(dou){
            compareTo(1.0)
            dec() // Increments this value (+1)or(+=1).
            inc() // Decrements this value (-1)or(-=1).
            minus(1.0) // (-)
            div(1.0) // (÷)
            plus(1.0) // (+)
            times(1.0) // (×)
            unaryPlus() // Returns this value.
            unaryMinus() // Return the negative of this value.
            rem(1.0) // (Remainder) equal to a-(b Q), where Q is the quotient of a/b.
            IEEErem(1.0) //(IEEERemainder) equal to x-(y Q), where Q is the quotient of x/y rounded to the nearest integer.
            coerceIn(1.0..9.0) // Return the max.V if the Number get it, else min.V.
            coerceAtLeast(1.0)
            coerceAtMost(9.0)
            rangeTo(5.0) // -> 11.0 until 5.0
            isFinite()
            isInfinite()
            isNaN()
            toBigDecimal().run {
                /* see JAppeana.JBigDecimal */
            }
            toBits() // TODO: 29/05/2021
            toRawBits() // TODO: 29/05/2021
            toUInt()
            toULong()
            sign // Returns the sign of this value: `-1` if the value is negative, `0` if the value is zero, `1` if the value is positive.
            absoluteValue // |-1| = 1.
            ulp /* -> Unit int the Last Place:
                       ulp(x) is usually todo :Base -> b^(SIGNIFICAND_WIDTH-1)*b^ilogb(x)
                       mean: 2^-(53-1)*2^roundToInt(log2(x))
                       SIGNIFICAND: todo
                       ilogb:integer logarithm base
            */
            nextUp()// 1 -> 1.0000001 ,the fun give you near up value.
            nextDown()// 1 -> 0.999999994 ,it's give you near down value.
            nextTowards(1.0)// returns the floating-point number adjacent to the first argument in the direction of the second argument.
            pow(1.0) // -> xʸ
            withSign(1.0) // following sing of the value and return it.
            roundToInt() // the fun is mix from ceil and floor ,it's near correct value of the integer.
            roundToLong() // like same roundToInt()
            toDuration(DurationUnit.DAYS) // Returns a [Duration] equal to this [Double] number of the specified [unit].
            this.mod(3.0) //-> 1.0 , ex: we can put (3.0) three times in the (10.0) then we have (1.0) as left  and this is the our result.
        }

    // DoubleArray. It's like any Array but only for decimal number.
    val doubleArray = DoubleArray(3)

    // DoubleIterator.
    doubleIterator.nextDouble() // Returns the next value in the sequence without boxing.

}
