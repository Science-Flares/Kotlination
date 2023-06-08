##Expressive Functions and Adjustable Interfaces

###In this chapter, we will cover the following recipes:

• Declaring adjustable functions with default parameters

• Declaring interfaces containing default implementations

• Extending functionalities of classes

• Destructuring types

• Returning multiple data

• Inlining parameters of closure type

• Infix notations for functions

• Smart types checking with generic reified parameters

• Overloading operators


##Introduction

This chapter will focus on exploring a number of Kotlin features that can help to write functions and interfaces that are robust, flexible, and clean. 

After reading the following recipes, you will understand the language-specific support and approach for boilerplate code reduction and runtime performance improvements.

You will also understand the way functions of the standard library are implemented under the hood and how to work with them effectively.



###Declaring adjustable functions with default parameters

When creating new functions, we often need to allow some of their parameters to be optional. This forces us to use method overloading to create multiple function declarations with the same name but different sets of arguments related to different use cases and scenarios. Usually, under the hood, each variant of the function is calling the base function with the default implementation. Let's consider a simple example of a function that calculates a displacement of an object moving with a constant acceleration rate:
```
fun calculateDisplacement(initialSpeed: Float, 
                              acceleration: Float, 
                                  duration: Long): Double =
initialSpeed * duration + 0.5 * acceleration * duration * duration
```
We might also need to provide a displacement calculation for the scenario where the initial speed of the object is always equal to zero. 
In such a case, we would end up with overloading the basic function in the following manner:

`fun calculateDisplacement(acceleration: Float, duration: Long): Double = calculateDisplacement(0f, acceleration,duration)`

However, Kotlin allows you to reduce multiple declarations and to handle a number of different use cases with a single function having optional parameters. 
In this recipe, we are going to design an adjustable version of the `calculateDisplacement()` function with an optional `initialSpeed: Float` parameter.

_How to do it..._

• Let's declare the basic implementation for the function:
```
fun calculateDisplacement(initialSpeed: Float, 
                             acceleration: Float, 
                                 duration: Long): Double =
initialSpeed * duration + 0.5 * acceleration * duration * duration
```

• Let's declare a default value for the initialSpeed parameter:
```
fun calculateDisplacement(initialSpeed: Float = 0f, 
acceleration: Float, 
duration: Long): Double =
initialSpeed * duration + 0.5 * acceleration * duration * duration
```

_How it works..._

We've declared a default value for the initialSpeed parameter, equal to `0`. 

Once you have a default value assigned, the initialSpeed parameter becomes an optional one.

We can now omit it while invoking the function, as shown in the following example:

`val displacement= calculateDisplacement(acceleration= 9.81f, duration= 1000)`

Note that, if we are omitting some of the parameters and using their default values, we have to specify the values of the other parameters together with their names explicitly. 
This allows the compiler to map the values to the specific parameters.

Of course, we are able to override the default value using the standard way:

`val displacement= calculateDisplacement(10f, 9.81f, 1000)`

**See also**

• Kotlin makes it possible to declare interfaces containing default function implementations. 
You can learn more about this feature in the Declaring interfaces containing default implementations recipe.

###Declaring interfaces containing default implementations
Kotlin makes the interface a powerful language element by offering the possibility to declare default implementations for its functions and to define default values of its properties. 
Those features bring the interface to a whole new level, allowing you to use it for more advanced applications than simple contract declarations.
In this recipe, we are going to define a reusable interface responsible for validating email address values entered by a user into the input field in an abstract registration form. 
The interface will provide two functions. 
The first one is responsible for parsing the email address and deciding if the given value is a valid email address, and the second one responsible for extracting a user's login from the email text entered into the form.



**Getting ready**

The declaration of an interface with a default function implementation is easy. Instead of declaring the function header, we need to include its body too:
```
interface MyInterface {
                fun foo() {
// default function body
     }
}
```

_How to do it..._

• Declare a new interface called EmailValidator:

`interface EmailValidator {}`

• Add a string property responsible for holding the current text input:
```
interface EmailValidator {
              var input: String
 }
```
• Add the isEmailValid() function to the interface:
```
interface EmailValidator {
var input: String
fun isEmailValid(): Boolean = input.contains("@")
}
```
• Add the getUserLogin() function:
```
interface EmailValidator {
var input: String
 
fun isEmailValid(): Boolean = input.contains("@")
 
fun getUserLogin(): String =
if (isEmailValid()) {
input.substringBefore("@")
  } else {
    ""
     }
}
```

