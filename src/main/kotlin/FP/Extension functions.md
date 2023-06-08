## Theory: Extension functions

Often a developer doesn't write everything from scratch but uses an already written code. It saves them some time. However, there are some downsides such as the inability to edit the used code, especially when the code comes from another developer. Let's look how to optimize your work!

##### Problem with existing classes

To work with classes that you can't modify, you can write a function that takes an object of that class as an argument:

```kotlin
fun repeated(string: String): String = string + string

repeated("ha")  // returns "haha"
```

Do you see an issue here? In Kotlin, standard operations are available as member functions. For example, to get the first symbol of the `"ha"` string, you can write `"ha".first()`. It's more convenient to use a single syntax for all operations. So how can we add a member function to the uneditable `String` class?

##### Defining and calling extension functions

Kotlin has just the right **syntactic sugar** for it: extension functions. Let's rewrite the `repeated` function as an extension one:

```kotlin
fun String.repeated(): String = this + this
```

Now to get the `"haha"` we can write

```kotlin
"ha".repeated()
```

As you can see, the syntax to define an extension function is like defining a simple function. Just write the **name of** a **class** that you would like to extend **and** add a **dot** before the function name. The class to be extended is called a **receiver type**.

You can get access to the field of an object in the extension function almost as easily, as in the member function. That object is called a **receiver object**.

```kotlin
class Client(val name: String, val age: Int)

fun Client.getInfo() = "$name $age" // Client is the receiver type


val client = Client("John", 32)
print(client.getInfo()) // client is the receiver object
```

Note, if the developer hides some information (you will know how to do it later) and your code cannot get it, the extension function can't have access too. So, it works more like a simple function, not a member function



**Note**, that just like other functions, extension functions **can take arguments and return a value** of any type including the same type as the receiver type.



So the issue is solved: Kotlin developers can add any functions to any classes they want.

We also need to mention that extension functions are used even in Kotlin standard library. For example, if you look at the `String` class definition, you will see only necessary member functions. Other functions such as `.first()` and `.toUpperCase()` are actually extension functions needed just to simplify the code of the class.

##### Extension function vs member function

Let's assume we have the following class:

```kotlin
class A {
    fun member() = println("hi from member")
}

fun A.extension() = println("hi from extension")
```

Both functions can be called in the same way: `A().member()` and `A().extension()`. So we actually can't be sure if it's a member or an extension function just by looking at its calling line. We need to check the implementation.

And what if a developer tries to add an already existing function to a class? The answer is a bit complex here because there can be several different cases.

If we try to define another `fun A.extension()`, the code won't compile. You cannot define two functions with the same signature, as always.

If we add `fun A.member() = println("bye from not member")`, the code will compile but calling `A().member()` will give us `"hi from member"`.

So **member functions always win**. It helps when somebody wants to change the object behavior intentionally or unintentionally.

If you want to give an extension function the name that already exists, you must change the signature of the function, for example, change its arguments. It won't break the already existing code.

##### Idiom

The extension function is an idiomatic way to add some functionality to an existing class. It is simple to use and shows that your new function is closely related to the class. So use it, with community approval! This idiom is documented on [kotlinlang.org](https://kotlinlang.org/docs/idioms.html#extension-functions).

```kotlin
fun String.spaceToCamelCase() { ... }

"Convert this to camelcase".spaceToCamelCase()
```

##### Conclusion

The extension function is a useful instrument, that can help you work with the existing class. Sometimes you can't change this class. Other times you need to add some functionality and don't want to store it in your class, because it takes up a lot of space. The extension function is a good solution for all of this. Good luck with tasks!