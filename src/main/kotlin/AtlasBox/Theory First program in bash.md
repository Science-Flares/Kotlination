## Theory: First program in bash

After you've successfully installed Linux, you can start exploring the command line. Working in a terminal at first may seem confusing because you need to deal with operating systems using plain-text commands. However, if you master it, you will be able to automate every single bit of the whole system. Also, a good knowledge of the Linux shell will help you write scripts to algorithmize any frequent actions. It is easier than using the same commands every time you need them. So, let's start by writing your first bash program!

##### Echo utility

Bash is an enhanced version of the Bourne shell. Its name stands for "Bourne Again Shell" and also can be understood as a "Born again" shell. Bash is needed to receive user commands and send them to the operating system for further processing. In addition to Bash, there are other versions of the command shells. However, we will use Bash in our topics, as it is one of the most popular modern shells in the Linux environment.

Usually, the very first task is to output a text line. In Bash, there is a special utility for this. The echo command is simple but useful, though it can only output text lines to the terminal. The syntax is also easy: `echo <options> <text_line>`. Let's try it! For the first time, we can skip the options and just output a line in the terminal.

```no-highlight
$ echo Hello, Linux!
Hello, Linux!
```

If you want to output a text with several lines, use the quotation marks:

```no-highlight
$ echo "Hello, Linux!" "Here I am!"
```

Okay, but what if you need to output each phrase in a separate line? Then you should add a `-e` flag as an option into the echo command. This flag enables support for outputting escape sequences such as linefeed `\n`:

```no-highlight
$ echo -e "Hello, Linux! \nHere I am!"
Hello, Linux!
Here I am!
```

In case you don't want to output the linefeed after the message, add `-n` flag to the echo command.

Great, the first step is complete and now you know how to output the lines of text! What's next?

##### Hello World!

The next step is to write the first 'Hello, world!' script in Bash. Create the file named `hello_world.sh` (you can use a text editor).

```no-highlight
#!/usr/bin/env bash
echo 'Hello, world!'
```

Our script consists of two lines. The first one contains the path to the shell interpreter with which you need to execute the command. In this example, it's `/usr/bin/env bash` that searches the Bash executable in your environment. At the beginning of this line, there is a sequence of two symbols `#!`, that's called a **shebang.** It is a directive for the Linux program loader that specifies a program that will run the script. The second line is already familiar to you, it's the `echo` command.



Shebang defines an interpreter for the desired programming language. For example, to run a Python script add a line `#!/usr/bin/env python3`. You can run a script from the command line like this: `./file.py`.



OK, you've got a script. How to run it?

##### Run a shell script

There are three ways to run a shell script:

- You can open the corresponding directory with your file and type `bash hello_world.sh`. This way requires only one command, but do not forget to specify that you want to launch the script with Bash.
- You can make the file executable by typing `chmod +x hello_world.sh`. Then you can run it using `./hello_world.sh` also from its directory. Here, you can skip specifying Bash, but remember to make the file executable.
- You can replace the file with `bin` by `sudo cp hello_world.sh /usr/local/bin` and then use only the filename `hello_world.sh` to run the script. As you can see, you may also need `sudo` (superuser access) to put your file in the right place. This way implies that you know how to change a directory in Bash and what `sudo` mode is. And the advantage of this is that the final command consists of a filename only.

Another approach is to add the directory to your `PATH` variable, but we've learned a lot today, so we'll tell you how to work with Linux system variables in the next topics.



If you are not familiar with `chmod`, `cp` and `sudo`, please, don't worry, we'll explain them later in detail. For now, you just need to understand how we use them.



##### Conclusion

Congratulations! Now you know how to create shell scripts and run them in Bash! We hope you found this information useful and now you're one more step closer to understanding how Linux works.Generics: in, out, where﻿

