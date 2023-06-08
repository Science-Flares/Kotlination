##Miscellaneous

In this chapter, we will cover the following recipes:

• Kotlin and Java interoperability issues

• Kotlin and JavaScript interoperability issues

• Renaming of generated classes

• Decompiling Kotlin code to JVM bytecode

• Adding custom names for imports

• Wrapping complex type declarations with type aliases

• Expressive try…catch declarations

• Safe type-casting


###Introduction

This chapter is going to focus on presenting handy solutions to various problems and issues that Kotlin developers deal with on a daily basis. 
Here, you will find useful tips and solutions for issues related to interoperability with Java and JavaScript and neat tricks that will help you write code more effectively.

###Kotlin and Java interoperability

This recipe is going to show how you to combine both **Java** and **Kotlin** classes together and use them in the same application component. 
We will declare a **Kotlin** data class, called ColoredText, that holds two properties of the `String` and `atlasCompose.ui.Color` types.
Apart from the properties, it is also going to expose a utility function inside a companion object responsible for text-processing. 
We are going to learn how to make use of those properties and how to declare the function from the `ColoredText` class to be visible as a **JVM** static method inside the **Java** class.


_How to do it..._

• Declare the `ColoredText` class:
```kotlin
data class ColoredText
@JvmOverloads
constructor(
var text: String = "",
var color: atlasCompose.ui.Color = defaultColor) {

 companion object {
  @JvmField
  val defaultColor = atlasCompose.ui.Color.BLUE
 }
}
```
• Implement a static **JVM** method inside the companion object:
```kotlin
data class ColoredText
@JvmOverloads
constructor(var text: String = "",var color: atlasCompose.ui.Color = defaultColor) {
 companion object {
  @JvmField
  val defaultColor = atlasCompose.ui.Color.BLUE

  @JvmStatic
  fun processText(text: String): String =
   with(text) {
    toLowerCase().trim().capitalize()
   }
 }
}
```
• Add a member function that enables you to print the text property to the console:
```kotlin

data class ColoredText
@JvmOverloads
constructor(var text: String = "",var color: atlasCompose.ui.Color = defaultColor) {
 fun printToConsole() = println(text)

 companion object {
  @JvmField
  val defaultColor = atlasCompose.ui.Color.BLUE

  @JvmStatic
  fun processText(text: String): String =
   with(text) {
    toLowerCase().trim().capitalize()
   }
 }
}
```
• Implement a Java class that makes use of Kotlin class functions and properties:
```java
public class JavaApp {
 public static void main(String... args) {
  String rawText = " one Of The Best Programming Skills You Can Have " +
                     "Is Knowing When To Walk Away For Awhile. ";
  String text = ColoredText . processText (rawText);
  ColoredText myText = new ColoredText( text, ColoredText.defaultColor );
  myText.printToConsole();
 }
}
```

_How it works..._

As the result, the main function from the JavaApp Java class is going to print the following wisdom to the console:
```text
One of the best programming skills you can have is knowing when to walk away for awhile.
```
Kotlin and Java interoperability is absolutely painless, thanks to the fact that both Kotlin and Java classes are compiled to the same **JVM** bytecode included in a common codebase.
However, there are a few special cases that require additional attention when we want to make Kotlin declarations available on the Java side in a specific way.

**First**, in the `ColoredText` class, we are marking the constructor with the `@JvmOverloads` annotation, which tells the compiler to generate multiple instances of the constructor in case there are any default property values declared.
Thanks to this, we could instantiate the `ColoredText` class in some Java classes without passing the text and/or color property values.
Next annotation we are using is `@JvmField`, which tells the Kotlin compiler not to generate getter and setter functions for this property and expose it as a field. 
It provides a cleaner syntax for accessing the values on the Java side when we expose a constant value through Kotlin objects.
Another commonly used annotation is `@JvmStatic`.
Its aim is to tell the compiler that an additional static method needs to be generated for this function in order to make it available in Java as a direct static function of the outer class. 
For example, in our case, we are able to access the `processText()` function in Java in the following manner, by omitting the Companion element:
```java
ColoredText.processText("sample text")
```

