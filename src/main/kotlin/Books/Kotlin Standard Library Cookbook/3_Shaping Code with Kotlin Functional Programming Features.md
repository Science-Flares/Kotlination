##Shaping Code with Kotlin Functional Programming Features

In this chapter, we will cover the following recipes:

• Working effectively with lambda expressions

• Discovering basic scoping functions – let, also, and apply

• Initializing objects the clean way using the run scoping function

• Working with higher-order functions

• Functions currying

• Function composition

• Implementing the Either Monad design pattern

• Approach to automatic functions memoization


###Introduction

Despite the fact that Kotlin is recognized implicitly as an object-oriented language, it is still open to other programming styles and paradigms. 
Thanks to Kotlin's built-in features, we are able to apply functional programming patterns to our code with ease. 
Having the possibility to return functions from other functions or to pass a function as a parameter allows us to benefit from a deferred computation.
In addition, we are able to return functions, instead of already-computed values, on different layers in the code.
This results in the lazy-evaluation feature.

Compared to Scala or other functional programming languages, Kotlin doesn't require us to use dedicated, functional style design patterns. 
It also lacks some of their out-of-the-box implementations. 
However, in return, it brings more flexibility to developers as far as software architecture and implementation details are concerned. 
The Kotlin language and standard library components provide full built-in support for basic functional programming concepts.
And more sophisticated ones can always be implemented from scratch or reused from some of the available external libraries. 
The ones worth giving a try are the Kotlin Arrow (http://arrow-kt.io) and funKTionale (https://github.com/MarioAriasC/funKTionale) projects. 
However, keep in mind the words of Robert `C`. 
Martin—It is perfectly possible to write a program that is both object-oriented and functional. Not only is it possible, it is desirable. 
There is no `OO` vs `FP` the two are orthogonal and coexist nicely. 
It should be understood that functional programming is only one available tool. It should be used wisely and only where it is applicable.
This chapter focuses on explaining functional programming features supported by Kotlin internally. 
It gives you hands-on experience in solving real-life problems by using state-of-the-art functional programming concepts. 
By the end of the chapter, you should be familiar with the Kotlin language support for the functional programming approach and standard library components that can help implement it.

###Working effectively with lambda expressions

In this recipe, we are going to explore the concept of lambdas and closures.
We are going to write part of an Android application code responsible for handling button-click actions.

**Getting ready**

In order to implement this recipe's code, you need to create a new Android application project using Android Studio IDE. 
Let's assume we have the following class, which is a sort of controller of the application view layer:
```kotlin

class RegistrationScreen : Activity() {
    private val submitButton: Button by lazy { findViewById(R.id.submit_button) }
    override fun onCreate(savedInstanceState: Bundle?) {
        
// hook function called once the screen is displayed
    }
}
```
It contains a reference to the `submitButton: Button` instance. 
Inside the `onCreate()` function we are going to implement logic responsible for handling the button clicks. 
Once the button is clicked, we want to make it invisible.
In order to invoke some action when the button is clicked, we need to call the `View.setOnClickListener(listener: OnClickListener)` function on the `View` subclass.
The `OnClickListener` is a functional interface defined as follows:
```java
public interface OnClickListener {
void onClick(View view);
}
```
Under the hood, the Android OS invokes the `onClick()` function when the user clicks the view.
There are two ways of implementing a functional interface in Kotlin:
• Defining an object that implements the interface:
```kotlin

val myInterfaceInstance = object: MyInterface {
    override fun foo() {
// foo function body
    }
}
```
• Treating the interface as a function and implementing it, for example, in the form of the lambda:
```kotlin
val myInterfaceAsFunction: () ->Unit = { 
// foo function body
}
```

_How to do it..._

• Call the `setOnClickListener` function and pass an empty `OnClickListener` instance as a lambda expression:
```kotlin
class RegistrationScreen : Activity() {
    private val submitButton: Button by lazy { findViewById(R.id.submit_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        submitButton.setOnClickListener { view: View ->
// do something on click
        }
    }
}
```
• Modify the visibility of the `submitButton` instance inside the function body:
```kotlin
class RegistrationScreen : Activity() {
    private val submitButton: Button by lazy { findViewById(R.id.submit_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        submitButton.setOnClickListener {
            submitButton.visibility = View.INVISIBLE
        }
    }
}
```

_How it works..._

Thanks to treating `OnClickListener` as a function, we were able to implement it in the clean and concise form of a lambda expression.
The lambda's body will globally be invoked whenever the user clicks the button.
In our case, once the button is clicked, it will be hidden away.
Lambda expressions are one of the most essential functional features of the language and are used extensively in the standard library components. 
They can be seen as an abbreviated form of a function or a functional interface implementation.
Lambdas help to organize code correctly and reduce a lot of boilerplate code. 
The syntax of a lambda expression can be seen as a block of code placed between `{ }` symbols.
Lambda expressions can have function arguments defined explicitly, for example:
```kotlin
val myFunction: (View) ->Unit = { view ->
    view.visibility = View.INVISIBLE
}
```
For the sake of brevity, the explicit parameter can be omitted.
However, we can still access it using it modifier:
```kotlin
val myFunction: (View) ->Unit = {
    it.visibility = View.INVISIBLE
}
```


**There's more...**

When working with lambdas, whenever we want to execute the code inside their body, we need to call the `invoke()` function on them or its equivalent, the `()` operator:
```kotlin
val callback: () ->Unit = { println("The job is done!") }
callback.invoke()
callback()
```
The preceding code is going to print the text twice:
```
"The job is done!"
"The job is done!"
```
There is also another clean way of passing functions as the parameters to other functions.
We can do it using function references:
```kotlin
fun hideView(view: View): Unit {
    view.visibility = View.INVISIBLE
}
 
submitButton.setOnClickListener(::hideView)
```
The function references approach can be particularly useful for reusing the function implementation across the codebase.

###Discovering basic scoping functions – let, also, apply

In this recipe, we are going to explore three useful extension functions from the standard library—`let`, `also`, and `apply`. 
They work great together with lambda expressions and help to write clean and safe code. 
We are going to practice their usage while applying them to implement a sequence of data-processing operations.

**Getting ready**

Let's assume we can fetch the date using the following function:
```kotlin
fun getPlayers(): List<Player>? 
```

Here, the `Player` class is defined like this:
```kotlin
data class Player(val name: String, val bestScore: Int)
```

We would like to perform the following sequence of operations to the `getPlayers()` function result:

• Print the original set of players in the list to the console

• Sort the collection of the Player objects in descending order

• Transform collection `Player` objects into the list of strings obtained from the `Player.name` property

• Limit the collection to the first element and print it to the console

In order to accomplish the task, first, we need to get familiar with the characteristics of the `let`, `also`, and `apply` functions. 
They are provided in the standard library as extension functions for a generic type. 
Let's explore the headers of the `let`, `also`, and `apply` functions:
```
public inline fun <T, R>T.let(block: (T) ->R): R
 
public inline fun <T>T.also(block: (T) ->Unit): T
 
public inline fun <T>T.apply(block: T.() ->Unit): T 
```
They look similar, however, there are some subtle differences in return types and in parameters. 
The following table compares the three functions:
Function
Return type
Argument in block argument
Block argument definition
let
R (from block body)
Explicit it
(T) -> R
also
T (this)
Explicit it
(T) -> Unit
apply
T (this)
Implicit this
T.() -> Unit

_How to do it..._

• Use the let function together with the safe operator to assure null safety:
```kotlin
getPlayers()?.let {}
```
• Inside the let function's lambda parameter block, use the `also()` function to print the original set of players in the list to the console:
```kotlin
getPlayers()?.let {
    it.also {
        println("${it.size}players records fetched")
        println(it)
    }
}
```
• Use the `let()` function to perform sorting and mapping transformations:
```kotlin
getPlayers()?.let {
    it.also {
        println("${it.size}players records fetched")
        println(it)
    }.let {
        it.sortedByDescending { it.bestScore }
    }
}
```
• Limit the collection of players to a single Player instance with the highest score using the `let()` function:
```kotlin
getPlayers()?.let {
    it.also {
        println("${it.size}players records fetched")
        println(it)
    }.let {
        it.sortedByDescending { it.bestScore }
    }.let {
        it.first()
    }
}
```
• Print the name of the best player to the console:
```kotlin
getPlayers()?.let {
    it.also {
        println("${it.size}players records fetched")
        println(it)
    }.let {
        it.sortedByDescending { it.bestScore }
    }.let {
        it.first()
    }.apply {
        val name = this.name
        print("Best Player: $name")
    }
}
```


_How it works..._

Let's test our implementation. 
For the sake of the test, we can assume that the `getPlayers()` function returns the following results:
 
```kotlin
fun getPlayers(): List<Player>? = listOf(
Player("Stefan Madej", 109),
Player("Adam Ondra", 323),
Player("Chris Charma", 239))
```
The code we have implemented is going to print the following output to the console:

`3 players records fetched`

`[Player(name=Stefan Madej, bestScore=109), Player(name=Adam Ondra, bestScore=323), Player(name=Chris Charma, bestScore=239)]`

`Best Player: Adam Ondra`

Note that, in the case of the `apply()` function, we can omit this keyword while accessing class properties and functions inside the function lambda block:
```kotlin
apply {
    print("Best Player: $name")
}
```
It was just used in the example code for the sake of clarity.
The useful feature of the `let()` function is that it can be used to assure the null safety of the given object.
In the following example inside the let scope, the players argument will always hold a not null value even if some background thread tries to modify the original value of the mutable results variable:
```kotlin
var result: List<Player>? = getPlayers()
result?.let { players: List<Player> ->
    //...
}
```

**See also**

• If you'd like to learn more about lambda expressions, check out the **Working effectively with lambda expressions** recipe

###Initializing objects the clean way using the run scoping function

In this recipe, we are going to explore another useful extension function provided by the standard library, called `run()`.
We are going to use it in order to create and set up an instance of the` java.util.Calendar` class. 

**Getting ready**

First, let's explore the characteristics of the `run()` function defined in the standard library with the following function header:
```kotlin
public inline fun <T, R>T.run(block: T.() ->R): R
```
It is declared as an extension function for a generic type.
The run function provides implicit this parameter inside the block argument and returns the result of the block execution.

_How to do it..._

• Declare an instance of the Calendar.Builder class and apply the `run()` function to it:
```kotlin
val calendar = Calendar.Builder().run {
    build()
}
```
• Add the desired properties to the builder:
```kotlin
val calendar = Calendar.Builder().run {
    setCalendarType("iso8601")
    setDate(2018, 1, 18)
    setTimeZone(TimeZone.getTimeZone("GMT-8:00"))
    build()
}
```
• Print the date from the calendar to the console:
```kotlin

val calendar = Calendar.Builder().run {
    setCalendarType("iso8601")
    setDate(2018, 1, 18)
    setTimeZone(TimeZone.getTimeZone("GMT-8:00"))
    build()
}
print(calendar.time)
```

_How it works..._

The run function is applied to the `Calendar.Builder` instance. 
Inside the lambda passed to the `run` function, we can access the `Calendar.Builder` properties and methods via `this` modifier. 
In other words, inside the run function block, we are accessing the scope of the `Calendar.Builder` instance. 
In the recipe code, we are omitting to invoke Builder methods with `this` keyword.
We can call them directly because the run function allows accessing the Builder instance inside its scope by an implicit `this` modifier.

**There's more...**

We can also use the `run()` function together with the safe `?` operator to provide null safety of the object referenced by `this` keyword inside the `run()` function scope. 
You can see it in action in the following example of configuring the Android `WebView` class:
```kotlin
webview.settings?.run {
    this.javaScriptEnabled = true
    this.domStorageEnabled = false
}
```
In the preceding piece of code, we are ensuring that the settings' property is not null inside the run function scope, and we can access it with `this` keyword.

**See also**

• The Kotlin standard library offers another similar extension function, called `apply()`, which is useful for the initialization of objects. 
The main difference is that it returns an original instance of the object it was called on.
You can explore it in the Implementing builders the smart way recipe in [Chapter 5], Tasteful Design Patterns Adopting Kotlin Concepts.


###Working with higher-order functions

Kotlin is designed to provide first-class support for operating on functions. 
For example, we are able to easily pass functions as parameters to a function.
We can also create a function that can return another function. 
This kind of function is called a **higher-order** function. 
This powerful feature helps to write a functional style code easily. 
The possibility to return a function instead of a value makes along with the ability to pass a function instance to another function as an argument, makes it possible to defer computations and to shape code cleanly. 
In this recipe, we are going to implement a helper function that is going to measure the execution time of other functions passed to it as an argument.

_How to do it..._

Implement the measureTime function:
```kotlin
fun measureTime(block: () ->Unit): Long {
    val start = System.currentTimeMillis()
    block()
    val end = System.currentTimeMillis()

    return end - start
}
```

_How it works..._

The `measureTime()` function takes an argument, called block, of the functional type.
The block function parameter is invoked inside the `measureTime()` function using the `()` modifier. 
Finally, the difference between timestamps (before and after the block execution) is returned. 
Let's analyze the following example showing the `measureTime()` function in action. 
We can consider having the following function responsible for computing the factorial of a given integer:
```kotlin
fun factorial(n: Int): Long {
    sleep(10)
    return if (n == 1) n.toLong() else n * factorial(n - 1)
}
```

In order to measure the `factorial()` function execution time we can use the `measureTime()` function as follows:
```kotlin
val duration = measureTime {
    factorial(13)
}
print("$durationms")
```
As the result, we get the execution time printed to the console:

`154 ms`

Note that it is also possible to pass a function reference instead of a lambda instance as the parameter to the `measureTime()` function:
```kotlin
fun foo() = sleep(1000)
val duration = measureTime(::foo)
print("$durationms")
```

###Functions currying 

Currying is a common technique in functional programming.
It allows transforming a given function that takes multiple arguments into a sequence of functions, each having a single argument. 
Each of the resulting functions handles one argument of the original (uncurried) function and returns another function. 
In this recipe, we are going to implement an automatic currying mechanism that could be applied to any function taking three parameters.

**Getting ready**

To understand the concept of function currying, let's consider the following example of a function handling three parameters:
```kotlin
fun foo(a: A, b: B, c: C): D 
Its curried form would look like this:
fun carriedFoo(a: A): (B) ->(C) ->D 
```

In other words, the curried form of the `foo` function would take a single argument of the `A` type and return another function of the following type: `(B) -> (C) -> D`. 
The returned function is responsible for handling the second argument of the original function and returns another function, which takes the third argument and returns a value of type `D`. 
In the next section, we are going to implement the `curried()` extension function for the generic functional type declared as follows: `<P1, P2, P3, R> ((P1, P2, P3)`. 
The `curried()` function is going to return a chain of single-argument functions and will be applicable to any function which takes three arguments.

_How to do it..._

• Declare a header of the `curried()` function:
```kotlin
fun <P1, P2, P3, R>((P1, P2, P3) ->R).curried(): (P1) ->(P2) ->(P3) ->R 
```

• Implement the `curried()` function body: 
```kotlin
fun <P1, P2, P3, R>((P1, P2, P3) ->R).curried(): (P1) ->(P2) ->(P3) ->R =
    { p1: P1 ->
        { p2: P2 ->
            { p3: P3 ->
                this(p1, p2, p3)
            }
        }
    }
```

_How it works..._

Let's explore how to use the `curried()` function in action. 
In the following example we are going to call `curried()` on the following function instance which is responsible for computing a sum of three integers:
```kotlin
fun sum(a: Int, b: Int, c: Int): Int = a + b + c
```

In order to obtain a curried form of the `sum()` function, we have to invoke the `curried()` function on its reference:
```kotlin
::sum.curried()
```
Then we can invoke the curried sum function in the following way:
```kotlin
val result: Int = ::sum.curried()(1)(2)(3)
```
In the end, the result variable is going to be assigned an integer value equal to `6`.
In order to invoke the `curried()` extension function, we access the `sum()` function reference using the `:: modifier`.
Then we invoke the next functions from the function sequence returned by the curried function one by one. 
The preceding code could be written in an equivalent more verbose form with explicit types declarations:
```kotlin
val sum3: (a: Int) ->(b: Int) ->(c: Int) ->Int = ::sum.curried()
val sum2: (b: Int) ->(c: Int) ->Int = sum3(1)
val sum1: (c: Int) ->Int = sum2(2)
val result: Int = sum1(3)
```
Under the hood, the currying mechanism implementation is just returning functions nested inside each other.
Every time the specific function is invoked, it returns another function with the arity reduced by one. 

**There's more...**

There is a similar pattern called partial application. 
It is more flexible than currying as it doesn't limit the number of arguments handled by each of the functions. 
For example, given a foo function declared as follows:
```kotlin
fun foo(a: A, b: B, c: C): D 
```
We could transform it into the following form:
```kotlin
fun foo(a: A, c: C) ->(B) ->D
```
Both currying and partial application are useful whenever we can't provide the full number of required arguments to the function in the current scope. 
We can apply only the available ones to the function and return the transformed function.



###Function composition

In the **Functions currying** recipe, we discovered a neat way of transforming a function to extract new functions from it.
In this recipe, we are going to work on implementing the opposite transformation. 
It would be useful to have the option to merge a number of existing functions' declarations and define a new function from them.
This is a common functional programming pattern called functions composition. 
Kotlin doesn't provide **function composition** mechanism out of the box. 
However, thanks to the extended built-in support for operations on functional types, we are able to implement a reusable mechanism for the composition manually.

**Getting ready**

In order to get familiar with **function composition**, let's study the following example.
Let's say we have the following functions defined:
```kotlin
fun length(word: String) = word.length
fun isEven(x:Int): Boolean = x.rem(2) == 0
```
The first one is responsible for returning the length of a given string.
The second one checks whether a given integer is even.
In order to define a new function based on those two functions, we can make nested function calls:
```kotlin
fun isCharCountEven(word: String): Boolean = isEven(length(word))
```
This works fine, however, it would be useful if we were able to operate on the function references instead. 
In order to make it more concise we'd like to be able to declare the `isCharCountEven()` function using the following syntax for the functions' composition:
```kotlin
val isCharCountEven: (word: String) ->Boolean = ::length and ::isEven
```

_How to do it..._

• Declare an **infix** extension function for the single-argument function called `and()`:
```kotlin
infix fun <P1, R, R2>((P1) ->R).and(function: (R) ->R2): (P1) ->R2 = {
}
```

• Invoke the base function, and the one passed as an argument of `and()` internally:
```kotlin
infix fun <P1, R, R2>((P1) ->R).and(function: (R) ->R2): (P1) ->R2 = {
    function(this(it))
}
```

_How it works..._

In order to explore our **function composition** implementation, let's use the `and()` function to compose the `isCharCountEven()` function using the `length()` property, and the `isEven()` function:
```kotlin
fun length(word: String) = word.length
fun isEven(x:Int): Boolean = x.rem(2) == 0
val isCharCountEven: (word: String) ->Boolean = ::length and ::isEven
print(isCharCountEven("pneumonoultramicroscopicsilicovolcanoconiosis"))
```
The preceding code is going to return the following output:

`false`

Under the hood, the `and()` extension function just invokes the given two functions one inside another.
However, thanks to the infix notation we can perform the composition in the code while avoiding nested function calls. 
Moreover, the result of the `::length` and `::isEven` call in the preceding example returns a new function instance which can be easily reused, just like a normal function.

###Implementing the Either Monad design pattern

The concept of Monad is one of the fundamental functional programming design patterns.
We can understand a Monad as an encapsulation for a data type that adds a specific functionality to it or provides custom handlers for different states of the encapsulated object.
One of the most commonly used is a Maybe monad.
The Maybe monad is supposed to provide information about the enclosed property presence.
It can return an instance of the wrapped type whenever it's available or nothing when it's not.
**Java 8** introduced the `Optional<T>` class, which is implementing the Maybe concept.
It's a great way to avoid operating on null values.

However, apart from having the information about the unavailable state, we would often like to be able to provide some additional information.
For example, if the server returns an empty response, it would be useful to get an error code or a message instead of the null or an empty response string. 
This is a scenario for another type of Monad, usually called `Either`, which we are going to implement in this recipe.

_How to do it..._

• Declare `Either` as a `sealed` class:
```kotlin

sealed class Either<out E, out V>
```
• Add two **subclasses** of `Either`, representing `Error` and `Value`:
```kotlin

sealed class Either<out L, out R>{
data class Left<out L>(val left: L) : Either<L, Nothing>()
data class Right<out R>(val right: R) : Either<Nothing, R>()
}
```
• Add factory functions for the convenient instantiating of Either:
```kotlin
sealed class Either<out L, out R> {
    data class Left<out L>(val left: L) : Either<L, Nothing>()
    data class Right<out R>(val right: R) : Either<Nothing, R>()

    companion object {
        fun <R> right(value: R): Either<Nothing, R>=
        Either.Right(value )
        fun <L> left(value: L): Either<L, Nothing>=
        Either.Left(value )
    }
}
```

_How it works..._

In order to make use of the class `Either` and benefit from the `Either.right()` and `Either.left()` methods, we can implement a `getEither()` function that will try to perform some operation passed to it as a parameter.
If the operation succeeds, it is going to return the Either.Right instance holding the result of the operation, otherwise, it is going to return `Either.Left`, holding a thrown exception instance:
```kotlin
fun <V>getEither(action: () ->V): Either<Exception, V>=
try { Either.right(action()) } catch (e: Exception) { Either.left(e) }
```
By convention, we use the `Either.Right` type to provide a default value and `Either.Left` to handle any possible edge cases.

**There's more...**

One of the essential functional programming features the Either Monad can provide, is the ability to apply functions to its values. 
We can simply extend the `Either` class with the `fold()` function, which can take two functions as the parameters.
The first function that should be applied to the `Either.Left` type and second, that should be applied to Either.Right:
```kotlin

sealed class Either<out L, out R> {
    data class Left<out L>(val left: L) : Either<L, Nothing>()
    data class Right<out R>(val right: R) : Either<Nothing, R>()

    fun <T> fold(leftOp: (L) -> T, rightOp: (R) -> T): T = when (this) {
        is Left -> leftOp(this.left)
        is Right -> rightOp(this.right)
    }
}
```
The `fold()` function is going to return a value from either the `leftOp` or `rightOp` function, whichever is used.
We can illustrate the usage of the `fold()` function with a server-request parsing example.
Let's say we have the following types declared:
```kotlin
data class Response(val json: JsonObject)
data class ErrorResponse(val code: Int, val message: String)
```
We also have a function responsible for delivering a backend response:
```kotlin
fun someGetRequest(): Either<ErrorResponse, Response>= //..
```
We can use the `fold()` function to handle the returned value in the right way:
```kotlin
someGetRequest().fold({
showErrorInfo(it.message)
}, {
    parseAndDisplayResults(it.json)
})
```

We could also extend the `Either` class with other useful functions similar to the ones available in the standard library for data-processing operations—map, filter, and exists.

###Approach to automatic Functions Memoization

Memoization is a technique used to optimize the program-execution speed by caching the results of expensive function calls and reusing their ready values when they are required again.
Although memoization causes an obvious trade-off between memory usage and computation time, often it's crucial to provide the desired performance.
Usually, we apply this pattern to computationally-expensive functions.
It can help to optimize recursive functions that call themselves multiple times with the same parameters' values.
Memoization can easily be added internally to function implementation.
However, in this recipe, we are going to create a FP.general-purpose, reusable memoization mechanism that could be applied to any function.

**How to do it...**

• Declare a Memoizer class responsible for caching the results:
```kotlin

class Memoizer<P, R>private constructor() {

    private val map = ConcurrentHashMap<P, R>()

    private fun doMemoize(function: (P) -> R):
                (P) -> R = { param: P ->
        map.computeIfAbsent(param) { param: P ->
            function(param)
        }
    }

    companion object {
        fun <T, U> memoize(function: (T) -> U): (T) -> U =
            Memoizer<T, U>().doMemoize(function)
    }
}
```

• Provide a `memoized()` extension function for the `(P) -> R` function type:
```kotlin
fun <P, R>((P) ->R).memoized(): (P) ->R = Memoizer.memoize<P, R>(this)
```

_How it works..._

The `memoize()` function takes an instance of a one-argument function as its argument.
The `Memoizer` class contains the `ConcurrentHashmap<P, R>` instance, which is used to cache the function's return values. 
The map stores functions passed to `memoize()` as arguments as the keys, and it puts their return values as its values. 
First, the `memoize()` function looks up the value for a specific param of the function passed as an argument. 
If the value is present in the map, it is returned. 
Otherwise, the function is executed and its result is both returned by `memoize()` and put into the map. 
This is achieved using the handy `inline fun <K, V> ConcurrentMap<K, V>.computeIfAbsent(key: K, defaultValue: () -> V): V` extension function provided by the standard library.
Additionally, we provide an extension function `memoized()` for the Function1 type that allows us to apply the `memoize()` function directly to the function references.
Under the hood functions in Kotlin are compiled to the FunctionN interface instances in the Java bytecode, where `N` corresponds to the number of function arguments. 
Thanks to that fact, we are able to declare an extension function for a function.
For example, in order to add an extension function for the function taking two arguments,` (P, Q) -> R`, we need to define an extension as `fun <P, Q, R> Function2<P, Q, R>.FP.myExtension(): MyReturnType`.
Now, let's take a look at how we could benefit from the `memoized()` function in action.
Let's consider a function that computes the factorial of an integer recursively:
```kotlin
fun factorial(n: Int): Long = if (n == 1) n.toLong() else n * factorial(n - 1)
```
We can apply the `memoized()` extension function to enable results-caching:
```kotlin
val cachedFactorial = ::factorial.memoized()
println(" Execution time: " + measureNanoTime { cachedFactorial(12) } + " ns")
println(" Execution time: " + measureNanoTime { cachedFactorial(13) } + " ns")
```

The preceding code gives the following output on a standard computer:
```
Execution time: 1547274 ns
Execution time: 24690 ns
```
As you can see, even though the second computation requires a higher number of recursive calls of the `factorial()` function, it takes much less time than the first computation.

**There's more...**

We could implement similar automatic memoization implementations for the other functions that take more than one argument.
In order to declare an extension function for a function taking `N` arguments, we'd have to implement an extension function for the `FunctionN` type.
