# `.let`'s talk about scope functions

Kotlin “scope functions” are functions that allow for changing the *scope*, or the range, of a variable. There are five such functions included in the Kotlin standard library: `apply`, `run`, `with`, `let`, and `also`.

Here’s a really contrived example:

```kotlin
fun myFun() {
    val outside = 6.2831853071
    run {
        val inside = 1.61803398875
        // Both outside and inside are usable and in scope
    }
    // inside is out of scope, and only outside is available
}
```

In that example, we used `run` to create a smaller *scope* for `inside`.

# `'this’` is the receiver

For the scope functions `apply`, `run`, and `with`, one of the most useful features is that the object referred to by `this` inside the block is the variable that's used in the call.

```kotlin
class Foo {
    //...
    myView.run {
        // this refers to myView rather than Foo inside the block.
        alpha = 0.5f
        background = ContextCompat.getDrawable(context, R.drawable.my_drawable)
    }
}
```

This works because the *scope* of `this` has changed to `myView`inside the `run` block. Aside from that, if we wanted to get access to the `this` object from before, we can do it just like we could from an inner class or anonymous object by using `this@Foo`.

# Three, er… Two values

Since scope functions are functions, they should return a value, and after thinking about it, one might consider three such candidates for those values:

- The object itself
- The last value of the block
- N̶o̶t̶h̶i̶n̶g̶

Actually, there’s no reason for it to ever have *no* value (AKA ‘Unit’) because we can always just ignore it, which leaves us with two possibilities for the value of the block.

The first option is for the value to be the object, AKA the receiver, itself. Kind of like a builder. This is how `apply` works.

```kotlin
val paint = Paint().apply {
    color = Color.MAGENTA
    style = Paint.Style.STROKE
    textSize = textHeadlinePx
}
```

Rather convenient! We can create *and* configure our `Paint` in one statement.

The second option is the function type, where the value of the block is the value of the last statement in the block. This is actually what *both*`run` and `with` accomplish.

```kotlin
val line = PoetryGenerator.obtain().run {
    style = "Emily Dickinson"
    style += "Lucille Clifton"
    lines = 1
    generate()
}
```

Here we get a reference to a `PoetryGenerator` instance and perform our configuration on it. But we're not interested in the `PoetryGenerator` itself, we're interested in the line of poetry it creates. Since `run` will set the value of `line` to the value of the last statement, all we have to do is call `generate()` at the end. `line` is then set to the return value of `generate()`.

`with` works exactly the same way, but while it's possible to write `nullableVar?.run {...}`, it would be a bit different with `with`:

```kotlin
val hash = with(nullableGenerator) {
    this?.configuration = config
    this?.generate()
}
```

Even though `with` returns a value, it reminds me of the `with`keyword in [Pascal](https://www.pascal-programming.info/lesson11.php#with) and [VB](https://docs.microsoft.com/en-us/dotnet/visual-basic/language-reference/statements/with-end-with-statement#example), which means I'll usually just end up using it like this:

```kotlin
with (myConfig) {
    data = value
    autoRefresh = false
    // ...etc...
}
```

# I’d rather be ‘it’

There are times when shifting the scope of `this` to another object temporarily makes things easier, but there are other times where that's not the case:

```kotlin
myIntent?.run { 
    data = this@MainActivity.data
    startActivity(this)
}
```

Yuck! Not only do we have to use a [qualified this](https://kotlinlang.org/docs/reference/this-expressions.html#qualified), just to reference a class property, but since `myIntent` is referenced by `this`, the call to `startActivity` looks a bit odd.

Fortunately this is where `also` and `let` come in. In this case, we essentially want to check if `myIntent` is null and proceed only when it's not. The idiomatic way to do this in Kotlin is with the `let`scope function:

```kotlin
myIntent?.let {
    it.data = data
    startActivity(it)
}
```

`let` works exactly like `run` except that instead of the object being referenced by `this`, it's referenced with `it`.

At least if you’d like it to be. This also works as expected:

```kotlin
myIntent?.let { intent ->
    intent.data = data
    startActivity(intent)
}
```

Now `myIntent` is referenced by `intent` inside the block, which can be helpful when you'd want to provide more context than `it`can provide.

The last scope function, `also`, works like `apply`, but, again, the object is referenced with `it` instead of `this`.

This is useful for two main reasons. First, it can be thought of as its name: create an object and also do this with it:

```kotlin
val myListener = Listener().also {
    addListener(it)
}
```

But it’s also tremendously helpful when doing something along with an unrelated object or statement. A great example of this is logging:

```kotlin
val key: String get() = keystore.getKey(KEY_ID).also {
    Log.v(TAG, "Read key at ${System.currentTimeMillis()}")
}
```

The log doesn’t even use the object. Using `also` allows us to add log a message without having to change the rest of the code, and then, when the log is no longer needed, it's simple to pull out again.

# What’s so special then?

“But wait!”, you might be saying, “All functions and lambdas create new scopes. What’s special about these?” And yes, actually we create new scopes all the time when we’re writing Kotlin. For example:

```kotlin
parentViewGroup.forEach { favoriteChild ->
    // Do something with favoriteChild…
}
```

Here, the scope of `favoriteChild` is limited to the inside of the `forEach` lambda, but `forEach` isn't a scope function. What makes them different?

In truth, it’s actually how ordinary they are that makes them special. `forEach`, `map`, `filter`, and many others create new scopes, but they also iterate over an `Iterable`, or perform a mapping, filter out values, etc…

Scope functions, in contrast, don’t do anything other than create a new scope. Kotlin makes the implementation of these functions [extremely simple](https://github.com/JetBrains/kotlin/blob/1.1.0/libraries/stdlib/src/kotlin/util/Standard.kt).

# How do I choose?

There’s been [a lot](https://medium.com/@elye.project/mastering-kotlin-standard-functions-run-with-let-also-and-apply-9cd334b0ef84) of [discussion](https://proandroiddev.com/the-difference-between-kotlins-functions-let-apply-with-run-and-else-ca51a4c696b8) about the topic of scope functions, including [a flowchart](https://cdn-images-1.medium.com/max/1600/1*pLNnrvgvmG6Mdi0Yw3mdPQ.png) to help select what function to use.

The choice comes down to this: if you want to return the object you’re starting with, then your choice is between `apply` and `also`. If you want to return the result of a method, then you'll want to look at `let`, `run`, and `with`.

![img](/home/nes/Pictures/1*UW1WkqmUnbJc1QEGzm59jg.png)

Then it’s just about which method of referencing the object is easier to read and maintain.

# Wrapping `it` up

We talked about how Kotlin includes five scope functions in the standard library. Three of them, `apply`, `run`, and `with`, use a receiver to change the scope of `this` to the object so its public properties and methods can be accessed without being qualified by the variable name.

We talked about how the remaining two, `let` and `also`, take the object and use it like a parameter, allowing it to be referenced with `it` or another name.

Finally, we talked a bit about how to choose which scope function to use, based on how to reference the object and what the statement should return.

Check out the [Google Developers](https://medium.com/google-developers) blog for more great content, and stay tuned for more articles on Kotlin!