###Kotlin and JavaScript interoperability

In the following recipe, we are going to configure and implement a sample web app project in order to explore how **Kotlin** can be compiled to **JavaScript**. 
We are going to implement a simple web app that will open an alert dialog when the app starts. 
The following example is going to present a way of combining **Kotlin** and **JavaScript** code together and configuring a **JavaScript** compilation with the Gradle build script.

**Getting ready**

In order to set up the project to compile **Kotlin** files into **JavaScript**, we need to add the following properties to the **module-level** Gradle build script.

**First**, we need to apply the `Kotlin2Js` plugin. 
We can do it with the following declaration:
```groovy
apply plugin: "kotlin2js"
```
At this point, whenever we execute the Gradle build task, the `Kotlin2JS` compiler is going to generate **JavaScript** code with the corresponding functions and classes from the Kotlin files and write them under the build/classes/kotlin/ directory to the JS file named after the project name.
However, we can modify this default behavior by specifying the output file parameter:
```kotlin
compileKotlin2Js.kotlinOptions.outputFile = "${projectDir}/web/js/app.js"
```

As the result, the output of the Kotlin files' compilation will be available under **web/js** directory, in the `app.js` file.
However, in order to execute the translated Kotlin code, we need to link the **Kotlin JS** standard library to it as well. 
We can modify the Gradle build script to include the required libraries in the web/js output directory:
```groovy
build.doLast {
 configurations.compile.each {
  File file ->
  copy {
   includeEmptyDirs = false

   from zipTree (file.absolutePath)
   into "${projectDir}/web/js/lib"
   include { fileTreeElement ->
    def path = fileTreeElement . path
            path.endsWith(".js") && (path.startsWith("META-INF/resources/") || !path.startsWith("META-INF/"))
   }
  }
 }
}
```
You can examine the configuration of the `Kotlin2JS` plugin in the sample project: https://github.com/PacktPublishing/Kotlin-Standard-Library-Cookbook/tree/master/Kotlin-Samples-JS.

_How to do it..._

• Create a new kotlin file, `AlertDialogApp.kt`, that contains the `main()` function:
```kotlin
fun main(args : Array<String>) {}
```










• Declare a reference to the JS alert() function:
```kotlin

fun main(args : Array<String>) {}
 
external fun alert(message: Any?): Unit
```
• Implement the showAlert() function and invoke it in the main() function:
```kotlin

fun main(args : Array<String>) {
showAlert()
}
 
fun showAlert() {
val number: dynamic = js("Math.floor(Math.random() * 1000)")
val message = "There were $numberviruses found on your computer! \uD83D\uDE31"
println("showing alert")
alert(message)
}
 
external fun alert(message: Any?): Unit
```

_How it works..._

As you can see, after running the Gradle build task, the `AlertDialogApp.kt` file is going to be translated into the `app.js` JavaScript code, available under the web/js directory, along with the `kotlin.js` file linked under the web/js/lib directory. 
We can test the JS-generated code by running it in the web browser. 
In order to do so, we will create a sample `HTML` file under the project's main directory, named `test_app.html`, which is going to link the `kotlin.js` standard library file and run the `app.js` file that contains the `main()` function implementation generated from the `AlertDialogApp.kt` file:
```html
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Test</title>
</head>
<body>
<script src="web/js/lib/kotlin.js"></script>
<script src="web/js/app.js"></script>
</body>
</html>
```
As the result, when we open the `test_app.html` file in a web browser, we are going to encounter the following pop-up dialog:



