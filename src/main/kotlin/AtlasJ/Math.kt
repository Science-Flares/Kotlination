package AtlasJ

import kotlin.math.*
import java.lang.Math as M

private class Math {

     val a = 1
     val b= 2
     private val x = 3.0
     private val y = 4.0
    init {
        M.addExact(a, b) // +
        M.subtractExact(a, b) // -
        M.multiplyExact(a, b) // ×.*
        M.multiplyFull(a, b) // ->  ×.* ,using specific for long type.
        M.floorDiv(a, b)
        abs(-a) // -> |a| ,abstract it's make any number is positive sing.
        x.pow(y) // -> xʸ
        sqrt(x) // -> ²√x
        M.cbrt(y) // -> ³√y
        M.scalb(x, b) // -> (x+x)b , b is the number of parentheses.
        x.withSign  (y) // -> returns the first argument with the sign of the second argument.
        M.PI // 3.14159265358979323846
        M.E // 2.7182818284590452354
        exp(x) // -> e^ˣ // todo
        expm1(x) // -> (e)-1 // todo
        x.IEEErem(y) //(IEEERemainder) equal to x-(y Q), where Q is the quotient of x/y rounded to the nearest integer.
        ceil(x) //  -> Rounds the given value [x] to an integer towards positive infinity.
        floor(x) // ->  Rounds the given value [x] to an integer towards negative infinity.
        M.floorMod(a, b)// if (a<b) =a, if (a=b) =0, if (a>b) = the bernete between both.
        y.roundToInt() // The fun is mix from ceil and floor ,it's near correct value of the number.
        cos(x) // adjacent/hypotenuse.
        acos(x) // to reverse 'cos' option.
        cosh(x) // returns the hyperbolic cosine of a number,  -> (eˣ+e¯ˣ)/2
        sin(x) // opposite/hypotenuse
        asin(x) // The arcsine (in radians) of the given number if it's between -1 and 1; otherwise, NaN.
        sinh(x) //returns the hyperbolic cosine of a number,  -> (eˣ-e¯ˣ)/2
        tan(x) // opposite/adjacent
        atan(x) // returns a numeric value between -π/2  and  π/2  radians.
        atan2(y, x) // function returns the angle in the plane (in radians) between the positive x-axis and the ray from (0,0) to the point (x,y).
        tanh(x) // = sinh(x)/cosh(x) = (eˣ-e¯ˣ)/(eˣ+e¯ˣ)
        M.decrementExact(a) // -> -a
        M.incrementExact(a) // -> +a.
        hypot(x, y) // -> √(x²+y²).
        M.random() // for chose randomly Double number.
        ln(x) // The natural logarithm.
        log10(x) // returns the natural logarithm of base the number 10.
        ln1p(x) // -> log(x+1).
        M.getExponent(y) // -> getExponent(16)= 4.
        M.multiplyHigh(1L, 2L)// todo
        round(x)// Rounds the given value (x) towards the closest integer.
        sign(x) // return sing the number as if was positives number=1.0 ,if was negative number =-1.0 ,if was zero =0.0, NaN -> NaN
        M.toIntExact(a.toLong())// -> using like .toInt
        M.toDegrees(x)// -> ex:1.0471975511965976(rad||c) = 60°
        M.toRadians(y) // -> ex:60° = 1.0471975511965976(rad||c).
        M.ulp(x)// unit in the least place.
        y.nextUp()// 1 -> 1.0000001 ,the fun give you near small up value.
        y.nextDown()// 1 -> 0.999999994 ,it's give you near small down value.
        x.nextTowards(y)// returns the floating-point number adjacent to the first argument in the direction of the second argument.
        M.negateExact(a)// -> the method reverse sing of the number.
        a.coerceAtLeast(b) // The function returns the largest of zero or more numbers.
        a.coerceAtMost(b) // The static function returns the lowest-valued number passed into it.
        M.fma(a.toDouble(), x, y) // -> a(x+y)
    }
}

