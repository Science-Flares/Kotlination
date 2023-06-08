##Best Practices for the Android, JUnit, and JVM UI Frameworks

In this chapter, we will cover the following recipes:

• Clean and safe View binding with the Android Extensions plugin

• Applying coroutines for asynchronous UI programming on Android, JavaFX, and Swing

• Easy class-serialization on Android using the @Parcelize annotation

• Implementing a custom property delegate that provides lifecycle-aware values

• Easy operations on SharedPreferences

• Less boilerplate Cursor data parsing

• Mocking dependencies with the Mockito Kotlin library

• Verifying function invocations

• Unit tests for Kotlin coroutines


###Introduction

The current chapter is going to address problems specific to the popular frameworks that **Kotlin** uses most often. 
In FP.general, it is going to focus on **Android** platform-specific aspects and asynchronous **UI** programming with coroutines both on the **Android** and **JVM** frameworks, such as **JavaFX** and **Swing**.
It will also guide you through writing effective unit tests for the **JVM** platform using the JUnit framework (https://junit.org/junit5/). 
The recipes related to unit-testing will include also more advanced topics, such as mocking dependencies with the mockito-kotlin (https://github.com/nhaarman/mockito-kotlin) library, testing asynchronous code based on the coroutines framework, and working with assertions provided by the standard library.


###Clean and safe view-binding with the Android Extensions plugin

In this recipe, we are going to explore the view-binding feature provided by the **Kotlin Android Extensions** plugin. 
It allows us to obtain references to View type elements declared in the `XML` layout files in an easy and robust way, without using the original `findViewById()` function.
We are going to declare a `TextView` element in the `Activity` layout and obtain a reference to it in order to display a sample text in it. 

**Getting ready**

In order to make use of the **Kotlin Android Extensions** plugin, we need to enable it in the **Android** project **module-level** `build.gradle` script by adding the following declaration:
apply plugin: 'kotlin-android-extensions'
You can examine the implementation and configuration of recipes related to the Android framework in the **AndroidSamples** project available in this book's **GitHub** repository: https://github.com/PacktPublishing/Kotlin-Standard-Library-Cookbook/.
To follow the Android-related recipes, you just need to create a new project in Android Studio.

_How to do it..._

• Create a new `Activity` in the project:
```kotlin
class MainActivity : AppCompatActivity() {}
```

• Implement the **UI** layout in the `activity_main.xml` file under the src/main/res/layout/ directory:
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
tools:context=".MainActivity">
 
<TextView
android:id="@+id/text_field"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:textSize="56sp"
app:layout_constraintBottom_toBottomOf="parent"
app:layout_constraintLeft_toLeftOf="parent"
app:layout_constraintRight_toRightOf="parent"
app:layout_constraintTop_toTopOf="parent" />
 
</androidx.constraintlayout.widget.ConstraintLayout>
```
• Set the layout for the `MainActivity` inside the `onCreate()` hook function:
```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
```
• Get a reference to the `TextView` declared in the `XML` layout and display a sample text in it:
```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text_field.text = "Bonjour!"
    }
}
```

_How it works..._

As the result, the `MainActivity` class is going to display a greeting inside `TextView`:

Under the hood, the Android Extensions plugin has generated extension properties for the `MainActivity` class for each of the View elements declared in the `activity_main.xml` file. 
The generated properties have the same names as the `IDs` of their corresponding layout elements.
Compared to the standard way of obtaining references to View classes using the `findViewById(): View` function, the Android Extensions binding mechanism is much cleaner and more painless.
It is also safe and robust as it does not require casting the View type to specific subclasses, and it regenerates all the extension properties whenever any changes are made to the `XML` layout files. 
Also, compared to other third-party view-binding libraries, it is much easier to use as it doesn't require any manual property declarations.
It just works seamlessly.

**There's more...**

By default, the Android Extensions plugin supports the `Activity`, `Fragment`, and `View` type containers where you can use the automatic view binding mechanism out of the box.
However, there is a possibility to use any class as an Android Extensions container, by implementing the `LayoutContainer` interface.
For example, it can be used in the `RecyclerView.ViewHolder` subclasses:
```kotlin
classViewHolder(overridevalcontainerView:View):ViewHolder(containerView),
LayoutContainer {
 funsetupItemView(title:String) { itemTitle.text = "Hello World!" }
}
```
You can learn more about Android Extensions applications in the official reference: https://kotlinlang.org/docs/tutorials/android-plugin.html.

###Applying coroutines for asynchronous _UI_ programming on _Android_, _JavaFX_, and _Swing_

Most of the **JVM-based GUI** frameworks have one thing in common—they run a specific thread that is responsible for updating the state of the application's **UI**. 
In this recipe, we're going to learn how to execute tasks asynchronously in the background and switch to the **UI** thread to update the **GUI** of the app.
We're going to create a simple counter, which is going to display the incremented integer value every second. 
The mechanism responsible for infinite counter-incrementing should operate in the background, however, it should switch to the **UI** thread context every time it needs to perform an update of the **UI** state.

**Getting ready**

The first step to start working with Kotlin **Coroutines** is to add the core-framework dependency to the project:
```groovy
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:0.23.3'
```
Apart from the **Coroutines** core dependency, we will need to add one of the framework-specific coroutines sub-modules, providing the coroutine-context implementation responsible for dispatching the coroutine on the **UI** thread.
You can find the list of coroutines' framework sub-modules in the official guide: https://github.com/Kotlin/kotlinx.coroutines/blob/master/ui/coroutines-guide-ui.md. 
In this recipe, we are going to target the Android platform, however, you can easily port the sample code to one of the supported frameworks such as, **Android**, **Swing**, or **JavaFx**.
You can examine the implementation and configuration of recipes related to the Android framework in the AndroidSamples project, available in the **GitHub** repository: https://github.com/PacktPublishing/Kotlin-Standard-Library-Cookbook/. 
To follow Android-related recipes, you just need to create a new project in Android Studio.

_How to do it..._

• Add a new `Activity` subclass:
```kotlin
class MainActivity: AppCompatActivity() {}
```
• Implement the UI layout in the `activity_main.xml` file under the src/main/res/layout/ directory:
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:app="http://schemas.android.com/apk/res-auto"
android:layout_width="match_parent"
android:layout_height="match_parent" >
 
<TextView
android:id="@+id/text_field"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:textSize="56sp"
app:layout_constraintBottom_toBottomOf="parent"
app:layout_constraintLeft_toLeftOf="parent"
app:layout_constraintRight_toRightOf="parent"
app:layout_constraintTop_toTopOf="parent" />
 
<Button
android:id="@+id/cancel_btn"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:text="Cancel"
app:layout_constraintBottom_toBottomOf="parent"
app:layout_constraintLeft_toLeftOf="parent"
app:layout_constraintRight_toRightOf="parent"/>
 
</androidx.constraintlayout.widget.ConstraintLayout>
```
• Set the layout for `MainActivity` inside the `onCreate()` hook function:
```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
```
• Start a new coroutine running in the background, incrementing the counter every second and displaying it in the `TextView` obtained from the `XML` layout:
```kotlin
class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val job = launch {
            var counter = 1
            while (true) {
                delay(1000)
                counter++
                withContext(UI) {
                    text_field.text = counter.toString()
                }
            }
        }
    }
}
```
• Allow coroutine-cancellation by clicking the cancel button:
```kotlin
class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text_field.text = "Bonjour!"

        val job = launch {
            var counter = 1
            while (true) {
                delay(1000)
                counter++
                withContext(UI) {
                    text_field.text = counter.toString()
                }
            }
        }
        cancel_btn.setOnClickListener {
            job.cancel()
        }
    }
}
```
_How it works..._

