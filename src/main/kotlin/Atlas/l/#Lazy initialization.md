## Theory: Lazy initialization

You can declare a variable by putting an equal sign followed by a value after its name:

```kotlin
var a = "I love Hyperskill!"
```

Creating a `String` object like this isn't a very resource- or time-consuming operation for the compiler. But creating instances of more complex classes can be much more expensive. In this topic, you will learn how to tackle this issue by delaying the creation of objects until they are needed using **lazy initialization**.

##### How it works

Lazy initialization lets us create an object at the exact point when the first reference to it occurs.

This means that we don't need to waste time by immediately using our program's resources to allocate memory to objects that aren't required from the outset.

If we allow objects to be initialized when they're needed, we can save a lot of time by redistributing the allocation of resources over the life cycle of our program.

##### Implementation

This goal can be achieved in different ways in different languages and frameworks.

For example, the [Spring backend framework](https://spring.io/projects/spring-framework) has the special property `spring.main.lazy-initialization=true`. This can reduce an application's startup time by creating fewer components at the beginning. However, in the context of Spring, this feature has [some disadvantages](https://www.baeldung.com/spring-boot-lazy-initialization#effects) that are beyond the scope of our topic.

Let's see how to use lazy initialization without frameworks.

Kotlin has a special `lazy()` function that accepts a [lambda](https://hyperskill.org/learn/step/6154). The first call executes this lambda and remembers the result. Subsequent calls simply return this value.

You can see a simple example below:

```kotlin
fun main() {
    val a: String by lazy {
        print("Variable a is initialized. ")
        "I love Hyperskill!"
    }

    println("Initializing a! ") // Initializing a!
    println(a) // Variable a is initialized. I love Hyperskill!
    println(a) // I love Hyperskill!
}
```

So, why did we get this output? Before the value of `a` became `"I love Hyperskill!"`, the lambda body was called with `print()`. This all happened the first time we called `println(a)` — no sooner, no later. When we printed `a` again, no calculation occurred, so only `a`'s value was displayed.



When using lazy initialization in Kotlin, you must declare variables with the `val` keyword because the value can only be initialized once.



##### Synchronization problems

If your programs take advantage of multithreading, you need to know about the `lazy()` function's mode parameter.

- ```kotlin
  LazyThreadSafetyMode.SYNCHRONIZED
  ```

   

  means that the value is only computed in one thread, and all threads will get the same value. It's the default option, so you can omit it if you wish:

  ```kotlin
  val a: String by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
  ```

- ```kotlin
  LazyThreadSafetyMode.PUBLICATION
  ```

   

  specifies that the lambda can be called several times with an uninitialized lazy object value, but the value that is returned first will be used:

  ```kotlin
  val a: String by lazy(LazyThreadSafetyMode.PUBLICATION) {
  ```

- ```kotlin
  LazyThreadSafetyMode.NONE
  ```

   

  means there's no synchronization at all, so if we call the variable from different threads, its value can't be uniquely defined. Using this option isn't recommended if your program allows a lazy object to be called for the first time from more than one thread:

  ```kotlin
  val a: String by lazy(LazyThreadSafetyMode.None) {
  ```

You may be wondering why the keyword `by` is used before `lazy`? Well, it's to do with **delegates**. This is how we delegate the implementation of the corresponding property to a lambda. A detailed explanation about this is provided in the delegates topic.

##### lateinit

Another delayed initialization feature in Kotlin that's worth mentioning is `lateinit`. This is a keyword rather than a function like `lazy()`.

Normally, if a class field's value isn't null, we should either initialize the property immediately or do it in the constructor. But there are often situations where, at the time we create an instance of a class, we can't initialize the property, and we don't want to make it nullable.

We can't use the following approach because the default value of the field must be initialized:

```kotlin
class A {
    var a: String
```

We could make `a` nullable, but we don't want it to be!

```kotlin
class A {
    var a: String? = null
```

Fortunately, `lateinit` can be used to solve this problem:

```kotlin
class A {
    lateinit var a: String

    fun initA(a: String) {
        this.a = a
    }
}
```

This method allows us to set the value of `a` in the `initA()` function at a convenient point after the object has been created.

Note that `lateinit` variables must be declared with `var`, unlike those declared with `lazy()`.

We can also check whether the property has already been initialized. (Under the hood, this is achieved through [reflection](https://hyperskill.org/learn/step/3609), but that's not something you need to understand right now.) To see how this works, let's add a function to our class. As shown below, `isInitialized` can be called on the current object's field via `this::a`, returning either true or false:

```kotlin
class A {
    lateinit var a: String

    fun initA(a: String) {
        this.a = a
    }

    fun doSmth() {
        if (::a.isInitialized)
            println("a is Initialized")
        else
            println("a isn't Initialized")
    }
}
```



If you try to access a property before the binding has been initialized, an error will be thrown:



Caused by: kotlin.UninitializedPropertyAccessException: lateinit property binding has not been initialized



##### Conclusion

You're now familiar with lazy initialization. You understand why it's needed and how it can be applied. Although their usage is quite similar, you're aware of the differences between `lazy()` and `lateinit`. You also know about the `lazy()` thread safety modes.