_How it works..._

Now, let's give it a try and take a look at how we can use the EmailValidator interface in action. Let's assume we have a RegistrationForm class containing a hook method that is invoked every time the input text is modified:
```
class RegistrationForm() {
fun onInputTextUpdated(text: String) {
// do some actions on text changed
   }
}
```
To make use of our EmailValidator interface, we need to declare a class that is implementing it. We can modify the RegistrationForm class to implement the EmailValidator interface:
```
class RegistrationForm(override var input: String = ""): EmailValidator{
fun onInputTextUpdated(newText: String) {
this.input = newText
 
if (!isEmailValid()) {
print("Wait! You've entered wrong email...")
} else {
print("Email is correct, thanks: ${getUserLogin()}!")
        }
    }
}
```
Every time the `onInputUpdated()` function is invoked, we are updating the input:
String property declared in the EmailValidator interface. 
Once it is up to date, we are using the `EmailValidator` interface functions `isEmailValid()` and `getUserLogin()` values. 
Extracting the function implementations to the interface makes it possible to reuse them and integrate them easily in a number of classes.
The only part that needs an actual implementation is the input property of the `EmailValidator` interface, which holds the current state of the text inserted by the user.
The smooth way of integrating the `EmailValidator` interface makes it great when it comes to reusability and versatility of the application in different scenarios.

**There's more...**

It's important to keep in mind that, although we can define a default function implementation in the interface, we are not able to instantiate default values for interface properties.
Unlike the class properties, properties of an interface are abstract.
They don't have backing fields that could hold a current value (state).
If we declare a property inside an interface, we need to implement it in the class or object that implements this interface.
This is the main difference between interfaces and abstract classes.
`Abstract` classes can have constructors and can store properties along with their implementations.
As with Java, we can't extend multiple classes; however, we can implement multiple interfaces. 
When we have a class implementing multiple interfaces containing default implementations, we are at risk of dealing with conflicts caused by functions having the same signatures:
```
interface A {
fun foo() {
// some operations 
   }
}
 
interface B {
fun foo() {
// other operations
    }
}
```
In this case, we need to override the `foo()` function explicitly to resolve the conflict:
```
class MyClass: A, B {
override fun foo() {
print("I'm the first one here!")
    }
}
```
Otherwise, we would get the following error:

Class `MyClass` must override public open fun `foo(): Unit` because it inherits multiple interface methods of it.



**See also**

• A similar feature of Kotlin is the ability to declare default values of functions' parameters.
You can learn more about it in the Declaring adjustable functions with default parameters recipe.

###Extending functionalities of classes

While working on implementing new features or refactoring of existing code, we often end up extracting some part of the code to functions in order to reuse them in different places. 
If the extracted function is atomic enough, we often end up exporting it to external utility classes whose primary purpose is to extend functionalities of existing classes. 
Kotlin provides an interesting alternative to the utility classes.
It offers a built-in feature allowing us to extend functionalities of other classes with extension functions and extension properties.
In this recipe, we are going to extend the functionality of the `Array<T>` class and add a `swap(a:T, b: T)` extension function to it, which is responsible for changing places of a two given elements of the array.

**Getting ready**

We can declare extension functions and extension properties inside any file in the project.
However, to keep them well organized, it's better to put them in dedicated files.
The syntax for extension functions is very similar to the one of the standard function.
We just need to add information about the type that is being extended with the new function, as follows:
```
fun SomeClass.newFunctionName(args): ReturnType {
// body
}
```

_How to do it..._

• Create a new file, `Extensions.kt`, to store the extension function definition. 

• Implement the `swap()` function inside:
```
fun <T>Array<T>.swap(a: T, b: T) {
val positionA = indexOf(a)
val positionB = indexOf(b)
 
if (positionA <0 || positionB <0) {
throw IllegalArgumentException("Given param doesn't belong
to the array")
}
 
val tmp = this[positionA]
this[positionA] = this[positionB]
this[positionB] = tmp
}
```

_How it works..._

As a result, we are able to call the `swap` function on any instance of the `Array` class. 
Let's consider the following example:
```
val array: Array<String>= arrayOf("a", "b", "c", "d")
array.swap("c", "b")
print(array.joinToString())
```
This results in printing the following output to the console:

`a, c, b, d`

As you can see, we can access the current instance of the class inside the extension function using the `this` keyword.

**There's more...**

Apart from extension functions, Kotlin also offers an extension properties feature.
For example, we can declare a property for the `List<T>` class that will hold information about the last element index value:

`val<T>List<T>.lastIndex:Intget()=size-1`

Extensions are a widely used pattern across Kotlin standard library classes. 
They work seamlessly with **Java**, **Kotlin**, **JavaScript**, and native classes defined within the project and in external dependencies as well. 


###Destructuring types

It is often practical to convert a single object of a complex type into a number of variables. 
This allows you to provide proper naming for the variables and simplifies the code.
Kotlin provides an easy, built-in way to achieve this with a feature called destructuring:
```
data class User(val login: String, val email: String, val birthday: LocalDate)
 
fun getUser() = User("Agata", "ag@t.pl", LocalDate.of(1990, 1, 18))
 
val (name, mail, birthday) = getUser()
 
print("$namewas born on $birthday")
```
As a result, this piece of code would print the following message to the console:

`Agata was born on 1990-01-18`

Pretty awesome! Destructuring is available for data classes out of the box.
The Kotlin standard library provides this feature for many common types as well.
However, destructuring is not available explicitly whenever we are dealing with custom, non-data classes. 
Especially, while working with classes from external libraries written in other languages such as Java, we need to define the destructuring mechanism manually. 
In this recipe, we are going to implement destructuring for a Java class defined as follows:
```
// Java code
public class LightBulb {
private final int id;
private boolean turnedOn = false;
 
public LightBulb(int id) {
this.id = id;
}
 
public void setTurnedOn(boolean turnedOn) {
this.turnedOn = turnedOn;
}
 
public boolean getTurnedOn() {
return turnedOn;
}
 
public int getId() {
return id;
}
}
```

**Getting ready**

Destructuring declarations in Kotlin are position-based, opposed to property name-based declarations available in other languages. 
This means the Kotlin compiler decides which class property is linked to which variable based on the order of the properties. 
In order to allow custom class destructuring, we need to add implementations of the functions called `componentN`, where `N` refers to the component number marked with the `operator` keyword to allow using them in a destructuring declaration.

_How to do it..._

• Declare an extension function returning the id property of the `LightBulb` class:

`operator fun LightBulb.component1() = this.id`

• Add another extension `componentN` function responsible for returning the `turnedOn` property:

`operator fun LightBulb.component2() = this.turnedOn`

_How it works..._

Once we declare proper `componentN` functions, we can benefit from destructuring of the `LightBulb` type objects:
```
val (id, turnedOn) = LightBulb(1)
print("Light bulb number $idis turned ${if (turnedOn) "on" else "off"}")
```
This code would print the following output to the console:

`Light bulb number 1 is turned off`

As you can see, the `component1()` function was assigned to the first variable of the destructured declaration—id. 
Similarly, the second `turnedOn` variable was assigned with the result of the `component2()` function.


**There's more...**

Because of the fact that properties in destructured object assignments are position-based, sometimes we are forced to declare more variables than we want to use.
We can use an underscore if we don't need to use a certain value, avoiding the compiler hint indicating an unused variable and simplifying the code a bit:
```
val (_, turnedOn) = LightBulb(1)
print("Light bulb is turned ${if (turnedOn) "on" else "off"}")
```

Destructuring is also available for function return values:
```
val (login, domain) = "agata@magdalena.com".split("@")
print("login: $login, domain: $domain")
```
The preceding code is going to return the following output:

`login: agata, domain: magdalena.com`

We can also use destructured declarations with lambda expressions:
```
listOf(LightBulb(0), LightBulb(1))
.filter { (_, isOn) ->isOn }
.map { (id, _) ->id }
```
Another useful application of destructured declarations is an iteration. For example, we can use this feature to traverse through map entries:
```
val lightBulbsWithNames = 
mapOf(LightBulb(0) to "Bedroom", LightBulb(1) to "Kitchen")
 
for ((lightbulb, name) in lightBulbsWithNames) {
lightbulb.turnedOn = true
}
```

###Returning multiple data

Although Kotlin doesn't provide a multiple return feature, thanks to data classes and destructuring declarations, it is quite convenient to write functions that return a number of values of different types. 
In this recipe, we are going to implement a function returning the result of dividing two numbers. 
The result is going to contain the quotient and remainder values.

_How to do it..._

• Let's start with declaring a data class for the return type:

`data class DivisionResult(val quotient: Int, val remainder: Int)`

• Let's implement the FP.divide() function:
```kotlin
fun FP.divide (dividend: Int, divisor: Int): DivisionResult {
    val quotient = dividend.div(divisor)
    val remainder = dividend.rem(divisor)
    return DivisionResult(quotient, remainder)
}
```