The coroutine started in the `MainActivity.onCreate()` function is running an infinite while loop. 
Each iteration starts with a one-minute delay and incrementation of the counter variable.
Next, we are applying the `withContext()` function in order to update `TextView` with the new value.
The `withContext()` function allows us to switch to a new coroutine dispatcher, obtained from the context argument, in order to execute a block of code passed to it. 
It doesn't create and start a new coroutine, but it modifies the context of the parent coroutine immediately. 
The new dispatcher is applied only temporarily to execute a given block of code. 
Any further operations executed inside the coroutine scope after the `withContext()` function call will be run with the original coroutine context.
We are assigning a `Job` instance returned by the coroutine to the `job` variable.
Next, we are setting up the listener to the cancel button.
Whenever the cancel button is clicked, the `cancel()` function is invoked on the coroutine `Job` reference.
As the result, our `MainActivity` implementation is going to update the `TextView` value every second.
Once the cancel button is clicked, the update mechanism is stopped immediately.


**There's more...**

If you're developing an application using different **JVM** frameworks, in order to switch to the **UI** thread from the background, you can use the `withContext()` function with the **JavaFx** or **Swing** constants instead of the **Android UI** context constants.

**See also**

• If you'd like to explore the basics of the coroutines framework, you should take a look at the recipes in [Chapter 7], [Making Asynchronous Programming Great Again]

###Easy class serialization on Android using the `@Parcelize` annotation

In this recipe, we are going to make use of the `@Parcelize` annotation to simplify the implementation of the Android Parcelable interface, allowing us to serialize objects efficiently. 
`@Parcelize` is available in the Kotlin Android Extensions plugin and provides automatic code-generation for Kotlin classes that implement the Parcelable interface.

