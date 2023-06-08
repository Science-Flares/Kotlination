##Powerful Data Processing

In this chapter, we will cover the following recipes:

• Composing and consuming collections the easy way

• Filtering datasets

• Automatic null removal

• Sorting data with custom comparators

• Building strings based on dataset elements

• Dividing data into subsets

• Transforming data with map and flatMap

• Folding and reducing datasets

• Grouping data


##Introduction

This chapter focuses on exploring standard library support for declarative-style operations on collections.
The following recipes present solutions to different types of programming problem related to dataset transformations, reductions, and grouping.
We will learn how to approach data processing operations with a functional programming style, together with the powerful data-processing extensions built into the standard library.



###Composing and consuming collections the easy way

The Kotlin standard library provides a number of handy extensions that make collections creation and merging easy and safe. We are going to learn them step by step.
Let's assume we have the following Message class defined:
```kotlin
data class Message(val text: String, 
                     val sender: String,
                       val timestamp: Instant = Instant.now() 
                         )
```
In this recipe, we are going to create two sample collections containing `Message` instances and merge them into one list of `Message` objects.
Next, we are going to iterate through the list of messages and print their text to the console. 

**Getting ready**

Kotlin standard library provides two basic interfaces which represent collection data structure—Collection and `MutableCollection`, both extending the `Iterable` interface.
The first one defines an immutable collection that only supports read access to its elements. 
The second interface allows us to both add and remove elements.
There are also more specialized interfaces that extend the `Collection` and `MutableCollection` base types, such as `List`, `MutableList`, `Set`, and `MutableSet`.
There are many functions available for creating collections of different types.
The most commonly used ones are the `<T> listOf(vararg elements: T)` function, which instantiates a List instance, and <T> mutableListOf(vararg elements: T), which returns an instance of MutableList for the elements given as a function's arguments. 

_How to do it..._

• Let's declare two lists containing sample data:
```kotlin
val sentMessages = listOf (
Message("Hi Agat, any plans for the evening?", "Samuel"),Message("Great, I'll take some wine too", "Samuel")
                             )
val inboxMessages = mutableListOf(
   Message("Let's go out of town and watch the stars tonight!","Agat"),Message("Excelent!", "Agat")
                                    )
```
• Merge sentMessages and inboxMessages into one collection:
```kotlin
val allMessages: List<Message>= sentMessages + inboxMessages
```

• Print out the text of the Message objects stored in the allMessages list to the console:
```kotlin
val allMessages: List<Message>= sentMessages + inboxMessages
allMessages.forEach { (text, _) ->
    println(text)
}
```

_How it works..._

As the result, our code is going to print the following text to the console:
```text
Hi Agat, any plans for the evening?
Great, I'll take some wine too
Let's go out of town and watch the stars tonight!
Excelent!
```

In order to add elements of one collection to another, we are using the `+` operator.
The standard library overloads this operator with the code responsible for merging elements of two `Collection` type instances collections into one instance.
The `sentMessages` and `inboxMessages` variables are declared as List instances. 
The `FP.plus` function returns a new `Collection` instance, containing elements of the `sentMessages` and inboxMessages lists. 
Finally, we use the `forEach()` function to iterate through the next elements of the list.
In the lambda block passed to the forEach function, we are print the text property of the current Message to the console. 
We are destructuring the lambda's argument of the `Message` type and accessing its text property directly inside the `println()` function. 

**There's more...**

The standard library also overloads a `-` operator for the Collection type.
We could use it to subtract some elements from the collection. 
For example, we could use it like this:
```kotlin
val receivedMessages = allMessages - sentMessages
receivedMessages.forEach { (text, _) ->
    println(text)
}
```
And we would get the following output:
```text
Let's go out of town and watch the stars tonight!
Excelent!
```

We could also use the standard for loop to implement the iteration:
```kotlin
for (msg in allMessages) {
    println(msg.text)
}
```

**See also**

• You can learn more about destructuring declarations in the Destructuring types recipe in [Chapter 2], Expressive Functions and Adjustable Interfaces

