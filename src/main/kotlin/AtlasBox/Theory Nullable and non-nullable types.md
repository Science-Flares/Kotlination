## Theory: Nullable and non-nullable types

If you're familiar with Java, you've probably heard something like **NullPointerException** (**NPE**). If you read about this first time, you're a lucky person, because **NPE** is the most frequent exception which will make you so unhappy. Moreover, there isn't any convenient way how to prevent such an exception in Java. Lucky you, Kotlin has a real remedy for **NPE**. Though, first, we need to learn about the special type of references.

##### Nullability

There are just a few ways how **NPE** may occur in Kotlin:

1. explicit call of `throw NullPointerException()` ;
2. `!!` syntax;
3. bad initializations, such as constructors and superclass constructors.

If you don't know anything about these things, it's just fine. You will learn about them later. For now, just remember that you don't have to pay as much attention to **NPE** as in Java and that you can concentrate on real tasks. Don't forget that Kotlin is a pragmatic language. So, what do we have?

First of all, every reference in Kotlin can be either nullable or not. Let's say we want to define a String variable, but we are not sure what it might be initially:

```kotlin
var name: String = null
```

So, what is `null` above? It just means that the `name` variable doesn't have a certain value. This code won't compile, because we declared a non-null variable. How can we fix it? Pretty easy:

```kotlin
var name: String? = null
```

As you can see, we just added a `?` sign right after the type of our variable. We marked our `name` variable as nullable. We can also do the same with other types, like `Int` or `Long`:

```kotlin
var age: Int? = null
```

So, without a `?` sign in the type you can't assign `null` to a variable.

##### Accessing nullable variables

Now try to guess what happens if you try to access this variable property:

```kotlin
var name: String? = null
print(name.length)
```

If you think, there will be an error, you're right! This code won't even compile. What can we do, then? Of course, we can add a common check for `null` like this:

```kotlin
if (name != null) {
    print(name.length)
}
```

If the `name` is null, the `print` won't be called.

Or we can access the length this way:

```kotlin
print(name?.length)
```

Here `null` is printed.

This `?.` pair of symbols is called a **safe call** in Kotlin. We will dig into this concept in a special topic. Right now there is enough information for you to practice.

##### Billion-dollar mistake

So, Kotlin introduces nullable types that differ from non-nullable ones. In old languages like Java there is no difference because every type is nullable. Therefore, in many languages it's not required to check a nullable variable against `null` before accessing it. This can cause lots of program crashes, and in 2009, Tony Hoare, a British Computer Scientist who invented the concept of `null` reference, described it as "**billion-dollar mistake**":



I call it my billion-dollar mistake. It was the invention of the null reference in 1965. At that time, I was designing the first comprehensive type system for references in an object-oriented language ([ALGOL W](https://en.wikipedia.org/wiki/ALGOL_W)). My goal was to ensure that all use of references should be absolutely safe, with checking performed automatically by the compiler. But I couldn't resist the temptation to put in a null reference, simply because it was so easy to implement. This has led to innumerable errors, vulnerabilities, and system crashes, which have probably caused a billion dollars of pain and damage in the last forty years.



##### Conclusion

There are two different types of references in Kotlin. Obviously, with non-null types you don't have to pay as much attention as with nullable ones. Be careful with your references and try to define a type that is appropriate in that particular case. However, don't worry too much for in most situations the compiler will point at your mistakes.