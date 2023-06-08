package AtlasJ

import java.math.BigInteger

private class BigInteger {
    val bigInteger = 100.toBigInteger()

    init {
        BigInteger.ZERO
        BigInteger.ONE
        BigInteger.TWO
        BigInteger.TEN

        with(bigInteger){
            lowestSetBit
            abs() // return the absolute Value.
            and(BigInteger.TWO) // bin(1 && 1= 1, 0 && 1= 1, 0 && 0= 0).
            andNot(BigInteger.ONE) // TODO: 11.01.2021
            byteValueExact()
            shortValueExact()
            intValueExact()
            longValueExact()
            bitCount() // Return ... the number one in the binary.
            bitLength() // Return ... of the bits.
            flipBit(0) // reversed the bit at n index.
            clearBit(0) // Changing the bit 1 to 0 at the n index.
            divideAndRemainder(BigInteger.ONE) // todo
            gcd(BigInteger.ONE) // GCD (Greatest Common Divisor) or HCF (Highest Common Factor) of two numbers is the largest number that divides both of them.
            nextProbablePrime() // todo
            isProbablePrime(1) // todo
            max(BigInteger.ONE) // comparison
            min(BigInteger.ONE) // comparison
            mod(BigInteger.ONE) // TODO: 29/05/2021
            modInverse(BigInteger.ONE) // todo
            modPow(BigInteger.ONE, BigInteger.TWO) // todo
            add(BigInteger.ONE) // (+)
            divide(BigInteger.ONE) // (÷)
            subtract(BigInteger.ONE) // (-)
            multiply(BigInteger.ONE) // (×)
            negate() // ×(-1)
            not() // todo
            or(BigInteger.ONE) // bin(1 || 1 = 1, else 0 )
            xor(BigInteger.ONE) // bin(the same is 1, if they are different is 0)
            pow(1) // a¹
            remainder(BigInteger.ONE) // equal to a-(b Q), where Q is the quotient of a/b.
            setBit(1)
            shiftLeft(1) // ... the binary toward the left.
            shiftRight(1) // ... the binary toward the right.
            signum() // Return 1 if was positive and -1 if was negative or 0
            sqrt() // (²√)
            sqrtAndRemainder() // todo
            testBit(1) // todo
            toByteArray().run {
            }
            toString().run {
            }
            minus(BigInteger.ONE) // (-)
            plus(BigInteger.ONE) // (+)
            times(BigInteger.ONE) // (×)
            dec() // Increments this value (+1)or(+=1).
            inc() // Decrements this value (-1)or(-=1).
            inv() // Makes every 0 to 1, and every 1 to 0.
            shl(1) // shiftLeft()
            shr(1) // shiftRight()
            toBigDecimal().run {
            }
            unaryMinus()
        }
    }
}