• If you'd like to master lambda expressions, you can take a look at the Working effectively with lambdas and closures recipe from [Chapter 3], Shaping Code with Kotlin Functional Programming Features

###Filtering datasets

Filtering is one of the most common programming challenges in the data processing field.
In this recipe, we are going to explore the standard library's built-in extension functions that provide an easy way to filter the Iterable data types.
Let's assume we have the following Message class declaration:
```kotlin
data class Message(val text: String,
val sender: String,
val receiver: String,
val folder: Folder = Folder.INBOX,
val timestamp: Instant = Instant.now())
 
enum class Folder { INBOX, SENT }
```
The getMessages() function returns the following data:
```kotlin
fun getMessages() = mutableListOf(
Message("Je t'aime", "Agat","Sam", Folder.INBOX),
Message("Hey, Let's go climbing tomorrow", "Stefan", "Sam", Folder.INBOX),
Message("<3", "Sam", "Agat", Folder.SENT),
Message("Yeah!", "Sam", "Stefan", Folder.SENT)
)
```
We are going to apply a filtering operation to the `getMessages()` function that will return only the messages with the Folder.
`INBOX` property and with the sender property equal to Agat.

**Getting ready**

To implement the filtering transformation, we are going to use the `Iterable<T>.filter(predicate: (T) -> Boolean)` extension function provided by the standard library. 
The `filter()` function takes a predicate function that returns `true` or `false` values for the given element of the generic Iterable dataset type `T`.

_How to do it..._

• Apply filtering to the `getMessages()` function:
```kotlin
getMessages().filter { it.folder == Folder.INBOX &&it.sender == "Agat" }
```

• Iterate through the filtered messages and print their messages to the console:
```kotlin
getMessages().filter { it.folder == Folder.INBOX &&it.sender == "Agat" }
.forEach { (text) ->
    println(text)
}
```

_How it works..._

We are applying the filter function to the results of the `getMessages()` function.
We pass a lambda block to the filter function, which returns a Boolean for each of the list's elements. 
The filter function returns a list containing filtered objects. 
Finally, we use the `forEach()` function to iterate through the next elements of the list. 
In the lambda block passed to the forEach function, we print the text property of the current Message to the console.

As a result, the code from the preceding section is going to print the following output to the console:

`Je t'aime`

**There's more...**

The Kotlin standard library offers corresponding `filter()` extension functions for other types, such as `Array`, `s.Sequence`, and `Map`. 
There are also many variations of the filter function that can be useful for specific scenarios. 
You can find all of them in the official documentation of the Kotlin standard library `kotlin.collections` package at http://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections.

**See also**

• If you'd like to master lambda expressions, you can take a look at the Working effectively with lambdas and closures recipe from [Chapter 3], Shaping Code with Kotlin Functional Programming Features

###Automatic null removal

While working with poorly designed APIs of servers or external libraries, we often need to deal with receiving null return values.
Thankfully, there are a number of standard library features that allow us to handle null values effectively.
In this recipe, we are going to implement a data preprocessing operation which will remove all the nulls from the dataset automatically.
Let's say we are working with an external API that provides us with the latest news feed. 
Unfortunately, it's not null-safe and can return random null values. 
For example, let's assume we have a `getNews(): List<News>` function that returns the following data:
```kotlin
fun getNews() = listOf(
News("Kotlin 1.2.40 is out!", "https://blog.jetbrains.com/kotlin/"),
News("Google launches Android KTX Kotlin extensions for developers",
"https://android-developers.googleblog.com/"),
null,
null,
News("How to Pick a Career", "waitbutwhy.com")
)
```

The News class is defined as follows:
```kotlin
data class News(val title: String, val url: String)
```

**How to do it...**

Apply the filterNotNull function to the getNews() function:
```kotlin
getNews().filterNotNull().forEachIndexed { index, news ->
    println("$index. $news")
}
```

_How it works..._

