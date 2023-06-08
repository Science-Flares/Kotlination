##Tasteful Design Patterns Adopting Kotlin Concepts

In this chapter, we will cover the following recipes:

• Implementing the Strategy pattern

• Exploring the power of the Delegation pattern

• Implementing delegated class properties

• Tracking state with the Observer pattern

• Restricting property updates with the Vetoable delegate

• Implementing the advanced Observer pattern by defining a custom property delegate

• Working with the Lazy delegate

• Implementing builders the smart way


##Introduction

The following chapter is going to present popular, FP.general-purpose design patterns applicable to a range of programming problems. 
The following recipes focus on exploiting Kotlin's built-in language support for implementing specific concepts and patterns.
Apart from basic design patterns, such as Strategy or Builder, the chapter will focus on different usages of Delegation in a diverse set of applications and scenarios. 
Once you get familiar with the concepts presented in this chapter, you will be able to utilize the language's built-in features while designing and developing elegant and reliable systems.

###Implementing the Strategy pattern

The Strategy design pattern is used to provide an interchangeable set of strategies that can be applied to a given input and return an output of a specific type. 
We can understand the concept of a strategy as an action or an algorithm that can be applied to the input.
A mechanism responsible for processing input should be able to switch between provided strategies at runtime.
To illustrate the Strategy pattern, we are going to implement a text-formatting mechanism that allows us to apply a transformation to the input text and print it to the console. 
We are going to implement a class called Printer, which will provide a `printText(text: String)` function for printing the text to the console.
Before printing out the text to the console, the Printer class will perform a transformation of the given text parameter according to the selected text formatting strategy. 

_How to do it..._

• Implement the Printer class:
```kotlin
class Printer(val textFormattingStrategy: (String) ->String) {
    fun printText(text: String) {
        val processedText = textFormattingStrategy(text)
        println(processedText)
    }
}
```
• Add sample strategies:
```kotlin
val lowerCaseFormattingStrategy: (String) ->String = {
    it.toLowerCase()
}
val upperCaseFormattingStrategy: (String) ->String = {
    it.toUpperCase()
}
```

_How it works..._

Let's start by testing how our Printer class works in action. 
First, declare two instances of the Printer class—the first one with the lowerCaseFormattingStrategy for the textFormattingStrategy property, and the second one with upperCaseFormattingStrategy:
```kotlin
val lowerCasePrinter = Printer(lowerCaseFormattingStrategy)
val upperCasePrinter = Printer(upperCaseFormattingStrategy)
Next, let's use them to format and display the following text:
val text = "This functional-style Strategy pattern looks tasty!"
 
lowerCasePrinter.printText(text)
upperCasePrinter.printText(text)
```

The following output will print to the console:
```text
this functional-style strategy pattern looks tasty!
THIS FUNCTIONAL-STYLE STRATEGY PATTERN LOOKS TASTY!
```
The `Printer.textFormattingStrategy` property is a function that takes a single String argument and returns a `String` type as the output. 
It is invoked inside the `printText(text: String)` function with the text parameter, and its output is returned by the function. 

**There's more...**

You can practice by implementing your own text-formatting strategies.
Try to implement a new text formatting strategy, called capitalizeFormattingStrategy, that will be responsible for capitalizing the first letter of the input text. 
Once you're done, create a new strategy be composed of the two implemented earlier—lowerCaseFormattingStrategy and capitalizeFormattingStrategy.
You can refer to the Function composition recipe in [Chapter 3], Shaping Code with Kotlin Functional Programming Features to learn more about the generic way of composing functions together. 

**See also**

• If you are not familiar with the concept of **higher-order** functions used to declare the `Printer.textFormattingStrategy` property, you can explore the Working with **higher-order** functions recipe from [Chapter 3], Shaping [Code with Kotlin Functional Programming Features]

###Exploring the power of the Delegation pattern

The **Delegation** pattern is a great alternative to typical inheritance of classes.
Delegation allows a certain class to be derived from another one or to implement an interface.
However, under the hood, the derived class is not a subclass of the base class but the composition is used instead to provide the properties of the base class to the derived one.
Whenever a request to the properties of the base class part is made, it is being redirected to a delegated object. 
This is comparable to subclasses deferring a request to parent classes. 
However, delegation not only allows us to achieve the same code reusability as inheritance does, it's also much more powerful and customizable.
Kotlin makes the Delegation pattern even more impressive because it provides a built-in support for declaring delegates using the `by` keyword.
In this recipe, we are going to implement a combination of dependent classes modeling a simple book library system.
We are going to write a code of a given UML class diagram that describes a set of dependent classes using inheritance.
However, we are going to use the delegation pattern instead of any inheritance occurrences.