[Edit page](https://github.com/JetBrains/kotlin-web-site/edit/master/docs/topics/generics.md)

Last modified: 08 September 2021

Classes in Kotlin can have type parameters, just like in Java:

```kotlin
class Box<T>(t: T) {
    var value = t
}
```

Copied!

To create an instance of such a class, simply provide the type arguments:

```kotlin
val box: Box<Int> = Box<Int>(1)
```

Copied!

But if the parameters can be inferred, for example, from the constructor arguments, you can omit the type arguments:

```kotlin
val box = Box(1) // 1 has type Int, so the compiler figures out that it is Box<Int>
```

Copied!

## Variance﻿

One of the trickiest aspects of Java's type system is the wildcard types (see [Java Generics FAQ](http://www.angelikalanger.com/GenericsFAQ/JavaGenericsFAQ.html)). Kotlin doesn't have these. Instead, Kotlin has declaration-site variance and type projections.

Let's think about why Java needs these mysterious wildcards. The problem is explained well in [Effective Java, 3rd Edition](http://www.oracle.com/technetwork/java/effectivejava-136174.html), Item 31: *Use bounded wildcards to increase API flexibility*. First, generic types in Java are *invariant*, meaning that `List<String>` is *not* a subtype of `List<Object>`. If `List` were not *invariant*, it would have been no better than Java's arrays, as the following code would have compiled but caused an exception at runtime:

```java
// Java
List<String> strs = new ArrayList<String>();
List<Object> objs = strs; // !!! A compile-time error here saves us from a runtime exception later.
objs.add(1); // Put an Integer into a list of Strings
String s = strs.get(0); // !!! ClassCastException: Cannot cast Integer to String
```

Copied!

Java prohibits such things in order to guarantee run-time safety. But this has implications. For example, consider the `addAll()` method from the `Collection` interface. What's the signature of this method? Intuitively, you'd write it this way:

```java
// Java
interface Collection<E> ... {
    void addAll(Collection<E> items);
}
```

Copied!

But then, you would not be able to do the following (which is perfectly safe):

```java
// Java
void copyAll(Collection<Object> to, Collection<String> from) {
    to.addAll(from);
    // !!! Would not compile with the naive declaration of addAll:
    // Collection<String> is not a subtype of Collection<Object>
}
```

Copied!

(In Java, you probably learned this the hard way, see [Effective Java, 3rd Edition](http://www.oracle.com/technetwork/java/effectivejava-136174.html), Item 28: *Prefer lists to arrays*)

That's why the actual signature of `addAll()` is the following:

```java
// Java
interface Collection<E> ... {
    void addAll(Collection<? extends E> items);
}
```

Copied!

The *wildcard type argument* `? extends E` indicates that this method accepts a collection of objects of `E` *or a subtype of* `E`, not just `E` itself. This means that you can safely *read* `E`'s from items (elements of this collection are instances of a subclass of E), but *cannot write* to it as you don't know what objects comply with that unknown subtype of `E`. In return for this limitation, you get the desired behavior: `Collection<String>` *is* a subtype of `Collection<? extends Object>`. In other words, the wildcard with an *extends*-bound (*upper* bound) makes the type *covariant*.

The key to understanding why this works is rather simple: if you can only *take* items from a collection, then using a collection of `String`s and reading `Object`s from it is fine. Conversely, if you can only *put* items into the collection, it's okay to take a collection of `Object`s and put `String`s into it: in Java there is `List<? super String>`, a *supertype* of `List<Object>`.

The latter is called *contravariance*, and you can only call methods that take `String` as an argument on `List<? super String>` (for example, you can call `add(String)` or `set(int, String)`). If you call something that returns `T` in `List<T>`, you don't get a `String`, but rather an `Object`.

Joshua Bloch gives the name *Producers* to objects you only *read from* and *Consumers* to those you only *write to*. He recommends:

> ### 
>
> 
>
> "For maximum flexibility, use wildcard types on input parameters that represent producers or consumers", and proposes the following mnemonic:
>
> *PECS stands for Producer-Extends, Consumer-Super.*

> ### 
>
> 
>
> If you use a producer-object, say, `List<? extends Foo>`, you are not allowed to call `add()` or `set()` on this object, but this does not mean that it is *immutable*: for example, nothing prevents you from calling `clear()` to remove all the items from the list, since `clear()` does not take any parameters at all.
>
> The only thing guaranteed by wildcards (or other types of variance) is *type safety*. Immutability is a completely different story.

### Declaration-site variance﻿

Let's suppose that there is a generic interface `Source<T>` that does not have any methods that take `T` as a parameter, only methods that return `T`:

```java
// Java
interface Source<T> {
    T nextT();
}
```

Copied!

Then, it would be perfectly safe to store a reference to an instance of `Source<String>` in a variable of type `Source<Object>` - there are no consumer-methods to call. But Java does not know this, and still prohibits it:

```java
// Java
void demo(Source<String> strs) {
    Source<Object> objects = strs; // !!! Not allowed in Java
    // ...
}
```

Copied!

To fix this, you should declare objects of type `Source<? extends Object>`. Doing so is meaningless, because you can call all the same methods on such a variable as before, so there's no value added by the more complex type. But the compiler does not know that.

In Kotlin, there is a way to explain this sort of thing to the compiler. This is called *declaration-site variance*: you can annotate the *type parameter* `T` of `Source` to make sure that it is only *returned* (produced) from members of `Source<T>`, and never consumed. To do this, use the `out` modifier:

```kotlin
interface Source<out T> {
    fun nextT(): T
}

fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs // This is OK, since T is an out-parameter
    // ...
}
```

Copied!

The general rule is this: when a type parameter `T` of a class `C` is declared `out`, it may occur only in the *out*-position in the members of `C`, but in return `C<Base>` can safely be a supertype of `C<Derived>`.

In other words, you can say that the class `C` is *covariant* in the parameter `T`, or that `T` is a *covariant* type parameter. You can think of `C` as being a *producer* of `T`'s, and NOT a *consumer* of `T`'s.

The `out` modifier is called a *variance annotation*, and since it is provided at the type parameter declaration site, it provides *declaration-site variance*. This is in contrast with Java's *use-site variance* where wildcards in the type usages make the types covariant.

In addition to `out`, Kotlin provides a complementary variance annotation: `in`. It makes a type parameter *contravariant*, meaning it can only be consumed and never produced. A good example of a contravariant type is `Comparable`:

```kotlin
interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}

fun demo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 has type Double, which is a subtype of Number
    // Thus, you can assign x to a variable of type Comparable<Double>
    val y: Comparable<Double> = x // OK!
}
```

Copied!

The words *in* and *out* seem to be self-explanatory (as they’ve already been used successfully in C# for quite some time), and so the mnemonic mentioned above is not really needed. It can in fact be rephrased at a higher level of abstraction:

**[The Existential](https://en.wikipedia.org/wiki/Existentialism) Transformation: Consumer in, Producer out!**:-)

## Type projections﻿

### Use-site variance: type projections﻿

It is very easy to declare a type parameter `T` as `out` and avoid trouble with subtyping on the use site, but some classes *can't* actually be restricted to only return `T`'s! A good example of this is `Array`:

```kotlin
class Array<T>(val size: Int) {
    operator fun get(index: Int): T { ... }
    operator fun set(index: Int, value: T) { ... }
}
```

Copied!

This class can be neither co- nor contravariant in `T`. And this imposes certain inflexibilities. Consider the following function:

```kotlin
fun copy(from: Array<Any>, to: Array<Any>) {
    assert(from.size == to.size)
    for (i in from.indices)
        to[i] = from[i]
}
```

Copied!

This function is supposed to copy items from one array to another. Let's try to apply it in practice:

```kotlin
val ints: Array<Int> = arrayOf(1, 2, 3)
val any = Array<Any>(3) { "" }
copy(ints, any)
//   ^ type is Array<Int> but Array<Any> was expected
```

Copied!

Here you run into the same familiar problem: `Array<T>` is *invariant* in `T`, and so neither `Array<Int>` nor `Array<Any>` is a subtype of the other. Why not? Again, this is because `copy` could have an unexpected behavior, for example, it may attempt to write a `String` to `from`, and if you actually pass an array of `Int` there, a `ClassCastException` will be thrown later.

To prohibit the `copy` function from *writing* to `from`, you can do the following:

```kotlin
fun copy(from: Array<out Any>, to: Array<Any>) { ... }
```

Copied!

This is *type projection*, which means that `from` is not a simple array, but is rather a restricted (*projected*) one. You can only call methods that return the type parameter `T`, which in this case means that you can only call `get()`. This is our approach to *use-site variance*, and it corresponds to Java's `Array<? extends Object>` while being slightly simpler.

You can project a type with `in` as well:

```kotlin
fun fill(dest: Array<in String>, value: String) { ... }
```

Copied!

`Array<in String>` corresponds to Java's `Array<? super String>`. This means that you can pass an array of `CharSequence` or an array of `Object` to the `fill()` function.

### Star-projections﻿

Sometimes you want to say that you know nothing about the type argument, but you still want to use it in a safe way. The safe way here is to define such a projection of the generic type, that every concrete instantiation of that generic type will be a subtype of that projection.

Kotlin provides so-called *star-projection* syntax for this:

- For `Foo<out T : TUpper>`, where `T` is a covariant type parameter with the upper bound `TUpper`, `Foo<*>` is equivalent to `Foo<out TUpper>`. This means that when the `T` is unknown you can safely *read* values of `TUpper` from `Foo<*>`.
- For `Foo<in T>`, where `T` is a contravariant type parameter, `Foo<*>` is equivalent to `Foo<in Nothing>`. This means there is nothing you can *write* to `Foo<*>` in a safe way when `T` is unknown.
- For `Foo<T : TUpper>`, where `T` is an invariant type parameter with the upper bound `TUpper`, `Foo<*>` is equivalent to `Foo<out TUpper>` for reading values and to `Foo<in Nothing>` for writing values.

If a generic type has several type parameters, each of them can be projected independently. For example, if the type is declared as `interface Function<in T, out U>` you could use the following star-projections:

- `Function<*, String>` means `Function<in Nothing, String>`.
- `Function<Int, *>` means `Function<Int, out Any?>`.
- `Function<*, *>` means `Function<in Nothing, out Any?>`.

> ### 
>
> 
>
> Star-projections are very much like Java's raw types, but safe.

## Generic functions﻿

Classes aren’t the only declarations that can have type parameters. Functions can, too. Type parameters are placed *before* the name of the function:

```kotlin
fun <T> singletonList(item: T): List<T> {
    // ...
}

fun <T> T.basicToString(): String { // extension function
    // ...
}
```

Copied!

To call a generic function, specify the type arguments at the call site *after* the name of the function:

```kotlin
val l = singletonList<Int>(1)
```

Copied!

Type arguments can be omitted if they can be inferred from the context, so the following example works as well:

```kotlin
val l = singletonList(1)
```

Copied!

## Generic constraints﻿

The set of all possible types that can be substituted for a given type parameter may be restricted by *generic constraints*.

### Upper bounds﻿

The most common type of constraint is an *upper bound*, which corresponds to Java's `extends` keyword:

```kotlin
fun <T : Comparable<T>> sort(list: List<T>) {  ... }
```

Copied!

The type specified after a colon is the *upper bound*, indicating that only a subtype of `Comparable<T>` can be substituted for `T`. For example:

```kotlin
sort(listOf(1, 2, 3)) // OK. Int is a subtype of Comparable<Int>
sort(listOf(HashMap<Int, String>())) // Error: HashMap<Int, String> is not a subtype of Comparable<HashMap<Int, String>>
```

Copied!

The default upper bound (if there was none specified) is `Any?`. Only one upper bound can be specified inside the angle brackets. If the same type parameter needs more than one upper bound, you need a separate *where*-clause:

```kotlin
fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
    where T : CharSequence,
          T : Comparable<T> {
    return list.filter { it > threshold }.map { it.toString() }
}
```

Copied!

The passed type must satisfy all conditions of the `where` clause simultaneously. In the above example, the `T` type must implement *both* `CharSequence` and `Comparable`.

## Type erasure﻿

The type safety checks that Kotlin performs for generic declaration usages are done at compile time. At runtime, the instances of generic types do not hold any information about their actual type arguments. The type information is said to be *erased*. For example, the instances of `Foo<Bar>` and `Foo<Baz?>` are erased to just `Foo<*>`.

Therefore, there is no general way to check whether an instance of a generic type was created with certain type arguments at runtime, and the compiler [prohibits such `is`-checks](https://kotlinlang.org/docs/typecasts.html#type-erasure-and-generic-type-checks).

Type casts to generic types with concrete type arguments, for example, `foo as List<String>`, cannot be checked at runtime. These [unchecked casts](https://kotlinlang.org/docs/typecasts.html#unchecked-casts) can be used when type safety is implied by high-level program logic but cannot be inferred directly by the compiler. The compiler issues a warning on unchecked casts, and at runtime, only the non-generic part is checked (equivalent to `foo as List<*>`).

The type arguments of generic function calls are also only checked at compile time. Inside the function bodies, the type parameters cannot be used for type checks, and type casts to type parameters (`foo as T`) are unchecked. However, [reified type parameters](https://kotlinlang.org/docs/inline-functions.html#reified-type-parameters) of inline functions are substituted by the actual type arguments in the inlined function body at the call sites and so can be used for type checks and casts, with the same restrictions for instances of generic types as described above.Generics: in, out, where﻿

[Edit page](https://github.com/JetBrains/kotlin-web-site/edit/master/docs/topics/generics.md)

Last modified: 08 September 2021

Classes in Kotlin can have type parameters, just like in Java:

```kotlin
class Box<T>(t: T) {
    var value = t
}
```

Copied!

To create an instance of such a class, simply provide the type arguments:

```kotlin
val box: Box<Int> = Box<Int>(1)
```

Copied!

But if the parameters can be inferred, for example, from the constructor arguments, you can omit the type arguments:

```kotlin
val box = Box(1) // 1 has type Int, so the compiler figures out that it is Box<Int>
```

Copied!

## Variance﻿

One of the trickiest aspects of Java's type system is the wildcard types (see [Java Generics FAQ](http://www.angelikalanger.com/GenericsFAQ/JavaGenericsFAQ.html)). Kotlin doesn't have these. Instead, Kotlin has declaration-site variance and type projections.

Let's think about why Java needs these mysterious wildcards. The problem is explained well in [Effective Java, 3rd Edition](http://www.oracle.com/technetwork/java/effectivejava-136174.html), Item 31: *Use bounded wildcards to increase API flexibility*. First, generic types in Java are *invariant*, meaning that `List<String>` is *not* a subtype of `List<Object>`. If `List` were not *invariant*, it would have been no better than Java's arrays, as the following code would have compiled but caused an exception at runtime:

```java
// Java
List<String> strs = new ArrayList<String>();
List<Object> objs = strs; // !!! A compile-time error here saves us from a runtime exception later.
objs.add(1); // Put an Integer into a list of Strings
String s = strs.get(0); // !!! ClassCastException: Cannot cast Integer to String
```

Copied!

Java prohibits such things in order to guarantee run-time safety. But this has implications. For example, consider the `addAll()` method from the `Collection` interface. What's the signature of this method? Intuitively, you'd write it this way:

```java
// Java
interface Collection<E> ... {
    void addAll(Collection<E> items);
}
```

Copied!

But then, you would not be able to do the following (which is perfectly safe):

```java
// Java
void copyAll(Collection<Object> to, Collection<String> from) {
    to.addAll(from);
    // !!! Would not compile with the naive declaration of addAll:
    // Collection<String> is not a subtype of Collection<Object>
}
```

Copied!

(In Java, you probably learned this the hard way, see [Effective Java, 3rd Edition](http://www.oracle.com/technetwork/java/effectivejava-136174.html), Item 28: *Prefer lists to arrays*)

That's why the actual signature of `addAll()` is the following:

```java
// Java
interface Collection<E> ... {
    void addAll(Collection<? extends E> items);
}
```

Copied!

The *wildcard type argument* `? extends E` indicates that this method accepts a collection of objects of `E` *or a subtype of* `E`, not just `E` itself. This means that you can safely *read* `E`'s from items (elements of this collection are instances of a subclass of E), but *cannot write* to it as you don't know what objects comply with that unknown subtype of `E`. In return for this limitation, you get the desired behavior: `Collection<String>` *is* a subtype of `Collection<? extends Object>`. In other words, the wildcard with an *extends*-bound (*upper* bound) makes the type *covariant*.

The key to understanding why this works is rather simple: if you can only *take* items from a collection, then using a collection of `String`s and reading `Object`s from it is fine. Conversely, if you can only *put* items into the collection, it's okay to take a collection of `Object`s and put `String`s into it: in Java there is `List<? super String>`, a *supertype* of `List<Object>`.

The latter is called *contravariance*, and you can only call methods that take `String` as an argument on `List<? super String>` (for example, you can call `add(String)` or `set(int, String)`). If you call something that returns `T` in `List<T>`, you don't get a `String`, but rather an `Object`.

Joshua Bloch gives the name *Producers* to objects you only *read from* and *Consumers* to those you only *write to*. He recommends:

> ### 
>
> 
>
> "For maximum flexibility, use wildcard types on input parameters that represent producers or consumers", and proposes the following mnemonic:
>
> *PECS stands for Producer-Extends, Consumer-Super.*

> ### 
>
> 
>
> If you use a producer-object, say, `List<? extends Foo>`, you are not allowed to call `add()` or `set()` on this object, but this does not mean that it is *immutable*: for example, nothing prevents you from calling `clear()` to remove all the items from the list, since `clear()` does not take any parameters at all.
>
> The only thing guaranteed by wildcards (or other types of variance) is *type safety*. Immutability is a completely different story.

### Declaration-site variance﻿

Let's suppose that there is a generic interface `Source<T>` that does not have any methods that take `T` as a parameter, only methods that return `T`:

```java
// Java
interface Source<T> {
    T nextT();
}
```

Copied!

Then, it would be perfectly safe to store a reference to an instance of `Source<String>` in a variable of type `Source<Object>` - there are no consumer-methods to call. But Java does not know this, and still prohibits it:

```java
// Java
void demo(Source<String> strs) {
    Source<Object> objects = strs; // !!! Not allowed in Java
    // ...
}
```

Copied!

To fix this, you should declare objects of type `Source<? extends Object>`. Doing so is meaningless, because you can call all the same methods on such a variable as before, so there's no value added by the more complex type. But the compiler does not know that.

In Kotlin, there is a way to explain this sort of thing to the compiler. This is called *declaration-site variance*: you can annotate the *type parameter* `T` of `Source` to make sure that it is only *returned* (produced) from members of `Source<T>`, and never consumed. To do this, use the `out` modifier:

```kotlin
interface Source<out T> {
    fun nextT(): T
}

fun demo(strs: Source<String>) {
    val objects: Source<Any> = strs // This is OK, since T is an out-parameter
    // ...
}
```

Copied!

The general rule is this: when a type parameter `T` of a class `C` is declared `out`, it may occur only in the *out*-position in the members of `C`, but in return `C<Base>` can safely be a supertype of `C<Derived>`.

In other words, you can say that the class `C` is *covariant* in the parameter `T`, or that `T` is a *covariant* type parameter. You can think of `C` as being a *producer* of `T`'s, and NOT a *consumer* of `T`'s.

The `out` modifier is called a *variance annotation*, and since it is provided at the type parameter declaration site, it provides *declaration-site variance*. This is in contrast with Java's *use-site variance* where wildcards in the type usages make the types covariant.

In addition to `out`, Kotlin provides a complementary variance annotation: `in`. It makes a type parameter *contravariant*, meaning it can only be consumed and never produced. A good example of a contravariant type is `Comparable`:

```kotlin
interface Comparable<in T> {
    operator fun compareTo(other: T): Int
}

fun demo(x: Comparable<Number>) {
    x.compareTo(1.0) // 1.0 has type Double, which is a subtype of Number
    // Thus, you can assign x to a variable of type Comparable<Double>
    val y: Comparable<Double> = x // OK!
}
```

Copied!

The words *in* and *out* seem to be self-explanatory (as they’ve already been used successfully in C# for quite some time), and so the mnemonic mentioned above is not really needed. It can in fact be rephrased at a higher level of abstraction:

**[The Existential](https://en.wikipedia.org/wiki/Existentialism) Transformation: Consumer in, Producer out!**:-)

## Type projections﻿

### Use-site variance: type projections﻿

It is very easy to declare a type parameter `T` as `out` and avoid trouble with subtyping on the use site, but some classes *can't* actually be restricted to only return `T`'s! A good example of this is `Array`:

```kotlin
class Array<T>(val size: Int) {
    operator fun get(index: Int): T { ... }
    operator fun set(index: Int, value: T) { ... }
}
```

Copied!

This class can be neither co- nor contravariant in `T`. And this imposes certain inflexibilities. Consider the following function:

```kotlin
fun copy(from: Array<Any>, to: Array<Any>) {
    assert(from.size == to.size)
    for (i in from.indices)
        to[i] = from[i]
}
```

Copied!

This function is supposed to copy items from one array to another. Let's try to apply it in practice:

```kotlin
val ints: Array<Int> = arrayOf(1, 2, 3)
val any = Array<Any>(3) { "" }
copy(ints, any)
//   ^ type is Array<Int> but Array<Any> was expected
```

Copied!

Here you run into the same familiar problem: `Array<T>` is *invariant* in `T`, and so neither `Array<Int>` nor `Array<Any>` is a subtype of the other. Why not? Again, this is because `copy` could have an unexpected behavior, for example, it may attempt to write a `String` to `from`, and if you actually pass an array of `Int` there, a `ClassCastException` will be thrown later.

To prohibit the `copy` function from *writing* to `from`, you can do the following:

```kotlin
fun copy(from: Array<out Any>, to: Array<Any>) { ... }
```

Copied!

This is *type projection*, which means that `from` is not a simple array, but is rather a restricted (*projected*) one. You can only call methods that return the type parameter `T`, which in this case means that you can only call `get()`. This is our approach to *use-site variance*, and it corresponds to Java's `Array<? extends Object>` while being slightly simpler.

You can project a type with `in` as well:

```kotlin
fun fill(dest: Array<in String>, value: String) { ... }
```

Copied!

`Array<in String>` corresponds to Java's `Array<? super String>`. This means that you can pass an array of `CharSequence` or an array of `Object` to the `fill()` function.

### Star-projections﻿

Sometimes you want to say that you know nothing about the type argument, but you still want to use it in a safe way. The safe way here is to define such a projection of the generic type, that every concrete instantiation of that generic type will be a subtype of that projection.

Kotlin provides so-called *star-projection* syntax for this:

- For `Foo<out T : TUpper>`, where `T` is a covariant type parameter with the upper bound `TUpper`, `Foo<*>` is equivalent to `Foo<out TUpper>`. This means that when the `T` is unknown you can safely *read* values of `TUpper` from `Foo<*>`.
- For `Foo<in T>`, where `T` is a contravariant type parameter, `Foo<*>` is equivalent to `Foo<in Nothing>`. This means there is nothing you can *write* to `Foo<*>` in a safe way when `T` is unknown.
- For `Foo<T : TUpper>`, where `T` is an invariant type parameter with the upper bound `TUpper`, `Foo<*>` is equivalent to `Foo<out TUpper>` for reading values and to `Foo<in Nothing>` for writing values.

If a generic type has several type parameters, each of them can be projected independently. For example, if the type is declared as `interface Function<in T, out U>` you could use the following star-projections:

- `Function<*, String>` means `Function<in Nothing, String>`.
- `Function<Int, *>` means `Function<Int, out Any?>`.
- `Function<*, *>` means `Function<in Nothing, out Any?>`.

> ### 
>
> 
>
> Star-projections are very much like Java's raw types, but safe.

## Generic functions﻿

Classes aren’t the only declarations that can have type parameters. Functions can, too. Type parameters are placed *before* the name of the function:

```kotlin
fun <T> singletonList(item: T): List<T> {
    // ...
}

fun <T> T.basicToString(): String { // extension function
    // ...
}
```

Copied!

To call a generic function, specify the type arguments at the call site *after* the name of the function:

```kotlin
val l = singletonList<Int>(1)
```

Copied!

Type arguments can be omitted if they can be inferred from the context, so the following example works as well:

```kotlin
val l = singletonList(1)
```

Copied!

## Generic constraints﻿

The set of all possible types that can be substituted for a given type parameter may be restricted by *generic constraints*.

### Upper bounds﻿

The most common type of constraint is an *upper bound*, which corresponds to Java's `extends` keyword:

```kotlin
fun <T : Comparable<T>> sort(list: List<T>) {  ... }
```

Copied!

The type specified after a colon is the *upper bound*, indicating that only a subtype of `Comparable<T>` can be substituted for `T`. For example:

```kotlin
sort(listOf(1, 2, 3)) // OK. Int is a subtype of Comparable<Int>
sort(listOf(HashMap<Int, String>())) // Error: HashMap<Int, String> is not a subtype of Comparable<HashMap<Int, String>>
```

Copied!

The default upper bound (if there was none specified) is `Any?`. Only one upper bound can be specified inside the angle brackets. If the same type parameter needs more than one upper bound, you need a separate *where*-clause:

```kotlin
fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
    where T : CharSequence,
          T : Comparable<T> {
    return list.filter { it > threshold }.map { it.toString() }
}
```

Copied!

The passed type must satisfy all conditions of the `where` clause simultaneously. In the above example, the `T` type must implement *both* `CharSequence` and `Comparable`.

## Type erasure﻿

The type safety checks that Kotlin performs for generic declaration usages are done at compile time. At runtime, the instances of generic types do not hold any information about their actual type arguments. The type information is said to be *erased*. For example, the instances of `Foo<Bar>` and `Foo<Baz?>` are erased to just `Foo<*>`.

Therefore, there is no general way to check whether an instance of a generic type was created with certain type arguments at runtime, and the compiler [prohibits such `is`-checks](https://kotlinlang.org/docs/typecasts.html#type-erasure-and-generic-type-checks).

Type casts to generic types with concrete type arguments, for example, `foo as List<String>`, cannot be checked at runtime. These [unchecked casts](https://kotlinlang.org/docs/typecasts.html#unchecked-casts) can be used when type safety is implied by high-level program logic but cannot be inferred directly by the compiler. The compiler issues a warning on unchecked casts, and at runtime, only the non-generic part is checked (equivalent to `foo as List<*>`).

The type arguments of generic function calls are also only checked at compile time. Inside the function bodies, the type parameters cannot be used for type checks, and type casts to type parameters (`foo as T`) are unchecked. However, [reified type parameters](https://kotlinlang.org/docs/inline-functions.html#reified-type-parameters) of inline functions are substituted by the actual type arguments in the inlined function body at the call sites and so can be used for type checks and casts, with the same restrictions for instances of generic types as described above.