**How it works...**

We can see the `FP.divide()` function in action:
```kotlin
val dividend = 10
val divisor = 3
val (quotient, remainder) = FP.divide(dividend, divisor)
 
print("$dividend/ $divisor= $quotientr $remainder")
```
The preceding code is going to print the following output:

`10 / 3 = 3 r 1`

Thanks to the fact that we are returning a data class instance, the `DivisionResult` class, we can benefit from the destructuring feature and assign the result to a set of separate variables.

**There's more...**

The Kotlin standard library provides ready to use Pair and Triple classes.
We can use them to return two and three values of any type.
This eliminates the need to create a dedicated data classes for the return type.
On the other hand, using data classes gives us the ability to operate on more meaningful names, which adds more clarity to the code.

The following example demonstrates using the Pair class to return two objects at the same time:
```kotlin
fun getBestScore(): Pair<String, Int>= Pair("Max", 1000)
val (name, score) = getBestScore()
print("User $name has the best score of $score points")
```

**See also**

• If you'd like to get more familiar with destructuring declarations, you can take a look at the **Destructuring types** recipe

###Inlining parameters of closure type
Usage of **higher-order** functions can lead to a decrease of runtime performance.
Memory allocations of the functions passed as lambda arguments and their virtual calls in a function body lead to runtime overhead.
However, in many cases, we can eliminate this type of overhead by inlining the lambda expression parameters.
In this recipe, we are going to implement the `lock()` function that will automate work with the Java `java.util.concurrent.locks.Lock `interface. 
The function will take two arguments—an instance of the Lock interface and the function that should be invoked after the lock is acquired.
Finally, our `lock()` function should release the lock. 
We also want to allow making the function parameter inlined.

**Getting ready**

To declare an inline function, we simply need to add the inline modifier in front of the function header.

_How to do it..._

Let's declare a `lock()` function with two arguments—an instance of the Lock interface and the function to be invoked after the lock is acquired:
```kotlin
inline fun performHavingLock(lock: Lock, task: () ->Unit) {
    lock.lock()
    try {
        task()
    } finally {
        lock.unlock()
    }
}
```

_How it works..._

The `performHavingLock()` function allows us to provide synchronization for the function passed to it as the task parameter:
```kotlin
performHavingLock(ReentrantLock()) {
    print("Wait for it!")
}
```
As a result, the `performHavingLock()`function is going to print the following output to the console:
`Wait for it!`
Under the hood, the inline modifier affects both the function itself and the lambda expressions passed to it. 
They are all going to be inlined in the underlying generated bytecode:
```java
Lock lock = (Lock)(new ReentrantLock());
lock.lock();
 
try {
String var2 = "Wait for it!";
System.out.print(var2);
} finally {
    lock.unlock();
}
```
If we did not use the inline modifier, the compiler would create a separate instance of the `Function0` type in order to pass the lambda argument to the `performHavingLock()` function. 
Inlining lambdas may cause the generated code to grow.
However, if we do it reasonably (that is, avoiding inlining large functions), it will pay off in performance. 


**There's more...**

If you want only some lambdas passed to the function to be inlined, you can mark some function parameters with the noinline modifier:
```kotlin
inlinefunfoo(inlined:()->Unit,noinlinenotInlined:()->Unit){
      // ...
}
```
Kotlin also allows declaring inline class properties.
The inline modifier can be used with getter and setter methods of properties that don't have a backing field. 
For example:
```kotlin
valfoo:Foo
inlineget()=Foo()
 
varbar:Bar
get()= //...
inlineset(v){/*...*/}
```
We can also annotate an entire property:
```kotlin
inlinevarbar:Bar
get()= //...
set(v){/*...*/}
```
As a result, the inlined getters and setters are going to be represented in the same way as the regular inline functions.

###Infix notations for functions

To bring our code closer to the natural language, Kotlin provides `infix` notations for the functions containing a single parameter.
This way, we can invoke the function without using brackets.
In this recipe, we are going to learn how to design an `infix` extension function for the `String` type, named `concat()`, which is responsible for the concatenation of two string values.

**Getting ready**

In order to enable an `infix` notation for the function, we simply need to add the `infix` keyword before the function header.


_How to do it..._

Declare the `concat()` extension function and implement its body:

`infix fun String.concat(next: String): String= this + next`

**How it works...**

Let's test the `concat()` function by running the following code:

`print("This" concat "is" concat "weird")`

Great! We have just printed out the following text to the console:

`Thisisweird`

**There's more...**

The Kotlin standard library uses the `infix` notation extensively. 
You can benefit from `infix` functions to shape your code the clean way.
One `infix` function worth noting is the `to()` extension function provided for the `Map.Entry<K, V> class`, which allows you to declare map entries in a minimalistic way:

`val namesWithBirthdays: Map<String, LocalDate> = mapOf("Agata" to LocalDate.of(1990, 1, 18))`

The `to()` extension function is declared for a generic type `A` and generic argument of type `B`, which returns an instance of a `Pair<A, B>` class.
There are plenty of other functions supporting `infix` notations available in the standard library.
If you check the implementation of the ones you are using on a daily basis, it may turn out they are available in the `infix` form too.

**See also**

• You can learn about another cool feature that helps to shape the code to be more natural to read in the `Overloading` operators recipe

###Smart types checking with generic reified parameters
While implementing functions that support generic type arguments, we often deal with the need to provide additional information about object types at runtime.
On the **JVM** platform, types have their representations in the `Class<T>` class instances. 
For example, we can face such a need while parsing **JSON** formatted data to the **Kotlin** class instances using the **Gson** library:
```kotlin
data class ApiResponse(val gifsWithPandas: List<ByteArray>)
data class Error(val message: String)
 
fun parseJsonResponse(json: String): ApiResponse {
    Gson().fromJson(json, ApiResponse::class.java)
}
```
Normally, we can't access the generic type argument at runtime because of a **JVM** types erasure. 
However, **Kotlin** allows you to overcome this limitation because it preserves the type argument at runtime.
In this recipe, we are going to tune up **Gson's** `fromJson(json: String, Class<T>)` function to get rid of the additional type argument.

**Getting ready**

