## Theory: BigDecimal

## Large numbers in Kotlin

Sometimes programmers have to work with extremely large numbers. Since standard primitive types cannot store them, there are two special classes for this purpose: **BigInteger** for integer numbers and **BigDecimal** for floating-point numbers.

You're already familiar with `BigInteger`, and in this topic, we are going to look at `BigDecimal`.

Both classes are provided by Java Class Library. The size of numbers isn't really limited by anything but the physical memory of your computer. In the case of `BigDecimal`, you can have as many digits after the point as you want, which is important for accurate calculations. There are programs where the accuracy of computation is critical, for example, aircraft or medical software, or storing and processing sums of money, to name a few.

## Creating objects of BigDecimal

To create an instance of `BigDecimal`, the first thing you need to do is import this class from the `java.math` package using the following statement:

```kotlin
import java.math.BigDecimal
```

At this point, you have several different options to create a `BigDecimal`: let's look at a couple of them. The first way is creating it from the `String` object and specifying the desired number in double-quotes. And you can immediately convert the input string to `BigDecimal`:

```kotlin
val firstBigDecimal = BigDecimal("10000000000000.5897654329")
val secondBigDecimal = BigDecimal(readLine()!!) // Store the input number
```

The second option is creating it from `Double`:

```kotlin
val bigDecimal = BigDecimal(10000000000000.5897654329)
```

You can convert numbers of other types to `BigDecimal` as follows:

```kotlin
val number = 100000.50000
val bigDecimal = number.toBigDecimal()
```

As you can see, that syntax is consistent and pretty simple.

It is worth mentioning that the class `BigDecimal` has several useful constants, just like `BigInteger`:

```kotlin
val zero = BigDecimal.ZERO // 0
val one = BigDecimal.ONE   // 1
val ten = BigDecimal.TEN   // 10
```

## Arithmetic operations

It is extremely important to keep in mind that `BigDecimal` is an **immutable** class. Immutability implies that you cannot change an existing instance of `BigDecimal`, even if it was declared with the `var` keyword. If you try to modify an existing object, it is created again.



Remember: `BigDecimal` numbers are immutable.



You might remember that in the case of `Double` and `Float`, there are a few potential problems with the floating point representation. For instance, the result of adding `0.2` and `0.1` won't be `0.3`, which affects the accuracy of further calculations:

```kotlin
println(0.1 + 0.2) // 0.30000000000000004
```

`BigDecimal` has no such problem: the results of all the operations will be absolutely correct.

Binary and unary operations are available for `BigDecimal`. In the code snippet below, you can see some examples of binary operations with `BigDecimal`:

```kotlin
val first = BigDecimal("0.2")
val second = BigDecimal("0.1")

val addition = first + second   // 0.3
val subtraction = first - second   // 0.1
val multiplication = first * second // 0.02
val division = first / second   // 2.0
val remainder = first % second // 0.0
```

Now, let's take a look at some unary operations:

```kotlin
var first = BigDecimal("0.2")

// decrement
val decrement = --first //  -0.8
// increment
val increment = ++first //  0.2
// unary minus, turning to opposite sign
val reverse = -first  //  -0.2
// absolute value
val module = first.abs()  //  0.2
// raise to the power, works only with Int
val power = first.pow(3) 
```

It is also possible to use increment (`++`) and decrement (`--`) operators in their postfix forms.

## Rounding control

When we need to tweak the accuracy (the number of digits after the point), `setScale()` comes to the rescue. It allows us to adjust the precision of large fractional numbers:

```kotlin
bigDecimal.setScale(newScale, RoundingMode)
```

The first parameter is `newScale`. It sets the number of digits after the decimal point. You may receive the scale of your number this way:

```kotlin
val fractionalNumber= 1234.5678.toBigDecimal()
println(fractionalNumber.scale()) // 4
```

The second parameter — `roundingMode`— allows us to manage the rounding mode. To use it, you need to perform the import:

```kotlin
import java.math.RoundingMode
```

The table below lists all the possible `BigDecimal` rounding modes along with their brief descriptions:

![img](https://ucarecdn.com/c1a3822c-c3aa-4b17-9584-60367e93069e/)

This might seem a little abstract, so let's look at some examples that will help sort things out.

## Rounding mode examples

So, you can adjust the accuracy of your large numbers and choose the rules by which they will be rounded. The following code snippet shows some examples of using `BigDecimal` rounding with a few different rounding modes:

```kotlin
var bigDecimal = BigDecimal("100.5649")
println(bigDecimal.setScale(3, RoundingMode.CEILING))   // 100.565

bigDecimal = BigDecimal("0.55")
println(bigDecimal.setScale(1, RoundingMode.HALF_DOWN)) // 0.5
println(bigDecimal.setScale(3, RoundingMode.UNNECESSARY)) // 0.550
```

Keep in mind that `BigDecimal` numbers are immutable, so it is not enough to simply apply `setScale()` in order for the number to retain the new value after rounding. You need to assign:

```kotlin
var bigDecimal = BigDecimal("999999999999999999.99999999999999")
bigDecimal.setScale(3, RoundingMode.HALF_UP)
println(bigDecimal) // 999999999999999999.99999999999999

bigDecimal = bigDecimal.setScale(3, RoundingMode.HALF_UP)
println(bigDecimal) // 1000000000000000000.000
```

The table below illustrates the difference in behavior depending on rounding modes. You'll be able to easily compare them:

*Examples of Different Rounding Modes, precision set to 0.*

![img](https://ucarecdn.com/4a85c713-5def-4192-8cc9-49b5044440a7/)



Note that `UNNECESSARY` will add insignificant zeros to the number if you specified too many digits in `setScale()`. But if you specify too few digits, an error will occur.



## Rounding in arithmetic operations

Finally, let's discuss something a little more advanced: at this point, you should have enough background knowledge for that.

Let's talk about determining the accuracy of the result of division. The scale of the result is the same as the scale of the dividend, and by default, `RoundingMode.HALF_EVEN` is used. However, you can specify the scale manually if necessary:

```kotlin
val dividend = BigDecimal("0.9865745")
val divisor = BigDecimal("3.543")

var quotient = dividend / divisor    // 0.2784574
quotient = dividend.setScale(4, RoundingMode.CEILING)/ divisor   // 0.2785
```

Let’s look at another example. Say we need to FP.divide an integer `BigDecimal` by something, and as a result, we want to get a fractional number. In such situations, it's important to clearly indicate the scale of the dividend:

```kotlin
val intDividend = BigDecimal("10")
val divisor = BigDecimal("3")

var quotient = intDividend / divisor    // 3
quotient = intDividend.setScale(4, RoundingMode.CEILING)/ divisor   // 3.3333
```

Addition, subtraction, and multiplication have no such specificities. Although precision is also a thing there and it is used in arithmetic operations, it's quite intuitive:

```kotlin
val first = BigDecimal("7.7777")
val second = BigDecimal("3.3")

val addition = first + second   // 11.0777; The result scale is 4 (max of the scales)
val subtraction = first - second   // 4.4777; The result scale is 4 (max of the scales)
val multiplication = first * second // 25.66641; The result scale is 5 (sum of the scales)
```

So, for these operations, we can describe the accuracy of the result as follows:

- **Addition**: the maximum scale of the addends;
- **Subtraction**: the maximum scale of the minuend and subtrahend;
- **Multiplication**: the sum of the multiplier and multiplicand scales;
- **Division**: the scale of the dividend.

## Conclusion

The `BigDecimal` class is useful for storing large fractional numbers. Standard arithmetic operations are also available for `BigDecimal` numbers. You can manage the behavior of objects of this class when rounded with `setScale()`: indicate the desired number of digits as the first parameter and the rounding mode as the second parameter.