**Getting ready**

We are going to work on implementing a set of the following classes using the Delegate pattern instead of inheritance:



In this class diagram, you can see two base classes that are being derived from the BasePublication class with its Book and Magazine subclasses, and the BaseUser class, which is extended by the Member and Librarian subclasses. 
Note that those base classes are implementing corresponding interfaces declaring their properties. 
The BaseUser class implements the User interface, and the BasePublication class implements the Publication interface. 
There is also the Rental interface, which declares methods implemented by the `Book` subclass.


In order to implement the delegation using the language's built-in language features, we are going to operate on interfaces directly and remove any existing inheritance.
Instead of extending the `BaseUser` and `BasePublication` base classes, we are going to use them as the properties of the final `User`, `Librarian`, `Book`, and `Magazine` classes, as presented in the following diagram:




_How to do it..._

• Declare the `Magazine` class implementing the `Publication` interface:
```kotlin
class Magazine(val number: Int,
title: String,
pageCount: Int) : Publication
```
• Delegate the `Publication` interface to the class property of the `Publication` type:
```kotlin
class Magazine(val number: Int,
val publication: Publication) :
Publication by publication
```
• Implement the Rentable interface:
```kotlin
interface Rentable {
    var currentUser: Optional<User>

    fun availableToRent() = !currentUser.isPresent

    fun doRent(user: User): Boolean {
        return if (availableToRent()) {
            currentUser = Optional.of(user)
            true
        } else {
            false
        }
    }
}
```
• Implement the `Book` class, delegating its `Publication` interface functionality to the class member:
```kotlin
class Book(val publicationDate: Instant,
val author: String,
val publication: Publication) :
Publication by publication, Rentable {

    override var currentUser: Optional<User>= Optional.empty()
}
```
• Implement the Member and Librarian classes, implementing the User interface and delegating it to their class properties:
```kotlin
class Member(val currentRentals: List<Rentable>,
name: String,
isActive: Boolean,
user: User) : User by user
 
class Librarian(user: User) : User by user

```
_How it works..._

Using the `by` keyword, we have delegated the implementation of the `User` and `Publication` interfaces to specialized objects defined as class members.
In the case of the `Book` and `Magazine` classes, the responsibilities for the Publication interface were delegated to the `publication: Publication` class properties, and, in the case of the Member and Librarian classes, the responsibilities for the User interface were delegated to the `user: User` properties.
Now, let's explore how we can work with delegated types.
Let's start by creating an instance of the `Book` class. 
We provide a `Book.publication` property of the `Publication` type by reusing the original BasePublication class declaration:
```kotlin
class BasePublication(override val title: String, 
override val pageCount: Int): Publication
```

Note that we are able to access all the public members of the `Publication` interface directly from the `Book` class instance. 
`Any` requests to those `Publication` interface properties are being redirected to the `val` publication property of the `Book` class:
```kotlin
val book = Book(Instant.now(), "Sam", 
BasePublication("Kotlin Standard Library Cookbook",
Integer.MAX_VALUE))
 
println("${book.title}written by ${book.author}has ${book.pageCount}pages.")
```
In the result, the preceding code should print the following output to the console:

`Kotlin Standard Library Cookbook written by Sam has 2147483647 pages.`

**See also**

• Another great type of Delegation design pattern is related to delegating class properties. 
You can find out more in the **Implementing delegated class properties** recipe. 



###Implementing delegated class properties

Class properties in Kotlin are more than just plain class fields. 
The key characteristic of Kotlin properties is the fact that their values are specified by accessor functions automatically.
Each class property in Kotlin has a dedicated set of accessor functions available out of the box.
By default, the Kotlin compiler generates a field storing the value of the property and its getters or setters as well. 
Each immutable `val` property has a corresponding `get()` function provided, and the mutable one declared with var keyword has the `set()` function in addition to a `get()` as well. 
We are also able to override a default implementation of the accessor function, which makes a property highly customizable and powerful.
For example, we can override the `get()` function of the property and provide a custom implementation for it, which can stop the compiler from storing the value of the property in a `field`. 
Moreover, the fact that properties are represented by their accessor functions and not by the fields values makes property delegation possible.  
The basic use cases for the property delegation include:

• Implementing lazy properties—providing the value that gets computed only upon first access

• Observable properties—listeners get notified about changes to the property

• Storing properties in a map, instead of a separate field for each property

In this recipe, we are going to learn how to create a function allowing us to easily serialize class instance into **JSON** format by to delegating its properties to be stored in a map. 

**Getting ready**