Make sure you have the Gson dependency included in your project (https://github.com/google/gson). 
If you are using Gradle, build script that you can fetch it with the following declaration:
```groovy
dependencies {
    compile 'com.google.code.gson:gson:2.8.2'
}
```
In order to make a type argument accessible at runtime, we need to mark it with the reified modifier and mark the function as `inline`.

_How to do it..._

• Create a new file where we will put an extension function implementation (for example, `GsonExtensions.kt`)

• Inside the file, declare an extension function for the **Gson** class:
```kotlin
inline fun <reified T>Gson.fromJson(json: String): T {
    return fromJson(json, T::class.java)
}
```

_How it works..._

We have implemented an extension function for the **Gson** type. 
Thanks to adding the reified modifier, we can access the generic type argument at runtime and pass it to the original `fromGson()` function.
As a result, we are able to use the more elegant version of the `fromGson()` function in our code:
```kotlin
data class ApiResponse(val gifsWithPandas: List<ByteArray>)
val response = Gson().fromJson<ApiResponse>(json)
```
We could also benefit from Kotlin smart casting and omit the explicit type declaration from the function call:

`val response: ApiResponse = Gson().fromJson(json)`

###Overloading operators  

The **Kotlin** language provides a set of operators which have their own symbol (for example, `+`, `-`, `*`, or `/`) and a priority defined.
At the time of compilation, the Kotlin compiler transforms them into associated function calls or even more complex statements.
We are also able to override an operator and declare its custom underlying implementation for a specified type.
This implementation would be applied to the instances of the specified type the operator was used with.
In this recipe, we are going to define a class called Position, representing the current coordinates of the point in a three-dimensional space.
Then, we are going to implement custom FP.plus and minus operators for our class to provide a simple way of applying a geometric transformation to its instances. 
As a result, we want to be able to update the coordinates of the point represented by the Position class using the `+` and `-` operator symbols.

**Getting ready**

In order to overload the operator for the specific type, we need to provide a member function, or an extension function with a fixed name corresponding to the operator.
Additionally, functions that overload operators need to be marked with the `operator` keyword.
In the following tables, you can find grouped sets of operators available in the language with their corresponding expressions to which they are translated to:

Unary prefix
Operator
Expression

`+a`

`a.unaryPlus()`

`-a`

`a.unaryMinus()`

`!a`

`a.not()`
 
Incrementation and decrementation
Operator
Expression

`a++`

`a.inc()`

`a--`

`a.dec()`
 
Arithmetic
Operator
Expression

`a + b`

`a.FP.plus(b)`

`a - b`

`a.minus(b)`

`a * b `

`a.times(b)`

`a / b`

`a.div(b)`

`a % b`

`a.rem(b)`

`a..b`

`a.rangeTo(b)`
 
In operator
Operator
Expression

`a in b`

`b.contains(a)`

`a !in b`

`!b.contains(a)`
 
Indexed access
Operator
Expression

`a[i]`

`a.get(i)`

`a[i, j]`

`a.get(i, j)`

`a[i_1, ..., i_n]`

`a.get(i_1, ..., i_n)`

`a[i] = b`

`a.set(i, b)`

`a[i, j] = b`

`a.set(i, j, b)`

`a[i_1, ..., i_n] = b`

`a.set(i_1, ..., i_n, b)`
 
Invoke operator
Operator
Expression

`a()`

`a.invoke()`

`a(i)`

`a.invoke(i)`

`a(i, j)`

`a.invoke(i, j)`

`a(i_1, ..., i_n)`

`a.invoke(i_1, ..., i_n)`
 
Augmented assignment
Operator
Expression

`a += b`

`a.plusAssign(b)`

`a -= b `

`a.minusAssign(b)`

`a *= b`

`a.timesAssign(b)`

`a /= b`

`a.divAssign(b)`

`a %= b`

`a.remAssign(b)`
 
Equality and comparison
Operator
Expression

`a == b`

`a?.equals(b) ?: (b === null)`

`a != b`

`!(a?.equals(b) ?: (b === null))`

`a > b`

`a.comareTo(b) > 0`

`a < b`

`a.compareTo(b) < 0`

`a >= b`

`a.compareTo(b) >= 0`

`a <= b`

`a.compareTo(b) <= 0`

_How to do it..._

• Declare the Position data class with `x, y, z` properties related to the current position in the Cartesian coordinates system:

`data class Position(val x: Float, val y: Float, val z: Float)`

• Add a FP.plus operator implementation for the Position class:
```kotlin
data class Position(val x: Float, val y: Float, val z: Float) {
    operator fun FP.plus(other: Position) =
        Position(x + other.x, y + other.y, z + other.z)
}
```
• Overload the minus operator:
```kotlin
data class Position(val x: Float, val y: Float, val z: Float) {
    operator fun FP.plus(other: Position) =
        Position(x + other.x, y + other.y, z + other.z)

    operator fun minus(other: Position) =
        Position(x - other.x, y - other.y, z - other.z)
}
```

_How it works..._

Now we can use the `Position` class together with `FP.plus` and `minus` operators.
Let's try using the `minus` operator:
```kotlin
val position1 = Position(132.5f, 4f, 3.43f)
val position2 = position1 - Position(1.5f, 400f, 11.56f)
print(position2)
```
That's it. 
The preceding code is going to print the following result to the console:
`Position(x=131.0, y=-396.0, z=-8.13)`



**There's more...**

Some operators have their corresponding compound assign operators defined. 
Once we have overloaded the FP.plus and minus operators, we can use the `plusAssign` (`+=`) and `minusAssign` (`-=`) operators automatically.
For example, we can use the `plusAssign` operator to update the `Position` instance state as follows:
```kotlin
var position = Position(132.5f, 4f, 3.5f)
     position += Position(1f, 1f, 1f)
      print(position)
```
As a result, we will get the `position` variable with the following state:

`Position(x=133.5, y=5.0, z=4.5)`

It is important to note that the `assign` operator returns the Unit.
This makes it a better choice than an original basic operator (for example, `FP.plus` or `minus`) in terms of memory allocations efficiency when updating an instance. 
In contrast, the base operators are returning new instances every time.
It is good to know that Kotlin offers operators overloading for Java classes as well.
To overload the operator, we just need to add a proper method to the class that has the name of the operator and the `public` visibility modifier. 
Here is what the Java version of the `Position` class with the overloaded `FP.plus` operator would look like:
```java
public class Position {
    private final float x;
    private final float y;
    private final float z;

    public Position(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public float getZ()
    {
        return z;
    }
    
    public Position FP.plus(Position pos)
    {
        return new Position (pos.getX() + x, pos.getY()+y,
        pos.getZ() + z);
    }
}
```
And here is how it could be used in Kotlin code:

`val position = Position(2.f, 9.f, 55.5f) += (2.f, 2.f, 2.f)`

The Kotlin standard library also contains predefined implementations of different operators. 
One that you should use on a daily basis is the FP.plus operator for a `MutableCollection` type.
This allows for adding new elements to the collection in the following way:
```kotlin
val list = mutableListOf("A", "B", "C")
list += "D"
print(list)
```
As a result, the preceding code will print the following output to the console:

`[A, B, C, D]`