Inside the `showAlert()` function in the `AlertDialogApp.kt` file, we are using the JavaScript `Math.floor()` and `Math.random()` functions to generate a random integer value from `0-1,000`.
We are using the `js()` function available in the Kotlin standard library to inline JS code in the Kotlin code.
As you can see, the result returned by `js()` is declared as a dynamic type.
The dynamic modifier is used to declare the dynamic type, which is characteristic for every JavaScript object. 
In Kotlin code, it can be used as an alternative to strongly-typed declarations. 
It makes sense to use it whenever we are dealing with third-party JS libraries that can return the results of not-specified types.
**Next**, we are using the random integer value generated using the JS-inlined code to compose the message that is going to be displayed. 
**Finally**, we are calling the JS `alert()` function and passing it the composed message.
This time, we are using the external modifier, which tells the Kotlin compiler that the corresponding declaration is written in pure JavaScript and it should not generate the implementation for it.

**There's more...**

You may wonder whether there is a practical solution to working with npm JS dependencies within a Gradle-based project. 
There is a solution that allows you to integrate your Kotlin project with npm dependencies easily, called the Kotlin Frontend Gradle plugin.
You can learn more about it in the official project guide: https://github.com/Kotlin/kotlin-frontend-plugin.


Renaming generated functions
In this recipe, we are going to learn how to modify a Kotlin function's name when it is being compiled to the generated JVM bytecode. We need this feature because of the type-erasure that happens when generating the JVM bytecode. However, thanks to the @JvmName annotation, we can declare a number of different functions, but that has the same name and use their original name in the Kotlin code while keeping their JVM bytecode names distinct to satisfy the compiler.

_How to do it..._

• Declare two functions that have the same names:
```kotlin

fun List<String>.join(): String {
return joinToString()
}
 
fun List<Int>.join(): String =
map { it.toString() }
.joinToString() 
• Mark the functions with the proper annotations:
@JvmName("joinStringList")
fun List<String>.join(): String {
return joinToString()
}
 
@JvmName("joinIntList")
fun List<Int>.join(): String =
map { it.toString() }
.joinToString()
```

_How it works..._

Thanks to providing the alternative function names, we were able to compile them to **JVM** bytecode.
However, you can easily test that we can use their original names inside the Kotlin code. 
That's because the Kotlin compiler is able to recognize them correctly based on their return type and generic type argument value.

You can test this by running the `join()` function both on the list of integers and on the list of strings:
```kotlin
fun main(vararg args: String) {
 println(listOf(1, 2, 3).join())
 println(listOf("a", "b", "c").join())
}
```
As the result, the preceding code will print the following text to the console:
```text
1, 2, 3
a, b, c
```
Keep in mind that, when you want to invoke those functions from Java, you will need to use their alternative names: `joinStringList()` and `joinIntList()`.

There's more...
There is also a corresponding `@JsName` annotation, which allows you to change the name of JavaScript functions and classes.
You can use it if you are compiling your Kotlin files to JavaScript using the `Kotlin2JS` plugin.
If you'd like to get familiar with the basics of the `Kotlin2JS` plugin, you can examine the **Kotlin and JavaScript interoperability** recipe.

**See also**

• If you'd like to learn how to explore the final **JVM** bytecode generated from **Kotlin** files, read the Decompiling Kotlin code to Java and **JVM** bytecode recipe

###Decompiling Kotlin code to Java and JVM bytecode

In this recipe, we are going to learn how to easily decompile our Kotlin files to see how their corresponding **JVM** bytecode is implemented and what the bytecode's corresponding Java implementation would look like.
This can help you to discover how various Kotlin concepts were implemented under the hood. 
It can also be helpful for code-debugging and optimization.


**Getting ready**

Let's create a new Kotlin file, named `Recipe4.kt`, that contains the following sample implementation in order to see its bytecode translation:
```kotlin

data class A(val a: String = "a") {
 companion object {
  @JvmStatic
  fun foo(): String = "Wooo!"
 }
}
```

_How to do it..._

• Open the Recipe4.kt file in IntelliJ.

