# Ranges, Progressions, and Sequences

### In this chapter, we will cover the following recipes:

• Exploring the use of range expressions to iterate through alphabet characters

• Traversing through ranges using progression with a custom step value

• Building custom progressions to traverse dates

• Using range expressions with flow control statements

• Discovering the concept of sequences

• Applying sequences to solve algorithmic problems

**Introduction**

This chapter will focus on explaining the advantages of range expressions and sequences.
These powerful data structure concepts offered by the **Kotlin** standard library can help you to improve the quality and readability of your code, as well as its safety and performance.
Range expressions provide a declarative way of iterating through sets of comparable types using for loops. 
They are also useful for implementing concise and safe control flow statements and conditions.
The s.Sequence class, as a missing supplement to the Collection type, provides a built-in lazy evaluation of its elements.
In many cases, using sequences can help optimize data-processing operations and make the code more efficient in terms of computation complexity and memory consumption. 
The recipes covered in this chapter are going to focus on solving real-life programming problems. 
Moreover, at the same time, they are also going to explain how those concepts work under the hood.



**Exploring the use of range expressions to iterate through alphabet characters**

Ranges, provided by the **Kotlin** standard library, are a powerful solution for implementing iteration and conditional statements in a natural and safe way. 
A range can be understood as an abstract data type that represents a set of iterable elements and allows iteration through them in a declarative way.
The ClosedRange interface from the `kotlin.ranges` package is a basic model of the range data structure.
It contains references to the first and last elements of the range and provides the `contains(value: T): Boolean` and `isEmpty(): Boolean` functions, which are responsible for checking whether the specified element belongs to the range and whether the range is empty. 
In this recipe, we are going to learn how to declare a range that consists of alphabet characters and iterate through it in a decreasing order.

**Getting ready:**

The Kotlin standard library provides functions that allow the declaration of ranges for the integral, primitive types, such as `Int`, `Long`, and `Char`. 
To define a new range instance, we can use the `rangeTo()` function.
For example, we can declare a range of integers from **0** to **1000** in the following way:

`val range: IntRange = 0.rangeTo(1000)`

The `rangeTo()` function has also its own special operator equivalent, `..`, which allows the declaration of a range with a more natural syntax:

`val range: IntRange = 0..1000`

Also, in order to declare a range of elements in a decreasing order, we can use the `downTo()` function.

_How to do it..._

• Declare a decreasing range of alphabet characters:

`'Z' downTo 'A'`

2. Create a for loop to traverse the range:
`for (letter in 'Z' downTo 'A') print(letter)`

_How it works..._

As a result, we are going to get the following code printed out to the console:

`ZYXWVUTSRQPONMLKJIHGFEDCBA`

As you can see, there is also a `downTo()` extension function variant for the available Char type.
We are using it to create a range of characters from `Z` to `A`.
Note that, thanks for the **infix** notation, we can omit the brackets while invoking the function—`'Z' downTo 'A'`.
Next, we are creating a for a loop, which iterates through the range and prints out the subsequent Char elements. 
Using the in operator, we are specifying the object that is being iterated in the loop—and that's it! As you can see, the Kotlin syntax for the for loop is neat and natural to use.
Implementations of ranges of the primitive types, such as IntRange, LongRange, and CharRange, also contain Iterator interface implementations under the hood. 
They are being used while traversing the range using the for loop under the hood. 
In fact, the range implementing the Iterable interface is called a progression.
Under the hood, the `IntRange`, `LongRange`, and `CharRange` classes inherit from the `IntProgression`, `LongProgression`, and `CharProgression` base classes, and they provide the implementations of the `Iterator` interface internally.

**There's more...**

There is also a convenient way to reverse the order of an already-defined progression. 
We can do so with the extension function provided for the `IntProgression`, `LongProgression`, and `CharProgression` types, which is called `reversed()`. 
It returns new instances of progressions with a reversed order of elements.
Here is an example of how to use the `reversed()` function:

```kotlin
val daysOfYear: IntRange = 1..365
for(day in daysOfYear.reversed()) {
    println("Remaining days: $day")
}
```

The preceding for loop prints the following text to the console:

```
Remaining days: 365
Remaining days: 364
Remaining days: 363
…
Remaining days: 2
Remaining days: 1
```