**Getting ready**

We are going to implement the Android instrumented test case in order to verify the effect of a class serialization and deserialization in action. 
To make use of the Android **KTX** library, we need to add it to the project dependencies. 
In our case, we will need it in the android-test module. 
We can add it with the following declaration:
```groovy
androidTestImplementation 'androidx.core:core-ktx:1.0.+'
```
In order to make use of the Kotlin Android Extensions plugin, we need to enable it in the Android project **module-level** `build.gradle` script by adding the following declaration:
```groovy
apply plugin: 'kotlin-android-extensions'
```
You can examine the implementation and configuration of recipes related to the Android framework in the **AndroidSamples** project available in the book's **GitHub** repository: https://github.com/PacktPublishing/Kotlin-Standard-Library-Cookbook/.
To follow Android-related recipes, you just need to create a new project in Android Studio.

_How to do it..._

• Let's start by creating a sample User class that implements the Parcelable interface using the `@Parcelize` annotation:
```kotlin
@Parcelize
data class User(val name: String, val address: Address): Parcelable
 
@Parcelize
data class Address(val street: String, 
val number: String, 
val city: String): Parcelable
```
• Verify serialization and deserialization of the User class instance by writing and reading it from the `Bundle` instance:
```kotlin
@Test
fun testUserParcelisation() {
// given
    val originalUser = User("Bob", Address("Rue de Paris", "123", "Warsaw"))
    val bundle = Bundle()

// when
    bundle.putParcelable("my_user", originalUser)

// then
    val deserialisedUser = bundle.get("my_user") as User
    assertEquals(originalUser, deserialisedUser)
}
```

_How it works..._

We have started by defining the `User` class, which contains a property of the Address class.
Both `User` and `Address` are decorated with the `@Parcelize` annotation.
It tells the Android Extensions plugin to generate code for the Parcelable interface implementation.
Inside the `testUserParcelisation()` function, we are creating an instance of the `User` class and serializing it using the Android `Bundle` mechanism. 
We are putting the original `User` class instance to the Bundle under the "my_user" key, and later we deserialize its instance by calling `bundle.get("my_user") as User`.

**Finally**, we compare the original and deserialized User instances using the `assertEquals()` function.

`@Parcelize` handles generating the Parcelable implementation for the following types:
 
 All the primitive types, `String`, `CharSequence`, objects and enums, `Exception`, `Size`, `SizeF`, `Bundle`, `IBinder`, `IInterface`, `FileDescriptor`, `SparseArray`, `SparseIntArray`, `SparseLongArray`, and `SparseBooleanArray`.
 It also supports any Serializable types (for example, `java.util.Date`), as well as `Collection` and `Array` types. 
 It also works with nullable types.

**See also**

• You can learn more about the features dedicated to Android development in the Kotlin Android Extensions plugin by studying the official guide: https://kotlinlang.org/docs/tutorials/android-plugin.html

###Implementing a custom property delegate that provides lifecycle-aware values