• Choose the Show Kotlin Bytecode option from the Tools/Kotlin menu. 
The box will present the JVM bytecode implementation.

• Click the Decompile button in the Kotlin Bytecode view. 
The corresponding Java implementation will be decompiled from the bytecode and will appear in the new window.

_How it works..._

The task of analyzing the Java implementation generated for data class A is left as an exercise for the reader. 
You can experiment by removing the data modifier from the Kotlin class definition and observing the changes in the bytecode and the decompiled Java implementation.

Adding custom names for imports
In this recipe, we are going to explore how to add custom names to the import declarations. 
We are going to import the `java.lang.StringBuilder` class, add a custom name to it and make use of it in the sample code to demonstrate it in action. 

_How to do it..._

• Import the StringBuilder class with a custom alias:
```kotlin
import java.lang.StringBuilder as builder
```
• Use the custom StringBuilder name in the sample code:
```kotlin

import java.lang.StringBuilder as builder
 
fun main(vararg args: String) {
 val text = builder()
  .append("Code is like humor. ")
  .append("When you have to explain it, ")
  .append("it’s bad.")
  .toString()
 println(text)
}
```

_How it works..._

As you can see, we were able to use an alternative name instead of the StringBuilder class. 
It's a small feature but sometimes can be used to make your code easier to read. 
Our sample code is going to print the following text to the console:
```text
Code is like humor. When you have to explain it, it’s bad.
```

###Wrapping complex type declarations with type aliases

Sometimes we need to deal with long or verbose type declarations. 
Thankfully, in Kotlin, we are able to assign an alternative name to any existing type and use the shorter alternative name instead. 
It can also help you to write more understandable and elegant code. 
This recipe is going to demonstrate how to use type aliases. 


**Getting ready**

Let's assume we have the following two classes predefined:
```kotlin

data class Song(val title: String)
data class Artist(val name: String)
```
We are going to define a type alias for the map of `Song` type values and a generic key `type–Map<T, List<Song>>`.
Next, we are going to use it to define a function that will return the most popular `Artist` instance for a given `Map<Artist, List<Song>>` object.

_How to do it..._

• Declare a generic type alias for the `Map<T, List<Song>>` type:
```kotlin
typealias GrouppedSongs<T>= Map<T, List<Song>>
```
• Implement the `getMostPopularArtist()` function using the type alias:
```kotlin
fun getMostPopularArtist(songs: GrouppedSongs<Artist>) =
songs.toList().sortedByDescending {it.second.size }.first().first
```

_How it works..._
Using the type alias, we were able to provide a custom name for the type and we could use it in `getMostPopularArtist(songs: GrouppedSongs<Artist>)` instead of `Map<Artist, List<Song>>` type, which resulted in a more meaningful declaration.
We can test our implementation by invoking `getMostPopularArtist()` with sample data:
```kotlin
val songs: GrouppedSongs<Artist> = mapOf(
 Artist("Bob Dylan") to listOf(Song("Blowing In The Wind"), 
  Song("To Fall in Love With You")),
 Artist("Louis Armstrong") to listOf(Song("What A Beautiful World"))
)
 
println("${getMostPopularArtist(songs)}is the most popular")
```

As a result, we are going to get the following text printed to the console:
```text
Artist(name=Bob Dylan) is most popular
```
###Expressive `try…catch` declarations

**Kotlin** is advertised as an extremely expressive language. 
However, it's one of the characteristics of the language that is not obvious in the beginning, especially if you are used to other languages such as **Java** or **JavaScript**. 
In order to present the language style more clearly, in this recipe, we are going to discover how to work with the `try…catch` declaration in a Kotlin way, by treating it as an expression.

**Getting ready**

Let's consider the following Java code:
```java
int value;
try{
    result=parseInt(input);
        } catch (NumberFormatException e){
    
        } finally{
        result=0;
        }
```
It declares an int result variable. 
**Next**, it tries to parse the string value to the integer with the `Integer.parseInt()` function, and if it succeeds, it assigns the result to the value variable. 
If the `parseInt()` fails to parse the string, a default value of `0` is assigned to the value variable.

