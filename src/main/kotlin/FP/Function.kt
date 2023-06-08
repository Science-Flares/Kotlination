@file:Suppress("UNUSED_PARAMETER")

package FP

import kotlinx.coroutines.delay
import java.math.BigInteger

//Function declarations
//Functions in Kotlin are declared using the fun keyword:
 fun myint(x: Int): Int {
 return 2 * x
 }
// or shortly
fun myint2() = 32 + 6

// Generic Function.
 fun <T>general(a:T):T {
    val go :T
    return a
}
// fun return Pair.
 fun myPair(int: Int,string: String):Pair<Int,String>{
    return Pair(int,string)
}
// also equality:
fun  deafening() = 10 to 20

// fun return Triple.
 fun myTriple(int: Int,string: String,char: Char):Triple<Int,String,Char>{
    return Triple(int,string,char)
}
// Array return .
 fun myMutable(vararg long: Long): ArrayList<*> {
    return arrayListOf(long)
}
// Anonymous fun.
fun myAnonymous() = fun () { }

// High-Order fun.
fun myExtension(a: () -> Unit) { }

// Lambda fun.High-Order.
fun myLambda() = { a: Int, b: Int -> a + b }

// infix notation.
// infix function must by extension function and have one parameter value.
private infix fun Any.divide(int: Int){}

// suspend Fun.
 private suspend fun mySuspend(){ delay(1000) }

// Local fun.
 fun firstFloor(){
    fun secondFloor(){
    }
}
// operator Fun.
// operator function must by extension function and have one parameter value.
// its powerful modifier used for make possibility operation between two values.
private operator fun Any.plus(int: Int){}

// recursion function.
// tailrec is useful especially when the fun need to call her self in her self,
// and for avoiding 'stackOverFlow' bag with big numbers.
tailrec fun fib(
    n: Int,
    a: BigInteger = BigInteger.ZERO,
    b: BigInteger = BigInteger.ONE
): BigInteger {
    return if (n == 0) b else fib(n-1,a+b,a)
}

/* inline Fun.
 Normally it's used in high-order functions, and when you have parameter as function
 the inline keyword came to make the implantation of your code more efficiency
 it's substitutes the body of parameter function directly into place where the function gets called,
 the system doesn't suggest this option because it is strong. */
 inline fun myInline(a: () -> Unit) { }

// noinline.
// Add noinline for exclude that functions you won't make it inline.
inline fun myInlineFun(a:()->Unit,noinline b:()->Unit){}

// To indicate that the lambda parameter of the inline function cannot use non-local field,
// mark the lambda parameter with the crossinline modifier
inline fun myInlineFunction(crossinline a:() -> Unit, b:() -> Unit){
    object {
//        val c = b() // cannot access.
        val c = a() // can access.
    }
}

// external modifier.
// If you need to access a class implemented in JavaScript from Kotlin in a typesafe way,
// you can write a Kotlin declaration using the external modifier.
 external fun myExternal()

/**
methods vs functions:
[Method] is a [function] associated to an object.
Definitely all top-level [functions] are not [methods].
 */

/* parameter vs argument:
Parameter is variable defined in function declaration.
 Argument is the actual value of this variable that get past to the function. */
// example :
@Suppress("UNREACHABLE_CODE")
private fun randomString(length: Int): String {
    /*                      ↑
                         parameter                 */
    return "it's $length"
}
object Some {
    init {
        randomString(10) // 10 is an argument
    }
//In this example length is a parameter, and 10 (used in function call) is an argument.
}

// where tip.
fun <F> genericF(f:F): F where F:Float = f // TODO: 14/12/2021