As a result, we are going to get the following output printed to the console:
```text
0. News(title=Kotlin 1.2.40 is out!, url=https://blog.jetbrains.com/kotlin/)
1. News(title=Google launches Android KTX Kotlin extensions for developers, url=https://android-developers.googleblog.com/)
2. News(title=How to Pick a Career, url=waitbutwhy.com)
```
In contrast, the code without the filterNotNull() function is as follows:
```kotlin
getNews().forEachIndexed { index, news ->
    println("$index. ${news.toString()}")
}
```
This will print the following output to the console:
```text
0. News(title=Kotlin 1.2.40 is out!, url=https://blog.jetbrains.com/kotlin/)
1. News(title=Google launches Android KTX Kotlin extensions for developers, url=https://android-developers.googleblog.com/)
2. null
3. null
4. News(title=How to Pick a Career, url=waitbutwhy.com)
```
The `Iterable.filterNotNull()` extension function removes all the `null` values from the original dataset. 
Under the hood, it copies non-null values to a newly created List instance. 
That's why it is more efficient to use sequences instead of collections in order to provide lazy evaluation for large datasets.

**See also**

• In the Filtering data sets recipe, we explored how to use the basic `filter()` function form provided by the standard library

• If you'd like to master lambda expressions, you can take a look at the Working effectively with lambdas and closures recipe from [Chapter 3], Shaping Code with Kotlin Functional Programming Features

###Sorting data with custom comparators

In this recipe, we are going to explore the support for sorting collections' elements by their properties.

**Getting started**

Let's assume we are dealing with the two collections of the Message type declared as follows:
```kotlin
data class Message(val text: String,
val sender: String,
val receiver: String,
val time: Instant = Instant.now())
```
These are provided by the allMessages variable:
```kotlin
val sentMessages = listOf(
Message("I'm programming in Kotlin, of course", 
"Samuel", 
"Agat", 
Instant.parse("2018-12-18T10:13:35Z")),
Message("Sure!", 
"Samuel", 
"Agat", 
Instant.parse("2018-12-18T10:15:35Z"))
)
val inboxMessages = mutableListOf(
Message("Hey Sam, any plans for the evening?", 
"Samuel", 
"Agat", 
Instant.parse("2018-12-18T10:12:35Z")),
Message("That's cool, can I join you?", 
"Samuel", 
"Agat",
Instant.parse("2018-12-18T10:14:35Z"))
)
val allMessages = sentMessages + inboxMessages
```
If we print the text of consecutive messages from the allMessages list, we get the following text printed to the console:
```text
I'm learning Kotlin, of course
Sure!
Hey Sam, any plans for the evening?
That's cool, can I join you?
```
That doesn't look right.
The messages should be displayed in chronological order. 
That means they should be sorted by the time property.

_How to do it..._

• Apply the sortedBy function to the allMessages collection:
```kotlin
allMessages.sortedBy { it.time }
```

• Print the sorted elements to the console:
```kotlin
allMessages.sortedBy { it.time }
.forEach {
    println(it.text)
}
```

_How it works..._

If we run the preceding code, we get the following output:
```text
I'm programming in Kotlin, of course
Sure!

Hey Sam, any plans for the evening?
That's cool, can I join you?
```
Now, all the messages are sorted properly, and the conversation makes sense.

**There's more...**

If our collection consisted of objects that implement the Comparable interface, we would be able to sort it simply by applying a `sorted()` function to it.
The Kotlin standard library also provides more specialized versions of the `sortedBy()` function, such as `sortedByDescending()` and `sortedWith()`. 
The first one works as a base-sorting function, but it returns the dataset sorted with the opposite order. 
The `sortedWith()` function allows us to sort the list with a custom comparator. 
For example, to sort a collection of the Message type elements first by sender and next by the time property, we could write the following code:
```kotlin
allMessages.sortedWith(compareBy({it.sender}, {it.time}))
```

###Building strings based on dataset elements