_How to do it..._

• Invoke the `parseInt()` function in the `try…catch` declaration:
```kotlin
try {
 parseInt("fdsaa")
} catch (e: NumberFormatException) {
 0
}
```
• Assign the result of the `try…catch` declaration to the value variable:
```kotlin

val result = try {
 parseInt("fdsaa")
} catch (e: NumberFormatException) {
 0
}
```

_How it works..._

That's it. 
The `try…catch` declaration in Kotlin can be assigned to a variable. 
The reason is that it is, in fact, an expression! In our example, `try…catch` returns the result of the `parseInt()` function, and when the function throws an exception, it returns `0`. 

**There's more...**

Similarly, we can treat other language declarations as expressions.
It's a common practice to assign a variable to the value returned by control flow statements, such as if and when.
For example, we can use when as an expression in the following way:
```kotlin

val result = when(input) {
 is Int -> input
 is String -> parseInt(input)
 else -> 0
}
```

###Safe type-casting

Whenever we perform **type-casting**, we should keep in mind that it is a potential source of exceptions. 
That's the reason why we should always perform type-checking using the `is` modifier or do the casting inside the `try…catch` block.
However, in Kotlin, we have also a safe casting option that will not throw `ClassCastException` but will return `null` instead. 
In this recipe, we are going to test the safe casting in action.


_How to do it..._

• Let's start by defining a function that returns a `Number` type of random `Double` value:
```kotlin
fun getRandomNumber(): Number = Random().nextDouble() * 10
```
• Try to cast the results of the function to different types using the safe-cast operator and print the casted values to the console:
```kotlin
println(getRandomNumber() as? Int)
println(getRandomNumber() as? Double)
println(getRandomNumber() as? String)
```

_How it works..._

The preceding code will not fail nor throw any exceptions. 
It will just return the null value instead.
Our casting test code is going to print the following output to the console:
```text
null
8.802117014997226
null
```
Using the safe `as?` casting modifier is a neat alternative to the traditional way. 
You can use it if you are working with nullable types, that is while working with external libraries that don't provide the null safety. 
However, if you can benefit from the null safety, it's best to use the standard casting operation.

**Other Books You May Enjoy**

If you enjoyed this book, you may be interested in these other books by Packt:


Hands-on Design Patterns with Kotlin
 Alexey Soshin
ISBN: 9781788998017

• Get to grips with Kotlin principles, including its strengths and weaknesses

• Understand classical design patterns in Kotlin

• Explore functional programming using built-in features of Kotlin

• Solve real-world problems using reactive and concurrent design patterns

• Use threads and coroutines to simplify concurrent code flow

• Understand antipatterns to write clean Kotlin code, avoiding common pitfalls

• Learn about the design considerations necessary while choosing between architectures




Kotlin Programming By Example
 Iyanu Adelekan
ISBN: 9781788474542

• Learn the building blocks of the Kotlin programming language

• Develop powerful RESTful microservices for Android applications

• Create reactive Android applications efficiently

• Implement an MVC architecture pattern and dependency management using Kotlin

• Centralize, transform, and stash data with Logstash

• Secure applications using Spring Security

• Deploy Kotlin microservices to AWS and Android applications to the Play Store


Leave a review - let other readers know what you think
Please share your thoughts on this book with others by leaving a review on the site that you bought it from.
If you purchased the book from Amazon, please leave us an honest review on this book's Amazon page. This is vital so that other potential readers can see and use your unbiased opinion to make purchasing decisions, we can understand what our customers think about our products, and our authors can see your feedback on the title that they have worked with Packt to create. 
It will only take a few minutes of your time, but is valuable to other potential customers, our authors, and Packt. 

**Thank you!**