Often, we need to declare a class property, which should depend on the lifecycle state of `Activity` or `Fragment`.
In this recipe, we are going to employ both the Kotlin `Lazy` delegate and the `Lifecycle` class provided by the Android Architecture Components library (https://developer.android.com/topic/libraries/architecture/).
We are going to implement a custom property delegate that will provide values in a lazy manner. 
This means that they are going to be instantiated only on the first call.
Moreover, we are going to clear their values once `Activity` or `Fragment` gets destroyed. 
This will avoid memory leaks, which can be caused by managing properties dependent on the Context instance with the standard `Lazy` delegate.

**Getting ready**

The basic `Lazy` delegate initialized using the `lazy()` function provided by the standard library gives the desired possibility of declaring a property of a non-null type, which can only be instantiated after a certain lifecycle event. 
For example, we reference an element of a screen layout in a property only after the layout was set up inside the `Activity.onCreate()` hook function.

However, such an implementation using `Lazy` will cause a memory leak if the property holds a reference to the `Activity` instance internally, as it will not allow it to be deleted by a garbage collector. 
The reason for this is that the lazy delegate is caching the instance it's holding.
We are going to implement our own property delegate, called LifeCycleAwareLazy, which will both extend the `Lazy` interface and clear the value it's holding when the activity is about to be destroyed.
We are going to use the `Lifecycle` library module available with the Android **Architecture** Components provided by **Google**.
We need to add it to the project dependencies in the **module-level** `build.gradle` script:
```groovy
implementation "android.arch.lifecycle:runtime:1.1.1"
```
_How to do it..._

• Declare the `LifecycleAwareLazy` class:
```kotlin
class LifecycleAwareLazy<T>(lifecycle: Lifecycle, val initializer: () ->T): Lazy<T>, GenericLifecycleObserver
```

• Register an observer to the given `Lifecycle` instance inside the init block:
```kotlin
class LifecycleAwareLazy<T>(lifecycle: Lifecycle, val initializer: () ->T): Lazy<T>, GenericLifecycleObserver {
 init {
  lifecycle.addObserver(this)
 }
}
```
• Implement an internal field that represents the current value stored by the delegate:
```kotlin
class LifecycleAwareLazy<T>(lifecycle: Lifecycle, val initializer: () ->T): Lazy<T>, GenericLifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }

    private object UNINITIALIZED_VALUE

    private var _value: Any? = UNINITIALIZED_VALUE
}
```




• Implement the value property, and the `isInitialized()` function required by the `Lazy` interface:
```kotlin
class LifecycleAwareLazy<T>(lifecycle: Lifecycle, val initializer: () ->T): Lazy<T>, GenericLifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }

    private object UNINITIALIZED_VALUE

    private var _value: Any? = UNINITIALIZED_VALUE

    @get:Synchronized
    override val value: T
        get() {
            if (_value === UNINITIALIZED_VALUE) {
                _value = initializer.invoke()
            }
            return _value as T
        }

    override fun isInitialized(): Boolean = _value != UNINITIALIZED_VALUE
}
```
 

• Implement the `GenericLifecycleObserver` interface:
```kotlin
class LifecycleAwareLazy<T>(lifecycle: Lifecycle, val initializer: () ->T): Lazy<T>, GenericLifecycleObserver {

    init {
        lifecycle.addObserver(this)
    }

    private object UNINITIALIZED_VALUE

    private var _value: Any? = UNINITIALIZED_VALUE

    @get:Synchronized
    override val value: T
        get() {
            if (_value === UNINITIALIZED_VALUE) {
                _value = initializer.invoke()
            }
            return _value as T
        }

    override fun isInitialized(): Boolean = _value != UNINITIALIZED_VALUE

    override fun onStateChanged(source: LifecycleOwner?, event: Lifecycle.Event?) {
        when (event) {
            Lifecycle.Event.ON_STOP -> {
                _value = UNINITIALIZED_VALUE
            }
            else -> return
        }
    }
}
```

_How it works..._

The `LifecycleAwareLazy` class we have implemented can be seen as an extended version of the standard `Lazy` delegate implementation. 
It observes events emitted by the `Lifecycle` instance passed to it in the constructor and handles the value accordingly. 
Internally, it contains the private `_value: Any?` mutable property set initially to the `UNINITIALIZED_VALUE` object, which represents an empty state. 
The _value property reflects the current state of the delegated property, which can be initialized or uninitialized. 
The `LifecycleAwareLazy` class exposes also the immutable value property, which is responsible for returning a final value of the delegated property.
Not it is marked with the `@get:Synchronized` annotation which informs the compiler to generate thread-safe getter function for this property.
Inside the value property getter, the current value of the `_value` property is checked.
Whenever it is equal to `UNINITIALIZED_VALUE`, first it gets reassigned to the result of the initialiser function passed in the constructor and then it is returned as the value of the `delegated` property.
`Lifecycle` is a class that holds the information about the current lifecycle state of an associated component (such as an activity or a fragment). 
It allows other objects to observe this state by subscribing to the state-change events by passing a callback to the `Lifecycle.addObserver()` function.
You can also obtain a current state by accessing the Lifecycle.currentState property.
Inside the init block, we are subscribing to the state updates of the Lifecycle object passed as the LifecycleAwareLazy constructor parameter.
We are passing the `LifecycleAwareLazy` instance using the `GenericLifecycleObserver` implementation to the lifecycle.addObserver(this) function.

We implement the `GenericLifeObserver` interface by overriding the `onStateChanged()` function inside the `LifecycleAwareLazy` class .
As you can see, we are updating the `_value` mutable property to the `UNINITIALIZED_VALUE` object whenever the `Lifecycle.Event.ON_STOP` event is emitted, meaning that the activity is about to be destroyed. 
This way, we can be sure that the `_value` property won't block the activity or fragment from being garbage-collected, even if it holds a reference to an activity `Context` instance directly or indirectly.
This is a huge win compared to the standard `lazy` delegate, which can lead to potential memory leaks.

**See also**

• If you'd like to get familiar with the basics of the `property-delegation` pattern, take a look at the Implementing delegated class properties recipe from [Chapter 5], [Tasteful Design Patterns Adopting Kotlin Concepts]

Easy operations on SharedPreferences
In this recipe, we will make use of the Android KTX library developed by Google, providing a set of useful extensions and utilities dedicated to Android app-development.
We are going to apply extension functions that allow us to operate on the SharedPreferences class in a clean and robust way.

**Getting ready**

In order to make use of the Android **KTX** library, we need to add it to the project dependencies.
In our case, we will need it in the android-test module. 
We can add it with the following declaration:
```groovy
androidTestImplementation 'androidx.core:core-ktx:1.0.+'
```
We are going to implement the Android instrumented test case in order to verify the effects of the operations we'll perform on SharedPreferences. 
You can examine the implementation and configuration of recipes related to the Android framework in the `AndroidSamples` project available in the **GitHub** repository: https://github.com/PacktPublishing/Kotlin-Standard-Library-Cookbook/.
To follow Android-related recipes, you just need to create a new project in Android Studio.

_How to do it..._

• Create a function that returns the `SharedPreferences` instance:
```kotlin
fun getDefaultSharedPreferences() = PreferenceManager.getDefaultSharedPreferences(InstrumentationRegistry.getContext())
```
• Save a sample string to the `SharedPreferences` instance:
```kotlin
@Test
fun testUserParcelization() {
 val prefs = getDefaultSharedPreferences()
 val userName: String = "Gonzo"
 prefs.edit {
  putString("user_name", userName)
 }
}
```
• Verify whether the string was successfully saved:
```kotlin
@Test
fun testSharedPrefs() {
 val prefs = getDefaultSharedPreferences()
 val userName: String = "Gonzo"
 prefs.edit {
  putString("user_name", userName)
 }

 val DEFAULT_VALUE = "empty"
 val fetchedUserName = prefs.getString( "user_name", DEFAULT_VALUE )
 assertSame(userName, fetchedUserName)
}
```

_How it works..._

We are using the `edit()` extension function provided by the `KTX` library for the SharedPreferences class.
It takes the lambda block, including the operations we want to perform on the `SharedPreferences.Editor` instance, and automatically invokes the `SharedPreferences.Editor.apply()` function to submit the transaction.
The lambda block passed to the `edit()` function implements the type, `SharedPreferences.Editor.() -> Unit`, which allows us to access an instance of Editor through the implicit this modifier.

If you'd like to submit operations applied to the Editor using the blocking `commit()` instead of the asynchronous `apply()` function, you should pass an additional `commit = true` parameter to the `edit()` function.

**See also**

• If you'd like to get familiar with more features offered by the Android **KTX** library, take a look at the library's official guide: https://developer.android.com/kotlin/ktx

###Less boilerplate Cursor data parsing

In this recipe, we are going to learn how to work with the Android `Cursor` type in a more effective and easy way.
We are going to create an extension function for the `Cursor` type, allowing us to query it in a clean way. 
We will also implement a practical example showing how to access the system-content resolver in order to fetch contacts stored on the device and transform Cursor into a list of strings representing the contacts' names.

**Getting ready**

You can examine the implementation and configuration of the recipes related to the Android framework in the AndroidSamples project available in the **GitHub** repository: https://github.com/PacktPublishing/Kotlin-Standard-Library-Cookbook/. 
To follow Android-related recipes, you just need to create a new project in Android Studio.

_How to do it..._

• Implement an extension function that allows us to fetch the values of a requested column name from Cursor:
```kotlin
fun Cursor.getString(columnName: String): String? {
 return getString(getColumnIndex(columnName))
}
```
• Obtain the `Cursor` instance that points to the system contacts table:
```kotlin
val NOT_SPECIFIED = ""
val content = getContext().contentResolver
val projection = arrayOf(ContactsContract.Data.DISPLAY_NAME)
val cursor = content.query(ContactsContract.Contacts.CONTENT_URI,projection,NOT_SPECIFIED,emptyArray(),NOT_SPECIFIED)
```
• Invoke the use function on the cursor instance and iterate through the data inside its scope:
```kotlin
val NOT_SPECIFIED = ""
val content = getContext().contentResolver
val projection = arrayOf(ContactsContract.Data.DISPLAY_NAME)
val cursor = content.query(ContactsContract.Contacts.CONTENT_URI,projection,NOT_SPECIFIED,emptyArray(),NOT_SPECIFIED)
 
val contacts = cursor.use {
 val contactsList = mutableListOf<String?>()
 while (it.moveToNext()) {
  val contactName = it.getString(ContactsContract.Data.DISPLAY_NAME)
  contactsList.add(contactName)
 }
 contactsList
}
```
_How it works..._

We are applying the `use()` extension function provided by the standard library to execute a set of operations on the `Cursor` instance.
`use()` can be invoked on any class that implements the `Closeable` interface.
Internally, after executing the lambda block passed to it as an argument, `use()` automatically invokes the `close()` function on the object it was called on.
Thanks to that, we can safely perform any operation on the Cursor instance and be sure that, even if some of them fail or result in throwing an exception, the cursor will eventually be closed. 

Inside the `use()` function's scope, we are iterating the cursor with the while loop by moving it to the next row in each iteration. 
For each of the rows, we are using the `getString()` extension function to obtain the current contact display name from the cursor. 
It allows us to avoid code duplication by combining `Cursor.getString()` and `Cursor.getColumnIndex()` together.

###Mocking dependencies with the Mockito Kotlin library

Often when writing unit test cases for complex classes, we face the problem of instantiating a great number of properties that the class we want to test depends on. 
Although this problem could be solved with dependency injection, it is faster, more efficient, and more desirable to mock a behavior of a specific object without instantiating it at all. 
In this recipe, we are going to explore how to use the **Mockito** Kotlin library to mock dependencies when writing a unit test for a simple registration form that contains an internal dependency whose behavior we are going to mock. 

**Getting ready**

We are going to use the **JUnit** library, which provides the core framework for running `test-case` classes. 
We need to add it our project's list of project dependencies by declaring it in the `gradle.build` script:
```groovy
implementation group: 'junit', name: 'junit', version: '4.12'
```
In order to make use of the Kotlin **Mockito** library, we can add it to the project dependencies with the following declaration:
```groovy
implementation 'com.nhaarman:mockito-kotlin:1.5.0'
```
You can examine the implementation and configuration of the recipes related to the Android framework in the `AndroidSamples` project available in the **GitHub** repository: https://github.com/PacktPublishing/Kotlin-Standard-Library-Cookbook/. 
To follow Android-related recipes, you just need to create a new project in Android Studio.

In this recipe, we are going to write a unit test for the RegistrationFormController class, declared as follows:
```kotlin
class RegistrationFormController(val api: RegistrationApi) {
 var currentEmailAddress: String = ""

 fun isEmailValid(): Boolean = currentEmailAddress.contains("@")

 fun checkIfEmailCanBeRegistered(): Boolean = isEmailIsValid() && api.isEmailAddressAvailable(currentEmailAddress)
}
RegistrationApi is defined as the following interface:
interface RegistrationApi {
 fun isEmailAddressAvailable(email: String): Boolean
}
```
Since we don't want to implement the `RegistrationApi` interface in order to instantiate the `RegistrationFormController` class, we are going to mock it instead using the Mockito Kotlin `mock()` function.

_How to do it..._

•  Create a new test class:
```kotlin
class MyTest {
}
```
• Create a mocked instance of the `RegistrationApi` interface as the test-class property:
```kotlin
class MyTest {
 private val api = mock<RegistrationApi>()
}
```
• Add a class property of the `RegistrationFormController` type:
```kotlin
class MyTest {
 private val api = mock<RegistrationApi>()
 private var registrationFormController =
  RegistrationFormController(api = api)
}

```

• Create the test method to check whether `checkIfEmailCanBeRegistered()` behaves correctly for an invalid email address occurrence: 
```kotlin

class MyTest {
 private val api = mock<RegistrationApi>()
 private lateinit var registrationFormController: RegistrationFormController

 @Before
 fun setup() {
  registrationFormController = RegistrationFormController(api = api)
 }

 @Test
 fun `email shouldn't be registered if it's not valid`() {
// given
  assertNotNull(registrationFormController)
  whenever(api.isEmailAddressAvailable(anyString())) doReturn (true)
// when
  registrationFormController.currentEmailAddress = "Hilary"
// then
  assertFalse(registrationFormController.checkIfEmailCanBeRegistered())
 }
}
```

_How it works..._

Inside the `email shouldn't be registered if it's not valid()` test method, we are setting up our mocked `RegistrationApi` property to return true any time its `isEmailAddressAvailable()` function is invoked, regardless of the string value passed to it.
Next, we are updating the `currentEmailAddress` property of the `RegistrationFormController` class with an invalid email address value. 
The test is going to pass because the `isEmailIsValid()` function works correctly and returns false for a given email address value.

As you can see, thanks to the mocking, we've avoided implementing the dependency of the class we were testing. It's a proper technique that allows us to test the specific parts of the business logic while mimicking the desired behavior of the dependencies.
**Mocking** can be also useful when we are not able to instantiate the dependencies because they are specific to a platform that is not compatible with the pure **JVM** (that is, Android).

**See also**

• You can look into the **Verifying function invocations** recipe in order to explore how to check whether any specific interactions with the mocked dependency were observed

###Verifying function invocations

Along with the possibility of simulating the particular behavior of dependencies in test methods, mocking allows us to verify whether specific functions of the mocked objects were invoked. 
In this recipe, we are going to write a unit tests for a simple registration-form controller. 
The registration form contains two internal dependencies that we are going to mock using the **Mockito** Kotlin library.
We are going to test whether the proper functions are being invoked in different scenarios.

**Getting ready**

We are going to use the **JUnit** library to provide a core framework for running `test-case` classes. 
We need to add it to our project's list of project dependencies by declaring it in the `gradle.build` script:
```groovy
implementation group: 'junit', name: 'junit', version: '4.12'
```
In order to make use of the Kotlin **Mockito** library, we can add it to the project dependencies with the following declaration:
```groovy
implementation 'com.nhaarman:mockito-kotlin:1.5.0'
```
You can examine the implementation and configuration of recipes related to the Android framework in the `AndroidSamples` project available in the **GitHub** repository: https://github.com/PacktPublishing/Kotlin-Standard-Library-Cookbook/. 
To follow Android-related recipes, you just need to create a new project in Android Studio.

In this recipe, we are going to write a unit test for the `RegistrationFormController` class, declared as follows:
```kotlin
class RegistrationForm(val api: RegistrationApi, val view: TextView) {
    var currentEmailAddress: String by Delegates.observable("", ::onEmailAddressNewValue)
 
fun onEmailAddressNewValue(prop: KProperty<*>, old: String, new: String) {
        if (checkIfEmailCanBeRegistered()) { 
            view.showSuccessMessage("Email address is available!")
         } else {
             view.showErrorMessage("This email address is not available.")
         }
}
 fun checkIfEmailCanBeRegistered(): Boolean = isEmailIsValid() &&api.isEmailAddressAvailable(currentEmailAddress)
 
 fun isEmailIsValid(): Boolean = currentEmailAddress.contains("@")
}
```
It contains the `RegistrationApi` property which is defined as the following interface:
```kotlin
interface RegistrationApi {
fun isEmailAddressAvailable(email: String): Boolean
} 
```
and the `TextView` type property defined as follows:
```kotlin
interface TextView {
fun showSuccessMessage(message: String)

fun showErrorMessage(message: String)
}
```
Since we don't want to implement the `RegistrationApi` and `TextView` interface in order to instantiate the `RegistrationFormController` class in our test, we are going to mock them using the **Mockito** Kotlin `mock()` function.

_How to do it..._

• Create a new test class:
```kotlin
class MyTest {
}
```
• Create a mocked instance of the `RegistrationApi` interface as the `test-class` property:
```kotlin
class MyTest {
 private val api = mock<RegistrationApi>()
}
```
• Create a mocked `TextView` instance:
```kotlin
class MyTest {
 private val api = mock<RegistrationApi>()
 private val view = mock<TextView>()
}
```
• Create the `RegistrationFormController` object as the `MyTest` class property:
```kotlin
class MyTest {
 private val api = mock<RegistrationApi>()
 private val view = mock<TextView>()
 private var registrationForm = RegistrationForm(api, view)
}
```
• Add a test method to verify whether the success message is shown if the address is available:
```kotlin
class MyTest {
private val api = mock<RegistrationApi>()
private val view = mock<TextView>()
private var registrationForm = RegistrationForm(api, view)
 
@Test
fun `should display success message when email address is available`() {
 // given
 assertNotNull(registrationForm)
 
 // when we update the currentEmailAddress to any String
 whenever(api.isEmailAddressAvailable(ArgumentMatchers.anyString())) doReturn(true)
 registrationForm.currentEmailAddress = "hilary@gmail.com"
 
 // then
 assertTrue(registrationForm.checkIfEmailCanBeRegistered())
 verify(view).showSuccessMessage("Email address is available!")
  }
}
```
• Add a test method to verify whether the error message is shown if the address is not available:
```kotlin

class MyTest {
 private val api = mock<RegistrationApi>()
 private val view = mock<TextView>()
 private var registrationForm = RegistrationForm(api, view)

 @Test
 fun `should display success message when email address is available`() {

  // given
  assertNotNull(registrationForm)
  // when we update the currentEmailAddress to any String
  whenever(api.isEmailAddressAvailable(ArgumentMatchers.anyString())) doReturn (true)
  registrationForm.currentEmailAddress = "hilary@gmail.com"
  // then
  assertTrue(registrationForm.checkIfEmailCanBeRegistered())
  verify(view).showSuccessMessage("Email address is available!")
 }

 @Test
 fun `should display error message when email address isn't available`() {
  // given
  assertNotNull(registrationForm)
  // when
  registrationForm.currentEmailAddress = "hilary@gmail.com"
  whenever(api.isEmailAddressAvailable(ArgumentMatchers.anyString())) doReturn (false)
  // then
  assertTrue(registrationForm.isEmailIsValid())
  verify(view).showErrorMessage(anyString())
  }
}
```





_How it works..._

Apart from behavior-mocking, **Mockito** Kotlin provides a reliable way of verifying interactions with mocked dependencies that occurred while executing the test method. 
In both the `should display success message when email address is available()` and `should display error message when email address isn't available()` functions, we just want to check whether the desired function of the `TextView` dependency was invoked. 
In order to do this, we are invoking the verify() function. 
For example, in order to check whether the `showErrorMessage()` function has been called on the mocked `view: TextView` dependency, we call the following code:
```kotlin
verify(view).showErrorMessage(anyString())
```
If the `showErrorMessage()` is not invoked, the test method will fail, and the proper log message will be printed to the console.