Sometimes, we all face the problem of generating text based on collections' elements.
This is where the `Iterable.joinToString()` extension function can help. 
For example, we can consider working on an email-message-forwarding feature. 
When a user clicks the forward button, the original message's body text is concatenated, with the prefix looking something like this:
```html
<br/>
<p>---------- Forwarded message ----------</p>
<p>
From: johny.b@gmail.com <br/>
Date: 14/04/2000 <br/>
Subject: Any plans for the evening?<br/>
To: natasha@gmail.com, barbra@gmail.com<br/>
</p>
```
In this recipe, we are going to implement a function that is going to generate the recipients' string, for example:
```html
To: natasha@gmail.com, barbra@gmail.com</br>
```

For a given list of Address type objects, it is defined as follows:
```kotlin
data class Address(val emailAddress: String, val displayName: String)
```

_How to do it..._

• Declare the `generateRecipientsString()` function header:
```kotlin
fun generateRecipientsString(recipients: List<Address?>): String
```

• Start by removing all the null items from the recipient parameter:
```kotlin
fun generateRecipientsString(recipients: List<Address?>): String =
recipients.filterNotNull()
```
• Transform collection elements of the Address type to the String type elements corresponding to the `Address.emailAddress` property:
```kotlin
fun generateRecipientsString(recipients: List<Address?>): String =
recipients.filterNotNull()
.map { it.emailAddress }
```
• Apply the `joinToString()` function in order to merge collection elements into the string:
```kotlin
fun generateRecipientsString(recipients: List<Address?>): String =
recipients.filterNotNull()
.map { it.emailAddress }
.joinToString(", ", "To: ", "<br/>")
```

_How it works..._

The `generateRecipientsString()` function uses the `Iterable.joinToString()` extension function from the standard library `kotlin.collections` package to generate the output string. 
The `joinToString()` function takes three parameters—the separator character, which is used to concatenate the next substrings, the `prefix`, and the `suffix` strings. 
It is invoked on a collection of `String` values. 
We are also applying the preprocessing operations that are responsible for removing the null values from the list of the Address objects and mapping the `Address` type to the `String` corresponding to the `Address.emailAddress` property.


**There's more...**

We could also use another version of the `joinToString()` function to simplify the logic of our `generateRecipientsString()` function implementation:
```kotlin
fun generateRecipientsString(recipients: List<Address?>): String =
recipients.filterNotNull()
.joinToString(", ", "To: ", "<br/>") { it.emailAddress }
```
As you can see, it takes the additional argument in the form of an inlined lambda block, which acts as a transformation function that is being applied to each of the recipients' collection elements.

**See also**

• To explore dataset mapping operations in more depth, you can read the Data transformation with the `map` and `flatMap` recipe

###Dividing data into subsets

A common data-processing task is to FP.divide a collection of data into subsets.
In this recipe, we are going to explore standard library functions that allow us to buffer a collection into smaller chunks. 
Let's say we have a list containing a large number of Message type objects and we would like to transform it into collections of sub-lists of a constant size. 
For example, the transformation would take the original collection of n elements:

`[mssg_1, mssg_2, mssg_3, mssg_4, mssg_5, mssg_6, mssg_7, ..., mssg_n]`

And it would then split it into a collection of four element subsets:

`[[mssg_1, mssg_2, mssg_3, mssg_4], ..., [mssg_n-3, mssg_n-2, mssg_n-1, mssg_n]]`

**Getting ready**

Let's start by declaring the Message class that we are going to use in the following recipe:
```
data class Message(val text: String,
                      val time: Instant = Instant.now())
```
Let's declare the messages variable that stores the sample data:
```kotlin
val messages = listOf(
Message("Any plans for the evening?"),
Message("Learning Kotlin, of course"),
Message("I'm going to watch the new Star Wars movie"),
Message("Would u like to join?"),
Message("Meh, I don't know"),
Message("See you later!"),
Message("I like ketchup"),
Message("Did you send CFP for Kotlin Conf?"),
Message("Sure!")
)
```

_How to do it..._

• Apply the `windowed()` function to the messages list: 
```kotlin
val pagedMessages= messages.windowed(4, partialWindows = true, step = 4)
```