The Kotlin standard library offers also another handy extension function called `until()`, which allows the declaration of ranges that don't include the last element. 
It is pretty useful when working with classes that contain internal collections and don't provide elegant interfaces to access them.
A good example would be the Android `ViewGroup` class, which is a container for the child View type objects.
The following example presents how to iterate through the next indexes of any given `ViewGroup` instance children in order to modify the state of each of the children:

```kotlin
val container: ViewGroup = activity.findViewById(R.id.container) as ViewGroup
(0 until container.childCount).forEach {
    val child: View = container.getChildAt(it)
    child.visibility = View.INVISIBLE
}

```

The `until()` **infix** function helps to make the loop conditions clean and natural to understand.

**See also**

• This recipe gave us an insight into how Kotlin standard library implementations of ranges for primitives are easy to work with.
A problem can appear if we want to traverse non-primitive types using the for a loop.
However, it turns out we can easily declare a range for any Comparable type. 
This will be shown in the Building custom progressions to traverse dates' recipe.

• As you have noticed, we are using the `in` an operator to specify the object that is being iterated in the loop. 
However, there are other scenarios where the `in` and `!in` operators can be used together with ranges.
We will investigate them in more depth in the Using range expressions with flow control statements recipe.


**Traversing through ranges using progression with a custom step value**

Besides, doing so for the `Iterator` instances, progressions implementations for integral types, such as the `Int`, `Long`, and `Char` types, also include the step property. 
The step value specifies the intervals between the subsequent elements of a range.
By default, the step value of a progression is equal to `1`.
In this recipe, we are going to learn how to traverse a range of alphabet characters with a step value equal to `2`. 
In the result, we want to have every second alphabet letter printed to the console.

**Getting ready**

The Kotlin standard library provides a convenient way of creating progression with a custom step value. 
We can do so using an extension function for progressions of integral types called `step()`. 
We can also benefit from the **infix** notation and declare a progression with a custom step, as follows:

```kotlin
val progression: IntProgression = 0..1000 step 100
If we were to use progression in the for loop, it would iterate 10 times:
val progression: IntProgression = 0..1000 step 100
for (i in progression) {
    println(i)
}
```

We could also achieve the same result by iterating with the while loop, as follows:

```kotlin
var i = 0
while (i <= 1000) {
    println(i)
    i += 100
}

```

_How to do it..._

• Declare a range of the `Char` type using the `downTo()` function:

`'z' downTo 'a'`

• Convert the range to a progression with a custom step value using the `step()` function:

`'z' downTo 'a' step 2`

• Use the `forEach()` function to iterate through the elements of the progression and print each of them to the console:

`('z' downTo 'a' step 2).forEach { character ->print(character) }`

_How it works..._

In the result, we are going to get the following code printed to the console:

`zxvtrpnljhfdb`

In the beginning, we declared a range containing all the alphabet characters in decreasing order with the `downTo()` function.
Then, we transformed the range the custom progression containing every second character with the `step()` function.
Finally, we are using the `Iterable.forEach()` function to iterate through the next elements of the progression and print each of them to the console.
The `step()` extension function is available for the `IntProgression`, `LongProgression`, and `CharProgression` types.
Under the hood, it creates a new instance of a progression copying the properties of the original one and setting up the new step value.

**See also**

• Apart from iteration, range expressions are useful for defining flow control conditions. 

You can read more about this in the Using range expressions with flow control statements recipe.


**Building custom progressions to traverse dates**

Kotlin provides built-in support for ranges of primitive types.
In the previous recipes, we worked with the IntRange and `CharRange` types, which are included in the Kotlin standard library.
However, it is possible to implement a custom progression for any type by implementing the Comparable interface. 
In this recipe, we will learn how to create a progression of the `LocalDate` type and discover how to traverse the dates the easy way.

**Getting ready:**

In order to accomplish the task, we need to start by getting familiar with the `ClosedRange` and `Iterator` interfaces. 
We need to use them to declare a custom progression for the `LocalDate` class:
```kotlin
 interface ClosedRange<T: Comparable<T>> {
     val start: T
     val endInclusive: T
     operator fun contains(value: T): Boolean {
        return value >= start && value <= endInclusive
    }
     fun isEmpty(): Boolean = start > endInclusive
}
```

The Iterator interface provides information about the subsequent values and their availability:

```kotlin
 interface Iterator<out T> {
     operator fun next(): T
     operator fun hasNext(): Boolean
}
```
The `ClosedRange` interface provides the minimum and maximum values of the range. 
It also provides the `contains(value: T): Boolean` and `isEmpty(): Boolean` functions, which check whether a given value belongs to the range and whether the range is empty respectively.
Those two functions have default implementations provided in the `ClosedRange` interface. 
As the result, we don't need to override them in our custom implementation of the `ClosedRange` interface.


_How to do it..._

• Let's start with implementing the Iterator interface for the LocalDate type. 
We are going to create a custom `LocalDateIterator` class, which implements the `Iterator<LocalDate>` interface:
```kotlin
class DateIterator(startDate: LocalDate, val endDateInclusive: LocalDate, val stepDays: Long) : Iterator<LocalDate> {
    private var currentDate = startDate
    override fun hasNext() = currentDate <= endDateInclusive
    override fun next(): LocalDate {
        val next = currentDate
        currentDate = currentDate.plusDays(stepDays)
        return next
    }
}
```
• Now, we can implement the progression for the LocalDate type.

Let's create a new class called DateProgression, which is going to implement the `Iterable<LocalDate>` and `ClosedRange<LocalDate>` interfaces:

```kotlin
class DateProgression(override val start: LocalDate, override val endInclusive: LocalDate, val stepDays: Long = 1) : Iterable<LocalDate>, ClosedRange<LocalDate> {
    override fun iterator(): Iterator<LocalDate> {
        return DateIterator(start, endInclusive, stepDays)
    }

    infix fun step(days: Long) = DateProgression(start, endInclusive, days)
}
```

• Finally, declare a custom rangeTo operator for the LocalDate class:

`operator fun LocalDate.rangeTo(other: LocalDate) = DateProgression(this, other)
`



_How it works..._

Now, we are able to declare range expressions for the LocalDate type. 
Let's see how to use our implementation.
In the following example, we will use our custom `LocalDate.rangeTo` operator implementation in order to create a range of dates and iterate its elements:

```kotlin
val startDate = LocalDate.of(2020, 1, 1)
val endDate = LocalDate.of(2020, 12, 31)
for (date in startDate..endDate step 7) {
    println("${date.dayOfWeek} $date ")
}
```
As a result, we are going to have the dates printed out to the console with a week-long interval:
```
WEDNESDAY 2020-01-01
WEDNESDAY 2020-01-08
WEDNESDAY 2020-01-15
WEDNESDAY 2020-01-22
WEDNESDAY 2020-01-29
WEDNESDAY 2020-02-05
...
WEDNESDAY 2020-12-16
WEDNESDAY 2020-12-23
WEDNESDAY 2020-12-30
```
The DateIterator class holds three `properties—currentDate: LocalDate`, `endDateInclusive: LocalDate`, and `stepDays: Long`.
In the beginning, the currentDate property is initialized with the startDate value passed in the constructor.
Inside the `next()` function, we are returning the currentDate value and updating it to the next date value using a given stepDays property interval.
The DateProgression class combines the functionalities of the `Iterable<LocalDate>` and `ClosedRange<LocalDate>` interfaces. 
It provides the Iterator object required by the Iterable interface by returning the DateIterator instance. 
It also overrides the start and endInclusive properties of the ClosedRange interface.
There is also the stepDays property with a default value equal to `1`.
Note that every time the step function is called, a new instance of the DateProgression class is being created.
You can follow the same pattern to implement custom progressions for any class that implements the Comparable interface.


**Using range expressions with flow control statements**

Apart from iterations, Kotlin range expressions can be useful when it comes to working with control flow statements.
In this recipe, we are going to learn how to use range expressions together with if and when statements in order to tune up the code and make it safe. 
In this recipe, we are going to consider an example of using the in operator to define a condition of an if statement.

**Getting ready**

Kotlin range expressions—represented by the ClosedRange interface—implement a `contains(value: T): Boolean` function, which returns an information if a given parameter belongs to the range. 
This feature makes it convenient to use ranges together with control flow instructions.
The contains() function has also its equivalent operator, in, and its negation, !in. 

_How to do it..._

• Let's create a variable and assign to it a random integer value:

`val randomInt = Random().nextInt()`

• Now we can check whether the randomInt value belongs to the scope of integers from 0 to 10 inclusive using range expressions:

```kotlin
if (randomInt in 0..10) {
print("$randomIntbelongs to <0, 10>range")
} else {
    print("$randomIntdoesn't belong to <0, 10>range")
}
```





_How it works..._