###Unit tests for Kotlin coroutines

In this recipe, we are going to explore how to effectively test code that uses coroutines internally.
We are going to write a unit test for the part of a code that runs asynchronously in the background while trying to authorize the given user credentials using an external **API**.
We are going to employ the Kotlin **Mockito** library to mock the calls to the external **API** and the `TextCoroutineContext` class, allowing us to test asynchronous code with ease.

**Getting ready**

We are going to use the **JUnit** library to provide the core framework for running `test-case` classes.
We need to add it to our project's list of project dependencies by declaring it in the gradle.build script:
```groovy
implementation group: 'junit', name: 'junit', version: '4.12'
```
In order to make use of the Kotlin **Mockito** library, we can add it to the project dependencies with the following declaration:
```groovy
implementation 'com.nhaarman:mockito-kotlin:1.5.0'
```

You can examine the implementation and configuration of recipes related to the Android framework in the `AndroidSamples` project available in the **GitHub** repository: https://github.com/PacktPublishing/Kotlin-Standard-Library-Cookbook/. 
To follow Android-related recipes, you just need to create a new project in Android Studio.
In this recipe, we are going to write a unit test for the Authenticator class, defined as follows:
```kotlin

class Authenticator(val api: Api) {

 fun tryToAuthorise(encodedUserNameAndPassword: ByteArray, context: CoroutineContext): Deferred<String>= async(context)
 {
  var authToken = api.authorise(encodedUserNameAndPassword)

  var retryCount = 0
  while (authToken.isEmpty() && retryCount <= 8) {
   delay(10, TimeUnit.SECONDS)
   authToken = api.authorise(encodedUserNameAndPassword)
   retryCount++
  }
  authToken
 }
}
```
The **Api** property is given as the following interface:
```kotlin
interface Api {
 // returns a non-empty auth token when the given credentials were authorised
 fun authorise(encodedUserNameAndPassword: ByteArray): String
}
```