• Add a `transform: (List<T>) -> R` transformation function as an additional, inline parameter to the windowed function:
```kotlin
val pagedMessages= messages.windowed(4, partialWindows = true, step = 4) { 
it.map { it.text }
}
```

_How it works..._

The windowed function splits the original list of messages into sublists of a specified size.
As a result, we get the `List<List<Message>>` type assigned to the pagedMessages handle. 
We could print the next message subsets with the following code:
```kotlin
pagedMessages.forEach { println(it) }
```

As the result, we get the following output printed to the console:
```text
[Any plans for the evening?, Learning Kotlin, of course, I'm going to watch the new Star Wars movie, Would u like to join?]
[Meh, I don't know, See you later!, I like the ketchup, Did you send CFP for Kotlin Conf?]
[Sure!]
```

The windowed function takes four parameters—the size of the window, a flag saying whether partial windows should be created, a step value, and an optional transforming function that is responsible for converting each of the generated windows. 
In our scenario, we are using a window size equal to `4`. 
This is why we need to specify the step value as equal to `4` as well because we want to have consecutive Message instances stored in the next windows.
We also set the `partialWindows` argument to true, otherwise, the last window containing a single message would be omitted. 
The last param of the windowed function allows us to map each of the windows into another type. 
We are mapping each of sublists returned by the `windowed()` function into the `List<String>` type.
There is also another version of the windowed function, without the last mapping parameter, so it can be treated as the optional one.

**There's more...**

There is also a handy wrapper of the `windowed()` function provided, called `chunked()`.
It doesn't require the step argument and sets it automatically to the window size value. 
It would be a good fit for this recipe's problem, however, the `windowed()` function was explained as it's more basic.

**See also**

• There are other functions available that solve different list and collection division scenarios, such as the `subList()` (https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/sub-list.html) and `partition()` (https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/partition.html) functions.
You can find out more about them in official docs using the provided links. 

Data transformation with map and flatMap
The support for declarative data mapping operations is one of the basic and most powerful features in the functional data-processing domain.
Often, when working with data, we need to transform a collection of a specific type into another type. 
It's also a common scenario to generate a list of objects from each element of a collection and to merge all of those new objects in a target collection together. 
Those are the use cases where the `map()` and `flatMap()` extension functions help.

In this recipe, we are going to use both of them to implement a mapping data transformation.
Let's imagine we are working on the part of the system responsible for managing university department lectures.
We are given the following types:
```kotlin
class Course(val name: String, val lecturer: Lecturer, val isPaid: Boolean = false)
class Student(val name: String, val courses: List<Course>)
class Lecturer(val name: String)
```
We also have a `getStudents(): List<Student>` function, which returns a list of all students from the database. 
We want to implement the `getLecturesOfCoursesWithSubscribedStudents()` function, which is going to to transform the `getStudents()` result to compute a list of lecturers whose courses are subscribed to by at least one student.

_How to do it..._

• Declare a function header:
```kotlin
fun getLecturesOfCoursesWithSubscribedStudents()
```

• Apply the flatMap operation to the list of students:
```kotlin
fun getLecturesOfCoursesWithSubscribedStudents() =
                                      getStudents().flatMap { student -> student.courses }
```
• Limit the collections' elements to distinct values:
```kotlin
fun getLecturesOfCoursesWithSubscribedStudents() =
              getStudents().flatMap { student -> student.courses }.distinct()
```



• Map the collection of the Course type elements to their corresponding Lecturer type properties:
```kotlin
fun getLecturesOfCoursesWithSubscribedStudents() =
                         getStudents().flatMap { student -> student.courses }
                           .distinct().map { course ->course.lecturer }.distinct()
```

_How it works..._

