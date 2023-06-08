## Theory: Jumps and returns

Have you ever had a situation when you wanted to have more control over loop iterations and wished you could jump out of a loop manually? For example, when a special condition occurs or when you need to process only certain cases and skip others? Fortunately, Kotlin has a special tool for it: **structural jump expressions**. There are three structural jump expressions in Kotlin: `break`, `continue`, and `return`. What makes them even more flexible is **labels**. Let's take a look at these features now.

## The Break Statement

A `break` expression is used to terminate the nearest enclosing loop. You can literally **break** the loop with your own condition. In the example below, we used it in the if-else case:

```kotlin
for (i in 1..10) {  
   // do something
   if (checkCondition){  
       break 
   }  
}
```

The `for` loop terminates when the `if` condition executes the `break` expression. Here is a more specific example:

```kotlin
println("Before the loop")  
for (i in 1..10) {  
    if (i == 3) {  
        break  
    }  
    println(i)  
}  
println("After the loop")  
```

In the third iteration, the `break` expression will leave the `for` loop. The output is:

```kotlin
Before the loop  
1
2
After the loop
```

As you can see, nothing happens with the code before and after the loop – `break` just terminates the nearest enclosing loop.

## The Continue Statement

Let’s have a look at the next keyword, `continue`. It lets us **proceed** to the next iteration of the enclosing loop. In other words, in case you want to skip the current iteration of the loop code with your own condition, you may use `continue`. For instance:

```kotlin
var result = ""
for (i in "hello world") {
    if (i == 'o') continue
    result += i
}
println(result)
```

This code will produce the following output:

```kotlin
hell wrld
```

You should use `continue` when you need to ignore one iteration. But be careful – code like this is really hard to read.

## Inner loops and structural jump expressions

Oftentimes, you may have one loop within another. Let's see how `break` and `continue` work in such a case.

Look at the following example:

```kotlin
for (i in 1..4) {
    for (j in 1..4) {
        if (j == 2) continue // you want to ignore j = 2
        if (i <= j) break    // you will print the string if i is greater than j
        println("i = $i, j = $j")
    }
    println("Finished to examine i = $i")
}
```

The result is:

```no-highlight
Finished to examine i = 1
i = 2, j = 1
Finished to examine i = 2
i = 3, j = 1
Finished to examine i = 3
i = 4, j = 1
i = 4, j = 3
Finished to examine i = 4
```

As you can see, `break` and `continue` terminate only one loop. But what should we do if we need to terminate the outer loop? In this case, you should use labels. Let' take a look at them.

## Labels

What is a **label**? Technically, it's just an identifier with the `@` sign at the end, for example: `loop@`, `kotlin@`. You can use almost any word with it, apart from the reserved words in Kotlin. What do you need labels for? Well, you can add your label to a loop:

```kotlin
loop@ for (i in 1..10) {
    // do something
}
```

Now, let's combine labels with the jump expressions described above and see what happens.

Here is an example of using the `break` statement with a label:

```kotlin
loop@ for (i in 1..3) { 
    for (j in 1..3) {
        println("i = $i, j = $j")   
        if (j == 3) break@loop  
    }  
}  
```

Both the inner and outer `for` loops are terminated at the third iteration. The output is this:

```kotlin
i = 1, j = 1
i = 1, j = 2
i = 1, j = 3
```

So, labels help us break not only the inner loop but also the outer one. Once again, `break` with a label terminates the execution of the labeled loop.

Now, let's modify the previous example and use the `continue` keyword:

```kotlin
loop@ for (i in 1..3) {
    for (j in 1..3) {
        for (k in 1..3) {
            if (k == 2) continue@loop
            println("i = $i, j = $j, k = $k")
        }
    }
}
```

The output result is as follows:

```kotlin
i = 1, j = 1, k = 1
i = 2, j = 1, k = 1
i = 3, j = 1, k = 1
```

With the label, we changed the default behavior of the `continue` keyword. Instead of skipping the execution of the inner loop at the second iteration and continuing with the next iteration, we returned to the outer loop and continued its execution. Without labels**,** such a trick wouldn't be possible .

## When and structural jump expressions

Let's take a look at another useful feature of Kotlin 1.4. Now you can use `break` and `continue` inside `when` without labels! See how it works.

Before Kotlin 1.4, you needed to write something like this:

```kotlin
Loop@for (i in 1..10) {
    when (i) {
        3 -> continue@Loop
        6 -> break@Loop
        else -> println(i)
    }
}
```

It is easy to understand that in this example, you skip the iteration when *i* equals 3 and break the loop when it equals 6. The result is:

```no-highlight
1
2
4
5
```

Now, let's rewrite this example!

```kotlin
for (i in 1..10) {
    when (i) {
        3 -> continue
        6 -> break
        else -> println(i)
    }
}
```

This code does the same thing but looks more clear!