_How to do it..._

• Create a new `test` class:
```kotlin
class MyTest {
}
```

• Add a mocked Api type `test-class` property:
```kotlin
class MyTest {
val api: Api = mock()
}
```
• Instantiate the `Authenticator` class as the class property:
```kotlin
class MyTest {

val api: Api = mock()
val authenticator = Authenticator(api)
}
```
• Implement the test to verify whether the `Api.authorise()` function is called at least `10` times in case of consecutive failed authorization attempts:
```kotlin
class MyTest {
 val api: Api = mock()
 val authenticator = Authenticator(api)

 @Test
 fun `should retry auth at least 10 times when Api returns empty token`() {
  whenever(api.authorise(any())) doReturn ""

  val context = TestCoroutineContext()

  runBlocking(context) {
   authenticator.tryToAuthorise("admin:1234".toByteArray(),context).await()
   context.advanceTimeBy(100, TimeUnit.SECONDS)
   verify(api, atLeast(10)).authorise(any())
  }
 }
}
```

_How it works..._

**First**, with mocking, we've avoided implementing the **Api** dependency of the `Authenticator` class we were writing the test for.
In fact, we are not interested in testing real results returned by the **Api** implementation. 
We want to test the mechanism of the `tryToAuthorise()` function and verify whether it's going to retry calling the `Api.authorise()` function at least `10` times in case of constant authorization failures. 
This is why we have set up the api mock to always return an empty string for the `authorise()` function result.
As you can imagine, such a test would take a lot of time to complete because, internally, the `tryToAuthorise()` function waits for `10` seconds before retrying the authorization. 
In order to avoid the too-long execution time, we need to artificially move forward in time by `100` seconds and check whether the `Api.authorise()` function was invoked at least `10` times.
We are able to do this by scheduling the two coroutines started with the `runBlocking()` function, and internally inside the `tryToAuthorise()` function, to run on the same instance of `TestCoroutineContext`. 
Then, to move forward in time by `100` seconds, we just call the `advanceTimeBy(100, TimeUnit.SECONDS)` function on the `TestCoroutineContext` instance.
As a result, our test method is going to complete in less than a second.