With the following `flatMap` operation, the `getLecturesOfCoursesWithSubscribedStudents()` function is transforms the collection of the Student type objects into the collection of the Course type by merging elements of the `Student.courses: Collection<Course>` property:
```kotlin
getStudents().flatMap { student: Student -> student.courses }
```
As the result, the preceding code returns the `Collection<Course>` type.
The collection returned by the `flatMap` operation contains a set of all the courses all of the students (obtained from the `getStudents()` function) are subscribed to.
Next, in order to remove duplicated courses, we append the chain of operations with the `distinct()` function. 
Then, we use the `map()` function.
It is responsible for transforming every single element of the Course type into its corresponding Lecturer type from the `Course.lecturer` property.
Finally, we are applying the `distinct()` function once again to return the list of lecturers with no duplicates. 

**There's more...**

The `map()` and `flatMap()` extension functions are also available for the `Map` data structure type.
They are useful when it comes to converting a map to a list of objects transformed from the map's key-value pairs.


###Folding and reducing datasets

While the `map()` operator takes a list of a given size and returns another list of the same size and of the modified type, the `fold()` and `reduce()` operations applied to the data set return a single element, composed of the consecutive elements of the data set. 
This may sound like a simple scenario for using a plain, imperative-style loop and local accumulator variable that holds a current state and is updated at each iteration.
We can consider the simple task of summing up integer values. 
Let's consider that we want to compute a sum of consecutive integers from `0` to `10`. 
We could achieve it using a simple for loop:
```kotlin
var sum= 0
(1..10).forEach { sum+= it }
```
However, there is an alternative, functional way of performing such computations, using the `fold()` function:
```kotlin
val sum = (1..3).toList().fold(0) { acc, i ->acc + i }
```

The second approach is desirable whenever we implement a chain of functional data-processing operations. 
Compared to the for loop, the fold function doesn't enforce consuming the collection elements explicitly and can be easily used together with other functional operators.
In this recipe, we are going make use of the fold function when implementing the function responsible for processing the audio album tracks. 
Let's assume we are given the following data types:
```kotlin
data class Track(val title: String, val durationInSeconds: Int)
data class Album(val name: String, val tracks: List<Track>)
and the sample Album class instance:
val album = Album("Sunny side up", listOf(
Track("10/10", 176),
Track("Coming Up Easy", 292),
Track("Growing Up Beside You", 191),
Track("Candy", 303),
Track("Tricks of the Trade", 151)
))
```
We want to implement an extension function for the Album type that will return a relative start time for the Track given as an argument. 
For example, the start time of the Growing Up Beside You track should be **468** seconds.

_How to do it..._

• Declare an extension function for the Album class:
```kotlin
fun Album.getStartTime(track: Track): Int
```

• Compute the start time for the given Track argument:
```kotlin
fun Album.getStartTime(track: Track): Int {
val index = tracks.indexOf(track)
return this.tracks
.take(index)
.map { (name, duration) ->duration }
.fold(0) { acc, i ->acc + i}
}
```
• Add a safety check for the track argument:
```kotlin
fun Album.getStartTime(track: Track): Int {
if (track !in tracks) throw IllegalArgumentException("Badtrack")
 
val index = tracks.indexOf(track)
return tracks
    .take(index)
    .map { (name, duration) ->duration }
    .fold(0) { acc, i ->acc + i }
}
```

_How it works..._

At the very beginning, our function does a safety check for the track argument passed to it to verify whether it belongs to the current Album instance. 
If the given track is not found within the `Album.tracks` collection, the `IllegalArgumentException` exception is thrown. 
Next, we create a sublist from the tracks property elements containing only the elements from the 0 index to the index of the track passed as the function parameter.
This sublist is created using the `take()` operator. 
Then, we map each of the Track type elements to the Int type corresponding to the duration of the track. 
Finally, we apply the fold function, to sum the durationInSeconds property values of the consecutive Track elements.
The fold function takes the initial argument responsible for initializing the internal accumulator variable holding the current state of the folding result.

In our case, we pass `0` as the initial value, which corresponds to the album start time. 
In the second argument passed to the fold function, we are defining how the accumulator should be updated with each of the consecutive `durationInSeconds` values.
Let's test the `Album.getStartTime()` function in action:
```kotlin
println(album.getStartTime(Track("Growing Up Beside You", 191)))
println(album.getStartTime(Track("Coming Up Easy", 292)))
```
The preceding code returns the following output:

`468`

`176`

**There's more...**

The standard library provides a similar function, named `reduce()`, which does the same operation as fold.
The difference between the two is that fold takes an explicit initial value, whereas reduce uses the first element from the list as the initial value. 

###Grouping data

The Kotlin standard library provides built-in support for the dataset group by operation.
In this recipe, we are going to explore how to use it.
Let's assume we are working with the following types:
```kotlin
class Course(val name: String, val lecturer: Lecturer, val isPaid: Boolean = false)
class Student(val name: String, val courses: List<Course>)
class Lecturer(val name: String)
```
We also have a `getStudents(): List<Student>` function that returns a list of all the students from the database.
Given the `getStudents(): List<Student>` function, we are going to implement the `getCoursesWithSubscribedStudents(): Map<Course, List<Student>>` function responsible for extracting the map of all the courses students are subscribed to, and the list of students subscribed to each of the courses.


_How to do it..._

• Declare a function header:
```kotlin
fun getCoursesWithSubscribedStudents(): Map<Course, List<Student>>
```

• Map each of the students to the list of the course-student pairs:
```kotlin
fun getCoursesWithSubscribedStudents(): Map<Course, List<Student>> =
    getStudents().flatMap { student -> 
        student.courses.map { course ->course to student }
}
```
• Group the course-student pairs by Course:
```kotlin
fun getCoursesWithSubscribedStudents(): Map<Course, List<Student>> =
    getStudents().flatMap { student -> 
        student.courses.map { course ->course to student }
    }.groupBy { (course, student) -> course }

```
• Apply a mapping transformation to the `Pair<Course, List<Student>>` type:
```kotlin
fun getCoursesWithSubscribedStudents(): Map<Course,List<Student>> =
    getStudents().flatMap { student -> student.courses.map { course ->
            course to student }
    }.groupBy { (course, _) ->course }.map { (course, courseStudentPairs) -> 
        course to courseStudentPairs.map { (_, student) -> student } 
}
```
• Apply a `toMap()` function at the end:
```kotlin
fun getCoursesWithSubscribedStudents(): Map<Course,List<Student>> = 
    getStudents().flatMap { 
            student -> student.courses.map { course ->course to student } 
    }.groupBy { (course, _) ->course }.map { 
            (course, courseStudentPairs) -> course to courseStudentPairs.map { 
            (_, student) -> student } 
    }.toMap()
```

_How it works..._

We start by transforming the list of students to list of the `Pair<Course, Student>` type with the `flatMap()` function.
Next, we apply the `groupBy()` function to group those pairs by a distinct Course instance. 
As the result of the grouping operation, we receive data of the following `type—Map.Entry<Course, List<Pair<Course, Student>>>`. 
We need to convert the `Map.Entry.value` property type to the `List<Student>` type.
We achieve it with the following mapping transforming function:
```kotlin
map { (course, courseStudentPairs) -> course to courseStudentPairs.map { 
        (_, student) ->student
}
}
```
As a result, each Course instance is associated with a list of students subscribed to it (`Pair<Course, List<Student>>`).
Note that the infix to function is being used to instantiate the Pair type.
Finally, we invoke the `toMap()` function, which produces the final `Map<Course, List<Students>>` instance from the list of course-student pairs.

**There's more...**

We can also modify our map building operation to a more concise form by using the mapValues function:
```kotlin
fun getCoursesWithSubscribedStudents(): Map<Course, List<Student>> =
    getStudents().flatMap {  
            student -> student.courses.map { course ->course to student }
        }.groupBy { 
            (course, _) ->course 
        }.mapValues { 
            (course, courseStudentPairs) -> courseStudentPairs.map { it ->it.second }
}
```



**See also**

• The code in this recipe uses destructuring types declarations in mapping operations. 
If you'd like to learn more about this, you can take a look at the Destructuring types recipe from [Chapter 2], Expressive Functions and Adjustable Interfaces.