We have used a range expression together with the in an operator in order to define a condition for the if statement. 
The condition statement is natural to read and concise.
In contrast, an equivalent classic implementation would look like this:

```kotlin
val randomInt = Random(20).nextInt()
if (randomInt >= 0 &&randomInt <= 10) {
print("$randomIntbelongs to <0, 10>range")
} else {
    print("$randomIntdoesn't belong to <0, 10>range")
}
```

No doubt, the declarative approach using the range and in an operator is cleaner and easier to read, compared to classic, imperative-style condition statements.

_There's more..._

Range expressions can enhance use of when expression as well. 
In the following example, we are going to implement a simple function that will be responsible for mapping a student's exam score to a corresponding grade.
Let's say we have the following enum class model for student grades:

`enum class Grade { A, B, C, D }`

We can define a function that will map the exam score value, in the 0 to 100 % range, to the proper grade (A, B, C, or D) using a when expression, as follows:

```kotlin
fun computeGrade(score: Int): Grade =
when (score) {
    in 90..100 -> Grade.A
    in 75 until 90 -> Grade.B
    in 60 until 75 -> Grade.C
    in 0 until 60 -> Grade.D
    else -> throw IllegalStateException("Wrong score value!")
}
```

Using ranges together with the in operator makes the implementation of the `computeGrade()` function much cleaner and more natural than the classic equivalent implementation using traditional comparison operators, such as `<`, `>`, `<=`, and `>=`.

**See also**

• If you'd like to discover more about lambdas, the **infix** notation, and operator overloading, go ahead and dive into [Chapter 2](), Expressive Functions and Adjustable Interfaces

**Discovering the concept of sequences**

In terms of **high-level** functionalities, the s.Sequence and Collection data structures are nearly the same. They both allow iteration through their elements. There are also many powerful extension functions in the Kotlin standard library that provide declarative-style data-processing operations for each of them. However, the s.Sequence data structure behaves differently under the hood—it delays any operations on its elements until they are finally consumed. It instantiates the subsequent elements on the go while traversing through them. These characteristics of s.Sequence, called lazy evaluation, make the structure quite similar to the Java concept, Stream. To understand all of this better, we are going to implement a simple data-processing scenario to analyze the efficiency and behavior of s.Sequence in implementation scenarios, and contrast our findings with Collection-based implementation.

**Getting ready:**

Let's consider the following example:

```kotlin
val collection = listOf("a", "b", "c", "d", "e", "f", "g", "h")
val transformedCollection = collection.map {
    println("Applying map function for $it")
    it
}
println(transformedCollection.take(2))
```

In the first line, we created a list of strings and assigned it to the collection variable.
Next, we are applying the `map()` function to the list.
Mapping operation allows us to transform each element of the collection and return a new value instead of the original one.
In our case, we are using it just to observe that `map()` was invoked by printing the message to the console. 
Finally, we want to filter our collection to contain only the first two elements using the `take()` function and print the content of the list to the console.

In the end, the preceding code prints the following output:

```
Applying map function for a
Applying map function for b
Applying map function for c
Applying map function for d
Applying map function for e
Applying map function for f
Applying map function for g
Applying map function for h
[a, b]
```

As you can see, the `map()` function was properly applied to every element of the collection, and the `take()` function has properly filtered the elements of the list. 
However, it would not be an optimal implementation if we were working with a larger dataset. 
Preferably, we would like to wait with the execution of the data-processing operations until we know what specific elements of the dataset we really need, and then apply those operations only to those elements. 
It turns out that we can easily optimize our scenario using the s.Sequence data structure. 
Let's explore how to do it in the next section.

_How to do it..._

• Declare a s.Sequence instance for the given elements:

`val sequence = sequenceOf("a", "b", "c", "d", "e", "f", "g", "h")`

• Apply the mapping operation to the elements of the sequence:

```kotlin
val sequence = sequenceOf("a", "b", "c", "d", "e", "f", "g", "h")
val transformedSequence = sequence.map {
    println("Applying map function for $it")
    it
}
```

• Print the first two elements of the sequence to the console:

```kotlin
val sequence = sequenceOf("a", "b", "c", "d", "e", "f", "g", "h")

val transformedSequence = sequence.map {
    println("Applying map function for $it")
    it
}
println(transformedSequence.take(2).toList())
```

_How it works..._

The s.Sequence-based implementation is going to give us the following output:

```
Applying map function for a
Applying map function for b
[a, b]
```

As you can see, replacing the Collection data structure with the s.Sequence type allows us to gain the desired optimization.
The scenario considered in this recipe was implemented identically—first, using List, then using the s.Sequence type.
However, we can notice the difference in the behavior of the s.Sequence data structure compared to that of Collection.
The `map()` function was applied only to the first two elements of the sequence, even though the `take()` function was called after the mapping transformation declaration. 
It's also worth noting that in the example using Collection, the mapping was performed instantly when the `map()` function was **invoked**. 
In the case of s.Sequence, mapping was performed at the time of the evaluation of its elements while printing them to the console, and more precisely while converting s.Sequence to the List type with the following line of code:

`println(transformedSequence.take(2).toList())`

**There's more...**

There is a convenient way of transforming Collection to s.Sequence. We can do so with the asSequence() extension function provided by the Kotlin standard library for the Iterable type. In order to convert a s.Sequence instance into a Collection instance, you can use the toList() function. 

**See also**

• Thanks to the feature of s.Sequence lazy evaluation, we have avoided needless calculations, increasing the performance of the code at the same time.

Lazy evaluation allows the implementation of sequences with a potentially infinite number of elements and turns out to be effective when implementing algorithms as well.

• You can explore a s.Sequence-based implementation of the Fibonacci algorithm in the Applying sequences to solve algorithmic problems recipe.

It presents, in more detail, another useful function for defining sequences called `generateSequence()`.

**Applying sequences to solve algorithmic problems:**

In this recipe, we are going to get familiar with the `generateSequence()` function, which provides an easy way to define the various types of sequences. 
We will use it to implement an algorithm for generating Fibonacci numbers.

**Getting ready**

The basic variant of the generateSequence() function is declared as follows:

`fun <T : Any>generateSequence(nextFunction: () ->T?): s.Sequence<T>`

It takes one parameter called nextFunction, which is a function that returns the next elements of the sequence.
Under the hood, it is being invoked by the `Iterator.next()` function, inside the s.Sequence class' internal implementation, and allows instantiation of the next object to be returned while consuming the sequence values.
In the following example, we are going to implement a finite sequence that emits integers from 10 to 0:

```kotlin
var counter = 10
val sequence: s.Sequence<Int>= generateSequence {
    counter--.takeIf { value: Int -> value >= 0 }
}
print(sequence.toList())
```


The `takeIf()` function applied to the current counter value checks whether its value is greater or equal to `0`. 
If the condition is fulfilled, it returns the counter value; otherwise, it returns null.
Whenever null is returned by the `generateSequence()` function, the sequence stops. After the takeIf function returns the value, the counter value is post-decremented.
The preceding code will result in the following numbers being printed to the console:

`[10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]`

The subsequent values of the Fibonacci sequence are generated by summing up their two preceding ones.
Additionally, the two first values are equal to `0` and `1`.
In order to implement such a sequence, we are going to use an extended variant of the `generateSequence()` function with an additional seed parameter, declared as follows:

`fun <T : Any>generateSequence(seed: T?, nextFunction: (T) ->T?): s.Sequence<T>`

_How to do it..._

• Declare a function called `fibonacci()` and use the `generateSequence()` function to define a formula for the next elements of the sequence:

```kotlin
fun fibonacci(): s.Sequence<Int> {
    return generateSequence(Pair(0, 1)) { 
        Pair(it.second, it.first + it.second) }.map { it.first }
}
```

• Use the `fibonacci()` function to print the next Fibonacci numbers to the console:

`println(fibonacci().take(20).toList())`



_How it works..._

As a result, we are going to get the next 20 Fibonacci numbers printed to the console:

`[0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181]`

The additional seed parameter in the `generateSequence()` provides a starting value. 
The `nextFunction()` function is applied to the seed while computing the second value. 
Later on, it is generating each following element using its preceding value. 
However, in the case of the Fibonacci sequence, we have two initial values, and we need a pair of preceding values in order to compute the next value.
For this reason, we wrapped them in Pair type instances. 
Basically, we are defining a sequence of `Pair<Int, Int>` type elements, and in each `nextFunction()` call, we are returning a new pair that holds the values updated accordingly. 
At the end, we just need to use the `map()` function to replace each Pair element with the value of its first property.
As a result, we are getting an infinite sequence of integer types returning the subsequent Fibonacci numbers.