Similar to the interface delegation, the class property delegation is achieved using the `by` keyword in the following manner:
```kotlin
class MyClass {
    var property: String by MyDelegate
}
```
The object which is delegated to should implement one of the following `interfaces—ReadWriteProperty` or `ReadOnlyProperty` from the `kotlin.properties` package. 
Those interfaces expose the `getValue()` and `setValue()` functions, which provide values for the property.

We are going to use the **Gson** library to convert objects into their **JSON** format representation. 
It's a widely used Java library for working with **JSON-formatted** objects. 
You can learn more about the library on its **GitHub** site (https://github.com/google/gson). 
If you're using the Gradle build tool, you need to add the **Gson** artifact to the project dependencies: 
```groovy
dependencies {
    implementation 'com.google.code.gson:gson:2.8.4'
}
```

_How to do it..._

• Implement the `Client` class containing a data property of a `Map<String, Any>` type:
```kotlin
data class Client(private val data: Map<String, Any>)
```
• Implement the `CreditCard` class:
```kotlin

data class CreditCard(val holderName: String,
val number: String,
val cvcCode: String,
val expiration: Long)
```
• Add the `name`, `email`, and creditCards properties to the `Client` class and delegate them to the `data` property:
```kotlin

data class Client(private val data: Map<String, Any>) {
    val name: String by data
    val email: String by data
    val creditCards: List<CreditCard> by data
}
```
• Implement the `toJson(): String` member function, allowing us to serialize a `Client` type object into **JSON** format, and the `fromJson(json: String): Client` utility function responsible for the opposite operation:
```kotlin
data class Client(private val data: Map<String, Any>) {
    val name: String by data
    val email: String by data
    val creditCards: List<CreditCard> by data

    /**
     * Function for serializing instance of Client class into
    JSON format
     */
    fun toJson(): String = gson.toJson(data)

    companion object {
        private val gson = Gson()

        /**
         * Utility function for instantiating Client class from
        JSON string
         */
        fun fromJson(json: String): Client {
            val mapType = object : TypeToken<Map<String,
                    Any>>() {}.type
            val data: Map<String, Any>= gson.fromJson(json,
            mapType)
            return Client(data)
        }
    }
}
```

_How it works..._

Class properties can be delegated to a Map or `MutableMap` instance, which contains keys of the `String` type and values of the `Any` type. 
The map's keys correspond to the names of the class properties, and the map's values associated with them store the properties values.
The map that is delegated to is being updated dynamically whenever the delegated property is updated.
Let's take a look at how we can make use of the Client class implemented in this recipe.
We can instantiate the `Client` class by passing the Map instance to the class constructor:
```kotlin

val SAMPLE_CLIENT_MAP = mapOf("name" to "Mark Zuck","email" to "mark@fb.com","creditCards" to listOf(CreditCard("Mark Zuckerberg", "123345456789", "123",1527330705017),CreditCard("Mark Zuckerberg", "987654321", "321",1527330719816)))
val client1 = Client(SAMPLE_CLIENT_MAP)
```

We can also instantiate the `Client` class using the `fromJson()` function, passing a string containing a **JSON** representation of the sample `Client` type object:
```kotlin
@Language("JSON")
const val SAMPLE_CLIENT_JSON ="{\n \"name\": \"Mark Zuck\",\n \"email\": \"mark@fb.com\",\n \"creditCards\": [\n{\n \"holderName\": \"Mark Zuckerber\",\n \"number\": \"123345456789\",\n \"cvc\": \"123\",\n \"expiration\": 1527330705017\n},\n{\n \"holderName\": \"Mark Zuckerber\",\n \"number\": \"987654321\",\n \"cvc\": \"321\",\n \"expiration\": 1527330719816\n}\n]\n}"
val client2 = Client.fromJson(SAMPLE_CLIENT_JSON)
```

If you are working with **IntelliJ IDE**, you can use a cool Language injection feature that allows us to inject another language's code snippet as a String type and provides support for the language-specific syntax for editing and formatting. 
You can use it to inject **JSON** snippets as a Kotlin String.
You can learn more about it at the official JetBrains tutorial (https://www.jetbrains.com/help/idea/using-language-injections.html).
Under the hood, the `Client.fromJson()` function uses **Gson** to convert **JSON** data to the `Map<String, Any>` instance.
We can now test those two ways and print the contents of both the `client1` and `client2` objects to the console:
```text
println("name: ${client1.name}, mail: ${client1.email}, cards: ${client1.creditCards}")
println("name: ${client2.name}, email: ${client2.email}, cards: ${client2.creditCards}")
```

As the result, we are going to get the following output printed to the console:
```text
name: Mark Zuck, email: mark@fb.com, cards: [{holderName=Mark Zuckerber, number=123345456789, cvc=123, expiration=1.527330705017E12}, {holderName=Mark Zuckerber, number=987654321, cvc=321, expiration=1.527330719816E12}]
 
name: Mark Zuck, email: mark@fb.com, cards: [CreditCard(holderName=Mark Zuckerberg, number=123345456789, cvcCode=123, expiration=1527330705017), CreditCard(holderName=Mark Zuckerberg, number=987654321, cvcCode=321, expiration=1527330719816)]
```

In both cases, all the class properties are stored in the data `map` object, no matter which way of instantiating the `Client` class was chosen. 
The delegation of the properties to the map allowed us to implement a mechanism that exports the state of the `Client` object to the map automatically.
The `map` object was stored internally in the `Client` class, however, it could be declared anywhere else as well.

**There's more...**

In this recipe, we have created the Client class, which contains immutable val properties.
In order to store mutable `var` properties, we can use a `MutableMap` instance instead of a **read-only** `Map`. 
Built-in support for class properties is a powerful feature of the language. 
It brings awesome possibilities to shape your code in a neat way. 
You should definitely give it a try when working on a more complex project.
For example, you can delegate the properties of your entities to be read and written directly to and from the database. 
There is also a group of ready-to-use delegates built into the standard library, such as the `Lazy` or Observable delegates.
You can learn more about their application in the next recipes in this chapter.
You can explore the full set of built-in delegates in the official standard library docs: https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-delegates/index.html.

**See also**

• If you'd like to explore the concept of interface delegation, you can take a look at the Exploring the power of `Delegation` pattern recipe

• You should also learn about the standard property delegates provided by the standard library

###Tracking state with the Observer pattern

The Observer pattern is a concept in which an object allows us to subscribe to the changes of its state and notifies a set of its observers automatically whenever there is a change of the object state.
The implementation of the Observer pattern in Kotlin is pretty easy with the help of the built-in Observable property delegate offered by the standard library.
In this recipe, we are going to implement an observable variable that will allow us to subscribe to the changes in its state. 
The subscribed listener should be notified immediately after any state updates.
In the following example, we are going to declare the `temperature: Int variable` and subscribe to its changes. 

_How to do it..._

• Define an initial value for the temperature variable:
```kotlin
val initialValue = 1
```
• Declare the listener for the variable that will be observed:
```kotlin
val initialValue = 1
val changesListener: (KProperty<*>, Int, Int) ->Unit =
{ _, _: Int, newValue: Int ->println("Current temperature: $newValue") }
```
• Declare the temperature variable, delegating its value to the `ReadWriteProperty` instance returned by the `Delegates.observable()` function:
```kotlin

val initialValue = 1
val changesListener: (KProperty<*>, Int, Int) -> Unit = { _, _: Int, newValue: Int ->println("Current temperature: $newValue") }
var temperature: Int by Delegates.observable(initialValue, changesListener)
```
_How it works..._

We are delegating the var temperature variable to the result of the `Delegates.observable()` function, which returns an instance of the `ReadWriteProperty` class. 
That fact makes it possible to declare temperature as a mutable variable.
The `observe()` function takes two arguments—the initial value, and an instance of the hook function that is going to be invoked on every change made to the delegated variable.
In our case, we are instantiating the function as the lambda block, which is supposed to print the new temperature value to the console.
Let's test how our implementation is going to work. 
We are going to modify the value of the temperature directly a couple of times:
```kotlin
temperature = 10
temperature = 11
temperature = 12
temperature = 30
```
As the result, we get the following output:
```text
Current temperature: 10
Current temperature: 11
Current temperature: 12
Current temperature: 30
```
On each change of the temperature value, the listener function is being invoked with the previous and new values of the property passed to its parameters.
 

**See also**

• If you'd like to explore how the property delegates are working under the hood, take a look at the **Implementing delegated class properties** recipe


###Restricting property updates with the `Vetoable` delegate

In this recipe, we are going to explore the usage of the `Vetoable` delegate offered by the standard library. 
Similar to the Observable, the `Vetoable` tracks the changes applied to the delegated property.
However, the `Vetoable` delegate is able to refuse to update the delegated property if a predefined update condition is not met. 
We are going to declare a variable of the Int type and specify the update condition, allowing us to update the variable only if the absolute value of change is greater than or equal to `10`.

_How to do it..._

• Let's start by defining an initial value for the temperature variable:
```kotlin
val initialValue = 1
```
• Define the update condition for the observed variable:
```kotlin

 import kotlin.math.absval initialTemperature = 1
val updateCondition: (KProperty<*>, Int, Int) ->Boolean = { _, oldValue: Int, newValue: Int -> abs(oldValue - newValue) >= 10 }
```
• Declare the `temperature: Int` variable and delegate it to the result of the `Delegates.vetoable()` function:
```kotlin

val initialTemperature = 1
val updateCondition: (KProperty<*>, Int, Int) ->Boolean =
{ _, oldValue: Int, newValue: Int ->Math.abs(oldValue - newValue) >= 10 }
var temperature: Int by Delegates.vetoable(initialTemperature, updateCondition)
```

_How it works..._

We are delegating the var temperature variable to the result of the `Delegates.vetoable()` function, which returns an instance of the ReadWriteProperty class. 
That fact makes it possible to declare temperature as a mutable variable. 
The `vetoable()` function takes two arguments—the initial value, and an instance of the hook function that is going to be invoked on every change made to the delegated variable.

That function provides the current value of the delegated variable and a candidate for the new value. 
As the result, the function returns the Boolean—true if the value can be updated, and false if the update condition is not met.
In our case, we are instantiating the function as the lambda block in which we check whether the absolute value of change is greater than or equal to `10`:
```kotlin
{ _, oldValue: Int, newValue: Int ->Math.abs(oldValue - newValue) >= 10 }
```
Let's test how our implementation is going to work.
We are going to modify the value of temperature directly a couple of times with different values and verify whether the update was approved by printing the temperature state to the console:
```kotlin
temperature = 10
println("Current temperature: $temperature")
 
temperature = 11
println("Current temperature: $temperature")
 
temperature = 12
println("Current temperature: $temperature")
 
temperature = 30
println("Current temperature: $temperature")
```
As the result, we get the following output printed out:
```text
Current temperature: 1
Current temperature: 11
Current temperature: 11
Current temperature: 30
```
As you can see, the value of temperature remains unchanged whenever we are assigning it with values that don't satisfy the specified condition.

**See also**

• In the next recipe, Implementing the advanced observer using a custom property delegate, we are going to combine together the functionalities of the Observable and `Vetoable` delegates by implementing our custom delegate. 
Read on to explore how to both filter updates of the property and implement the Observer pattern in one property delegate.


###Implementing the advanced Observer pattern by defining a custom property delegate

In this recipe, we are going to implement a custom, generic property delegate combining features of the Observable and `Vetoable` delegates available in the standard library. 
In other words, we want to implement a property delegate that allows us to notify a subscribed listener about any changes made to the observed property. 
At the same time, we also want the delegate to allow filtering of the updates made to the delegated property. 
In this example, we are going to declare the `temperature: Int` variable delegated to our custom implementation of the `ObservableVetoable` delegate class.
We are going to create a generic class that allows us to pass the initial value, a function responsible for filtering property updates, and a function that will be invoked immediately after the change to the property is made.

_How to do it..._

• Define the custom property delegate called `ObservableVetoableDelegate` as a subclass of the `ObservableProperty` class:
```kotlin
class ObservableVetoable<T>(initialValue: T,
                            val updatePrecondition: (old: T, new: T)->Boolean,
                            val updateListener: (old: T, new: T) -> Unit) 
    : ObservableProperty<T>(initialValue = initialValue) {

    override fun beforeChange(
        property: KProperty<*>,
        oldValue: T,
        newValue: T
    ): Boolean =
        updatePrecondition(oldValue, newValue)

    override fun afterChange(
        property: KProperty<*>,
        oldValue: T,
        newValue: T
    ) =
        updateListener(oldValue, newValue)
}
```



• Define the `initialTemperature`, `updatePrecondition`, and `updateListener` arguments required by the `ObservableVetoable` class:
```kotlin
val initialTemperature = 1
val updatePrecondition: (Int, Int) ->Boolean =
{ oldValue, newValue ->Math.abs(oldValue - newValue) >= 10 }
 
val updateListener: (Int, Int) ->Unit = { _, newValue ->println(newValue) }
```
• Declare the temperature: Int variable by delegating it to the `ObservableVetoable` class instance:
```kotlin
var temperature: Int by ObservableVetoable(initialTemperature,updatePrecondition,updateListener)
```

_How it works..._

We have defined the `ObservableVetoable` class and delegated the var temperature: Int variable to the `ObservableVetoable` instance.
Our `ObservableVetoable` class extends the `ObservableProperty` class, which implements the `ReadWriteProperty` interface under the hood. 
Thanks to this, `ObservableProperty` allows us to delegate mutable properties to it. 
The `ObservableProperty` class also has the `beforeChange(): Boolean` and `afterChange(): Unit` open functions, which are being invoked inside the `setValue()` function:
```kotlin
public override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
    val oldValue = this.value
    if (!beforeChange(property, oldValue, value)) {
        return
    }
    this.value = value
    afterChange(property, oldValue, value)
}
```



As you can see, whenever the `delegated` property is assigned to a new value, the `beforeChange()` function is invoked to check whether the new value meets specified conditions. 
If the conditions are met, the property gets updated and the `afterChange()` function is called.
In fact, our `ObservableVetoable` class takes instances of the function implementations, updatePrecondition and updateListener, which override the `beforeChange()` and `afterChange()` base functions. 
This way, we are able to both observe the changes made to the `delegated` property and notify the changes listener immediately to filter the changes being made to it.
For example, we can test our implementation by updating the temperature variable five times with different values:
```text
temperature = 11
temperature = 12
temperature = 13
temperature = 14
temperature = 30
```
As a result, we are going to have only two lines printed to the console:
```text
11
30
```
This means that our mechanism is working properly because our update precondition function is checking whether the absolute value of change is greater than or equal to `10`.
`updateListener()` is called only when the new value is accepted by the `updatePrecondition()` function.

**See also**

• If you'd like to get familiar with the basics of property-delegation support in Kotlin, take a look at the **Implementing delegated class properties** recipe, which contains an in-depth introduction and explanation of the language support for the delegation concept

• You can also explore the `Restricting` property's updates with Vetoable delegate and the Tracking state with Observer pattern recipes to get familiar with the Observer and Vetoable delegates provided by the standard library

###Working with the Lazy delegate

`Lazy` initialization is another design pattern that has its dedicated delegate implementation included in the standard library. 
The concept of `lazy` initialization refers to the strategy of delaying the creation of an object, calculation of a value, or execution of some expensive operation until the first time it's needed. 
In this recipe, we are going to define a sample class, `CoffeeMaker`, and declare an object of its type via the `Lazy` delegate.
Then we are going to perform example operations on the object to explore how the `lazy` delegate is working in action.

_How to do it..._

• Let's start with defining the `CoffeeMaker` class:
```kotlin
class CoffeeMaker {
init {
println("I'm being created right now... Ready to make some
coffee!")
}
 
fun makeEspresso() {
    println("Un espresso, per favore!")
}
 
fun makeAmericano() {
    print("Un caffè americano, per favore!")
}
}
```
• Declare a variable of the `CoffeMaker` type using the `lazy` delegate:
```kotlin
val coffeeMaker: CoffeeMaker by lazy { CoffeeMaker() }
```

_How it works..._

Let's test out how the `coffeeMaker` instance is going to behave by running the following code:
```kotlin

val coffeMaker: CoffeeMaker by lazy { CoffeeMaker() }
println("Is the CoffeMaker created already?")
 


coffeMaker.makeEspresso()
coffeMaker.makeAmericano()
```
And here is the output printed out to the console:
```text
Is the CoffeMaker created already?
I'm being created right now... Ready to make some coffe!
Un espresso, per favore!
Un caffè americano, per favore!
```
As you might have imagined, the constructor of the `CoffeeMaker` class is being called only upon the first request to the `coffeeMaker` variable. 
In fact, the lambda block passed to the lazy function is invoked on the call to the `coffeeMaker.makeEspresso()` function.
Once the `CoffeeMaker` object is instantiated, it's reused for any consecutive operations performed on it.
By default, the evaluation of `lazy` properties is synchronized; the value is computed only in one thread, and all threads will see the same value. 
If the synchronization of the initialization delegate is not required so that multiple threads can execute it simultaneously, pass `LazyThreadSafetyMode.PUBLICATION` as a parameter to the `lazy()` function.
And if you're sure that the initialization will always happen on a single thread, you can use the `LazyThreadSafetyMode.NONE` mode, which doesn't incur any thread-safety guarantees and the related overhead (https://kotlinlang.org/docs/reference/delegated-properties.html#lazy).
The `lazy()` function creates and returns an instance of the `Lazy<T>` interface:
```kotlin
public interface Lazy<out T> {
    public val value: T
    public fun isInitialized(): Boolean
}
```
As you can see, the value property is immutable, and it's not possible to declare a mutable variable or property using the `lazy` delegate. 
Under the hood, the `Lazy` implementation returns a specific value of the object it holds and is checking internally if it has been already evaluated.
During the first access to the object, the initializer function passed to the `lazy` function as an argument is being executed and its result is being assigned to the dedicated property.
Later, the cached value is going to be used instead of re-evaluating the value each time.


**See also**

• If you'd like to explore how the property delegates are working under the hood, take a look at the **Implementing delegated class properties** recipe

###Implementing builders the smart way

The **Builder** design pattern is one of the most commonly used mechanisms for instantiating complex types in the **Java** language. 
It was strongly recommended by _Joshua Bloch_ in the **Effective** **Java book**. 
Bloch says the builders should be used when we need to implement multiple constructors. He also mentions that builder pattern simulates named optional parameters. 
However, in Kotlin, those arguments for implementing a specialized builder class are no longer valid.
Kotlin allows us to provide default values to the class constructor arguments and properties and it has built-in support for named arguments.
Given those Kotlin features, there is no need to implement the builders in most scenarios since we can simply achieve their functionality using the language's built-in concepts.
However, in Kotlin, we can adapt the Builder pattern to achieve even more. 
We are going to utilize the concept of the builder, together with higher-order functions and the possibility of inlining lambda parameters, to define the **DSL-like** syntax for instantiating instances of a given class.

**Getting ready**

Let's assume we have the `Dialog` class specified in some external dependency with an interface provided as follows:
```kotlin
class Dialog {
    lateinit var title: String
    lateinit var message: String
    lateinit var messageColor: String
    lateinit var image: ByteArray

    fun show() = println("Dialog...\n$this")

    override fun toString() = "Title: $title \nImage: $image \nMessage:
    $message"
}
```


The `Dialog` class exposes the following `properties—title: String`, `message: String`, `messageColor: String`, and `image: File`.
We are going to implement a `DialogBuilder` class, which allows us to instantiate the `Dialog` class using the builder pattern.
As the result, we would like to create a mechanism that allows us to instantiate the `Dialog` type using a **DSL-like** syntax similar to the **JSON** format:
```kotlin
val dialog: Dialog = 
dialog {
    title {
        "Title"
    }
    message {
        text = "Message"
        color = "#FF0000"
    }
    image {
        File("path")
    }
}
```
_How to do it..._

• Create the `DialogBuilder` class containing properties responsible for holding values needed by the `Dialog` class properties:
```kotlin
class DialogBuilder() {
    private var titleHolder = "-"
    private var messageHolder = StyleableText("-", "#000")
    private var imageHolder: File = File.createTempFile("empty", "")

    class StyleableText(
        var text: String = "",
        var color: String = "#000"
    )
}
```
• Add the `title()`, `message()`, and `image()` functions, allowing us to modify the `titleHolder`, `message`, and `image` properties:
```kotlin

class DialogBuilder() {
    private var titleHolder = "-"
    private var messageHolder = StyleableText("-", "#000")
    private var imageHolder: File = File.createTempFile("empty", "")

    fun title(block: () -> String) {
        titleHolder = block()
    }

    fun message(block: StyleableText.() -> Unit) {
        messageHolder.apply { block() }
    }

    fun image(block: File.() -> Unit) {
        imageHolder.apply { block() }
    }

    class StyleableText(
        var text: String = "",
        var color: String = "#000"
    )
}
```
• Add the `build()` function, returning the `Dialog` class instance:
```kotlin

class DialogBuilder() {
    private var titleHolder = "-"
    private var messageHolder = StyleableText("-", "#000")
    private var imageHolder: File = File.createTempFile("empty", "")

    fun title(block: () -> String) {
        titleHolder = block()
    }

    fun message(block: StyleableText.() -> Unit) {
        messageHolder.apply { block() }
    }

    fun image(block: File.() -> Unit) {
        imageHolder.apply { block() }
    }

    fun build(): Dialog = Dialog().apply {
        title = titleHolder
        message = messageHolder.text
        messageColor = messageHolder.color

        imageHolder.apply {
            image = readBytes()
        }
    }

    class StyleableText(
        var text: String = "",
        var color: String = "#000"
    )
}
```
• Declare a constructor taking a function responsible for initialization of the `DialogBuilder` class:
```kotlin
class DialogBuilder() {
    private var titleHolder = "-"
    private var messageHolder = StyleableText("-", "#000")
    private var imageHolder: File = File.createTempFile("empty", "")

    constructor(initBlock: DialogBuilder.() -> Unit) : this() {
        initBlock()
    }

    fun title(block: () -> String) {
        titleHolder = block()
    }

    fun message(block: StyleableText.() -> Unit) {
        messageHolder.apply { block() }
    }

    fun image(block: File.() -> Unit) {
        imageHolder.apply { block() }
    }

    fun build(): Dialog = Dialog().apply {
        title = titleHolder
        message = messageHolder.text
        messageColor = messageHolder.color

        imageHolder.apply {
            image = readBytes()
        }
    }

    class StyleableText(
        var text: String = "",
        var color: String = "#000"
    )
}
```

• Implement the `dialog()` helper function, taking a function responsible for initializing `DialogBuilder` and returning the `Dialog` class instance:
```kotlin
fun dialog(block: DialogBuilder.() ->Unit): Dialog = DialogBuilder(block).build()
```

_How it works..._

Let's start by testing how we can use our `dialog()` function in action.
Let's use it to define a sample `Dialog` class instance:
```kotlin
val dialog =
dialog {
    title {
        "Warning!"
    }
    message {
        text = "You have 99999 viruses on your computer!"
        color = "#FF0000"
    }
    image {
        File.createTempFile("red_alert", "png")
    }
}
```
Now, we can invoke the `show()` function on the dialog variable, which is going print the following output to the console:
```text
Dialog...
Title: Warning! 
Image: [B@548c4f57 
Message: You have 99999 viruses on your computer!
```
That's pretty cool! The `DialogBuilder` class allows us to compose instances of the `Dialog` type in a readable and natural way.

Implementing the new syntax for the `Dialog` class composition was possible by the use of **higher-order** functions and inline notation for lambda arguments. 
Note that each of the `DialogBuilder` functions, `title()`, `message()`, and `image()`, that are preparing information about the state of the target class properties, take a single functional parameter. 
The functional arguments are passed in the form of lambda blocks.
There are two kinds of function types being used as parameters in the builder methods—the first one, which simply returns a specific value for the property, and the second one, which returns a function with a receiver type.
The second type of the function returns Unit but takes an instance of the receiver type.
Function types are allowed to have an additional receiver type, which is declared before the dot.
In the following `notation—the A.(B) -> C` type represents a function that can be invoked on a receiver object of `A` type with a parameter of `B` type and return a value of `C`. 
Inside the body of the function literal, the receiver object passed to a call becomes an implicit this, so that you can access the members of that receiver object without any additional qualifiers, or access the receiver object using `this` keyword. 
You can read more about the available function types and their applications on the official Kotlin reference: https://kotlinlang.org/docs/reference/lambdas.html#function-types.
For example, the `title(block: () -> String)` function simply invokes the block function and assigns the result to the `DialogBuilder.titleHolder` property. 
On the other hand, whenever we are dealing with complex types, such as `StyleableText`, we are using the second approach using a function with a receiver type function's argument.
For example, let's analyze the `message(block: StyleableText.() -> Unit)` function:
```kotlin
fun message(block: StyleableText.() ->Unit) {
    messageHolder.apply { block() }
}
```
Under the hood, it is executing the `block: StyleableText.() -> Unit` argument to modify the `messageHolder: StyleableText` property instance directly.
The block argument is being invoked using the `()` modifier inside the apply function, which in this case provides the access to the `messageHolder` instance via a local `this` keyword.
The same approach is used in the constructor of the `DialogBuilder` class:
```kotlin
constructor(initBlock: DialogBuilder.() ->Unit): this() {
    initBlock()
}
```

The receiver of the DialogBuilder type is being provided to the functional parameter and the function passed as initBlock is invoked inside the constructor, allowing us to modify its state.

**There's more...**

The concept of **DSL-style** builders is used extensively in many Kotlin libraries and frameworks.
It is also employed by the standard library. 
For example, we can use the html function from the `kotlinx.html` library (https://github.com/Kotlin/kotlinx.html) to generate the `HTML` code:
```kotlin
val result =
html {
    head {
        title { +"HTML encoding with Kotlin" }
    }
    body {
        h1 { "HTML encoding with Kotlin" }
        p { +"this format can be used as an alternative to HTML" }

// an element with attributes and text content
        a(href = "http://jetbrains.com/kotlin") { +"Kotlin" }
    }
}
println(result)
```
The preceding code is going to generate a valid `HTML` code and print it to the console:
```text
<html>
<head>
<title>HTML encoding with Kotlin</title>
</head>
<body>
<h1>HTML encoding with Kotlin</h1>
<p>this format can be used as an alternative to HTML</p>
<a href="http://jetbrains.com/kotlin">Kotlin</a>
</body>
</html>
```
You can explore even more awesome applications of the Builders in Kotlin at https://kotlinlang.org/docs/reference/type-safe-builders.html.

**See also**

• If you'd like to learn more about the technical details of **higher-order** functions and `inline` notation for functional parameters, you can investigate the Inlining parameters of closure type recipe from [Chapter 2], Expressive Functions and Adjustable Interfaces and the Working with **higher order** functions recipe from [Chapter 3], Shaping Code with Kotlin Functional Programming Features
