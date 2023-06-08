## Theory: Hierarchy of exceptions

For different unexpected situations, there are different specialized types of exceptions. They are all related, making up a hierarchy of types. Here we’ll consider the key points of this hierarchy, review the most popular types of Kotlin exceptions that you may encounter, and see how they connect to each other.

##### Subtype and supertype

First of all, let's figure out the terminology. Types in Kotlin are organized into a hierarchy of subtype-supertype relationships. What are subtypes and supertypes? Let's look at an example.

Do you like coffee or tea? Well, both are drinks. We can state the fact that coffee and tea are both related to a specific type: drink. In Kotlin terms, coffee and tea are **subtypes** of drink, while the drink is a **supertype** for these two types:

<img src="https://trello-attachments.s3.amazonaws.com/5ece3b685557a6616ded60a7/6053456592b92c0b24817ef9/de29ae2bc98610ffad481e77c49c4461/%D0%9E%D0%B1%D1%8A%D1%8F%D1%81%D0%BD%D0%B5%D0%BD%D0%B8%D0%B5.svg" alt="Объяснение.svg" style="zoom:25%;" />

The **subtype** is a datatype that is related to another datatype (*supertype*) and inherits common characteristics and rules of behavior from it. Note that the rules of behavior of different *subtypes* may vary. In this example, all kinds of drinks have some sort of color, but coffee and tea have different colors.

Logically, a **supertype** is a type whose properties and methods specify the characteristics and rules of behavior that its subtypes will follow.

##### Overview of the exception hierarchy

The following picture illustrates a simplified hierarchy of exceptions:

![img](https://ucarecdn.com/f7ffede8-355a-4d41-b140-61e470f02a33/)


As you can see, the foundation of this hierarchy is the type `Throwable`. It is the root of the hierarchy, and all exceptions somehow belong to this type. It provides a set of useful methods for handling exceptions. And, since all exceptions in Kotlin are descendants of `Throwable`, all of these methods are available for each exception; in the following topics we’ll look into it further.

The type `Throwable` has two direct subtypes: `Error` and `Exception`. An `Error` indicates serious problems that a reasonable application should not try to process. We shouldn't think about handling errors in the program code. Here we’ll focus on the `Exception`; when you develop an application, you normally will catch and process exactly exceptions. Keep in mind that subtype and supertype are relative terms, so a given type doesn't have to be one or the other. In this case, `Exception` is a subtype of `Throwable` and also the supertype of `RuntimeException`.

Subtypes of the `Exception` type, such as `RuntimeException` and `IOException`, deal with exceptional events inside applications. Today we will focus in more detail just on the varieties of `RuntimeException`.

##### RuntimeException

The `RuntimeException` type is a rather special subtype of `Exception`. Subtypes of `RuntimeException`, such as `ArithmeticException` and `NumberFormatException`, can be thrown during the normal program execution. These exceptions are usually caused by insufficient checks in the program code, and can be prevented programmatically.

The next picture demonstrates a hierarchy of subtypes descended from `RuntimeException`:

![Иерархия.svg](https://trello-attachments.s3.amazonaws.com/5ece3b685557a6616ded60a7/6053456592b92c0b24817ef9/7bb3ba1aaf744e15b61124ce053f374e/%D0%98%D0%B5%D1%80%D0%B0%D1%80%D1%85%D0%B8%D1%8F.svg)

Now, let's move on to practice! Consider the most common types of exceptions; all of those that are discussed below belong to the type `RuntimeException`.

##### ArithmeticException

**`ArithmeticException`** is the runtime exception that is thrown when the code attempts an illegal arithmetic operation. Consider an extremely common example:

```kotlin
val example = 2 / 0 // throws ArithmeticException
```

Since division by zero is not allowed, this line will throw an `ArithmeticException`, and the program execution will be interrupted.

##### NumberFormatException

During calculations you can get another type of exception: **`NumberFormatException`**. It is thrown when a method expects to receive a number, but the actual input is something else. Take an example:

```kotlin
val string = "It's not a number"
val number = string.toInt() // throws NumberFormatException
```

Here we tried converting the string `"It's not a number"` to an `Int` number, and since this is obviously not possible, so a `NumberFormatException` was thrown.

##### IndexOutOfBoundsException

**`IndexOutOfBoundsException`** occurs when you access some non-existent index. For example, if you are trying to access the tenth element of a collection that has only 5 elements, your index will be out of bounds.

This type of exception has further subtypes. This example throws a `StringIndexOutOfBoundsException`, a special type of `IndexOutOfBoundsException` used for (you guessed it) strings:

```kotlin
val sequence = "string"
println(sequence[10])   // throws StringIndexOutOfBoundsException
```

As you may remember, a string can be represented as a set of characters, each of which is assigned an index. If the string does not contain the character with the specified index, a `StringIndexOutOfBoundsException` will be thrown.

##### Conclusion

All exceptions in Kotlin are contained in a single hierarchy. All of them belong to the type `Exception`, which in turn is a subtype of `Throwable`.

One important heir of `Exception` is `RuntimeException`: this type includes exceptions that can be thrown while the program is being executed. Its subtypes include `ArithmeticException`, `NumberFormatException`, and `IndexOutOfBoundsException`.

What if none of these exceptions provide exactly what you want? In the following topics, you will learn how to create custom types of exceptions, which will also be embedded in the existing hierarchy.