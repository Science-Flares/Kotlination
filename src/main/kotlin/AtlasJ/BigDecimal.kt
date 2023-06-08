package AtlasJ

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

private class BigDecimal {
     val bigDecimal = 10.toBigDecimal()

    init {
        BigDecimal.ZERO
        BigDecimal.ONE
        BigDecimal.TEN

        with(bigDecimal){
            abs() // return the absolute Value.
            add(BigDecimal.ONE) // (+)
            divide(BigDecimal.ONE) // (÷)
            multiply(BigDecimal.ONE) // (×)
            divideToIntegralValue(BigDecimal.ONE) // Return integer value from this dividing.
            divideAndRemainder(BigDecimal.ONE) // TODO
            byteValueExact() // Return the number if was in the Byte range.
            shortValueExact() // Return the number if was in the Short range.
            intValueExact() //
            longValueExact() //
            max(BigDecimal.ONE) // comparison
            min(BigDecimal.ONE) // comparison
            movePointLeft(1) // 1 -> 0.1
            movePointRight(1) // 0.1 -> 1
            negate() // ×(-1)
            plus() // ×(+1)
            pow(1) // a¹
            precision() // Return ... of the numbers.
            remainder(BigDecimal.ONE) // equal to a-(b Q), where Q is the quotient of a/b.
            round(MathContext.DECIMAL128) // TODO
            scale() // Return ... of the numbers after the Point.
            scaleByPowerOfTen(1) // a×10¹
            setScale(1,RoundingMode.UP) // todo
            signum() // Return 1 if was positive and -1 if was negative or 0
            sqrt(MathContext.DECIMAL128) // (²√)
            stripTrailingZeros() // 100 -> 1E+2, Note:the number should have 0 digit.
            subtract(BigDecimal.ONE) // (-)
            toPlainString().run {
            }
            toEngineeringString() // todo
            toBigInteger().run {
            }
            toBigIntegerExact() // todo
            ulp() /* Unit in the Last Place:
                       ulp(x) is usually b^(SIGNIFICAND_WIDTH-1)*b^ilogb(x)
                       mean: 2^-(53-1)*2^roundToInt(log2(x))
            */
            unscaledValue() // 1.23 -> 123, Removing the point from the number.
            minus(BigDecimal.ONE) // (-)
            div(BigDecimal.ONE) // (÷)
            times(BigDecimal.ONE) // (×)
            dec() // Increments this value (+1)
            inc() // Decrements this value (-1).
            unaryMinus() //  Return the negative of this value.
        }
    }
}
