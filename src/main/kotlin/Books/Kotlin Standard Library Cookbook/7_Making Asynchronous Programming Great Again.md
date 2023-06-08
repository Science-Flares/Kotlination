##Making Asynchronous Programming Great Again

In this chapter, we will cover the following recipes:

• Executing tasks in the background using threads

• Background threads synchronization

• Using coroutines for asynchronous, concurrent execution of tasks

• Using coroutines for asynchronous, concurrent tasks execution with results handling

• Applying coroutines for asynchronous data processing 

• Easy coroutine cancelation

• Building a REST API client with Retrofit and a coroutines adapter

• Wrapping third-party callback-style APIs with coroutines


###Introduction

This chapter is going to address various aspects of asynchronous programming problems.
The first two recipes, **Executing tasks in the background using threads** and Background-threads synchronization, are going to explain the standard library support for running background tasks using **JVM** threads.


In the further recipes, we are going to delve more deeply into the powerful Kotlin **Coroutines** framework.
Those recipes are going to explain a FP.general usage of coroutines for asynchronous and concurrent tasks executing. 
They will also present how to employ coroutines for solving more specific daily-life programming problems, such as concurrent data processing, asynchronous REST-call handling, and working with third-party callback-style APIs in a clean way.
After reading this chapter, you will feel convenient applying the coroutines framework to write robust asynchronous code or to optimize your code by running expensive computations concurrently.
The Kotlin Coroutines framework is not only a handy replacement for platform-specific concurrency and async frameworks. Its power is based on providing a unified, universal API that allows us to write asynchronous code, which can be run both on JVM, Android, JavaScript, and native platforms.

###Executing tasks in the background using threads

In this recipe, we are going to explore how to work effectively with the JVM Thread class in a clean way using the Kotlin standard library functions dedicated to convenient thread-running. 
We are going to simulate two long-running tasks and execute them concurrently in background threads.

**Getting ready**

In this recipe, we are going to make use of two functions simulating long-running operations. Here is the first:
```kotlin

private fun `5_sec_long_task`() = Thread.sleep(5000)
And here is the second:
private fun `2_sec_long_task`() = Thread.sleep(2000)
```
They are both just responsible for blocking a current thread for five and two seconds, respectively, in order to simulate long-running tasks.
We will also make use of the predefined function returning the current thread name for debugging purposes:
```kotlin
private fun getCurrentThreadName(): String = Thread.currentThread().name
```


_How to do it..._

• Let's start by logging the current thread name to the console:
```kotlin
println("Running on ${getCurrentThreadName()}")
```
• Start a new Thread and invoke the `5_sec_long_task`() function inside it:
```kotlin
println("Running on ${getCurrentThreadName()}")
 
thread {
println("Starting async operation on ${getCurrentThreadName()}")
`5_sec_long_task`()
println("Ending async operation on ${getCurrentThreadName()}")
}
```
• Start another Thread and invoke `2_sec_long_task`() inside it:
```kotlin

println("Running on ${getCurrentThreadName()}")
 
thread {
println("Starting async operation on ${getCurrentThreadName()}")
`5_sec_long_task`()
println("Ending async operation on ${getCurrentThreadName()}")
}
 
thread {
println("Starting async operation on ${getCurrentThreadName()}")
`2_sec_long_task`()
println("Ending async operation on ${getCurrentThreadName()}")
}
```

_How it works..._

The preceding code is going to print the following text to the console:
```text
Running on main
Starting async operation on Thread-0
Starting async operation on Thread-1
Ending async operation on Thread-1
Ending async operation on Thread-0
```

As you can see, we have successfully started two background threads, which are running concurrently. 
We are using the `thread()` utility function from the `kotlin.concurrent` package, which is responsible for instantiating and starting a new thread that runs a block of code passed to it in the form of a lambda expression.

**See also**

• Take a look at the rest of the recipes to discover how to use the Kotlin Coroutines framework to replace the threading mechanism with a more robust and flexible framework.
A good starting point could be the Using coroutines for asynchronous concurrent-tasks execution and Using coroutines for asynchronous concurrent-tasks execution with results-handling recipes.

###Background threads synchronization

In this recipe, we are going to explore how to work effectively with the JVM Thread class in a clean way using the Kotlin standard library functions dedicated to running threads in a convenient way. 
We are going to simulate two long-running tasks and execute them in background threads synchronously.

**Getting ready**

In this recipe, we are going to make use of the following two functions to simulate long-running operations. 
The `5_sec_long_task`() function:
```kotlin

private fun `5_sec_long_task`() = Thread.sleep(5000)
and the `2 sec long task`() function:
private fun `2_sec_long_task`() = Thread.sleep(2000)
```
They are both just responsible for blocking a current thread for five and two seconds, respectively, in order to simulate long-running tasks.
We will also make use of the predefined function returning the current thread name for debugging purposes:
```kotlin

private fun getCurrentThreadName(): String = Thread.currentThread().name
```


_How to do it..._

• Let's start by logging the current thread name to the console:
```kotlin

println("Running on ${getCurrentThreadName()}")
```
• Start a new Thread and invoke the `5 sec long task`() function inside it:
```kotlin

println("Running on ${getCurrentThreadName()}")
 
thread {
println("Starting async operation on ${getCurrentThreadName()}")
`5 sec long task`()
println("Ending async operation on ${getCurrentThreadName()}")
}
```
• Wait until the thread completes:
```kotlin

println("Running on ${getCurrentThreadName()}")
 
thread {
println("Starting async operation on ${getCurrentThreadName()}")
`5 sec long task`()
println("Ending async operation on ${getCurrentThreadName()}")
}.join()
```
• Start another Thread and invoke `2 sec long task`() inside it:
```kotlin

println("Running on ${getCurrentThreadName()}")
 
thread {
println("Starting async operation on ${getCurrentThreadName()}")
`5 sec long task`()
println("Ending async operation on ${getCurrentThreadName()}")
}.join()
 
thread {
println("Starting async operation on ${getCurrentThreadName()}")
`2 sec long task`()
println("Ending async operation on ${getCurrentThreadName()}")
}
```

• Wait until the thread completes:
```kotlin

println("Running on ${getCurrentThreadName()}")
 
thread {
println("Starting async operation on ${getCurrentThreadName()}")
`5 sec long task`()
println("Ending async operation on ${getCurrentThreadName()}")
}.join()
 
thread {
println("Starting async operation on ${getCurrentThreadName()}")
`2 sec long task`()
println("Ending async operation on ${getCurrentThreadName()}")
}.join()
```
• Test whether the main thread is free at the end:
```kotlin

println("Running on ${getCurrentThreadName()}")
 
thread {
println("Starting async operation on ${getCurrentThreadName()}")
`5 sec long task`()
println("Ending async operation on ${getCurrentThreadName()}")
}.join()
 
thread {
println("Starting async operation on ${getCurrentThreadName()}")
`2 sec long task`()
println("Ending async operation on ${getCurrentThreadName()}")
}.join()
 
println("${getCurrentThreadName()}thread is free now")
```

_How it works..._

The preceding code is going to print the following text to the console:
```text

Running on main
Starting async operation on Thread-0
Ending async operation on Thread-0
Starting async operation on Thread-1




Ending async operation on Thread-1
main thread is free now
```
We have successfully started two background threads, which are synchronized. 
In order to run both background threads sequentially, we are using the Thread.join() function, which just blocks the main thread until the background thread completes.
In order to instantiate and start a new background thread, we are using the thread() utility function from the kotlin.concurrent package.
We are passing it a block of code to be run inside the thread inside a lambda expression. 

**See also**

• Take a look at the next recipes explaining how to use the Kotlin Coroutines framework to replace the threading mechanism with a more robust and flexible framework. 
A good starting point could be the Using coroutines for asynchronous concurrent-tasks execution and Using coroutines for asynchronous concurrent-tasks execution with results-handling recipes.

###Using coroutines for asynchronous, concurrent execution of tasks

In this recipe, we are going to explore how to use the coroutines framework in order to schedule asynchronous, concurrent execution of tasks. We are going to learn both how to synchronize a sequence of short background tasks and how to run expensive, long-running ones at the same time. We will simulate the sushi rolls preparation process to discover how to schedule blocking and non-blocking tasks together.

**Getting ready**

The first step to start working with Kotlin Coroutines is to add the core framework dependency to the project:
```groovy
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:0.23.3'
```
The preceding code declares the kotlinx-coroutines-core dependency in a Gradle build script, which is used in the sample project (https://github.com/PacktPublishing/Kotlin-Standard-Library-Cookbook).

In the current recipe, we will assume our sushi-cooking simulation requires the four following steps to be performed:

• Cook the rice 

• Prepare the fish

• Cut the vegetables

• Roll the sushi


These steps are going to be simulated by the following functions:
```kotlin
private fun `cook rice`() {
println("Starting to cook rice on ${getCurrentThreadName()}")
Thread.sleep(10000)
println("Rice cooked")
}
 
private fun `prepare fish`() {
println("Starting to prepare fish on ${getCurrentThreadName()}")
Thread.sleep(2000)
println("Fish prepared")
}
 
private fun `cut vegetable`() {
println("Starting to cut vegetables on ${getCurrentThreadName()}")
Thread.sleep(2000)
println("Vegetables ready")
}
 
private fun `roll the sushi`() {
println("Starting to roll the sushi on ${getCurrentThreadName()}")
Thread.sleep(2000)
println("Sushi rolled")
}
```
We will also use the following function to log a current thread name to the console: 
```kotlin

private fun `print current thread name`() {
println("Running on ${getCurrentThreadName()}")
println()
}
 
private fun getCurrentThreadName(): String = Thread.currentThread().name
```

For the sake of the exercise, we will assume the sushi-roll preparation process must fulfill the following requirements:

• The longest Rice-cooking step must be executed in the background in a non-blocking way

• The Fish-preparation and Vegetable-cutting steps have to be performed one by one while the rice is cooking

• The Sushi-rolling step can be done only when the first three steps are completed


_How to do it..._

• Let's start by logging the current thread name to the console:
```kotlin
`print current thread name`()
```
• Start a new coroutine running on a pool of background threads:
```kotlin

`print current thread name`()
var sushiCookingJob: Job
sushiCookingJob = launch(newSingleThreadContext("SushiThread")) {
`print current thread name`()
}
```
• Execute the `cook rice`() function asynchronously in a nested coroutine:
```kotlin

`print current thread name`()
var sushiCookingJob: Job
sushiCookingJob = launch(newSingleThreadContext("SushiThread")) {
`print current thread name`()
val riceCookingJob = launch {
`cook rice`()
}
}
```
• Run the `prepare fish`() and `cut vegetable`() functions sequentially while the `cook rice`() function is running in the background:
```kotlin

`print current thread name`()
var sushiCookingJob: Job
sushiCookingJob = launch(newSingleThreadContext("SushiThread")) {
`print current thread name`()
val riceCookingJob = launch {
`cook rice`()
}
println("Current thread is not blocked while rice is being
cooked")
`prepare fish`()
`cut vegetable`()
}
```
• Wait until the rice-cooking coroutine completes:
```kotlin

`print current thread name`()
var sushiCookingJob: Job
sushiCookingJob = launch(newSingleThreadContext("SushiThread")) {
`print current thread name`()
val riceCookingJob = launch {
`cook rice`()
}
println("Current thread is not blocked while rice is being
cooked")
`prepare fish`()
`cut vegetable`()
riceCookingJob.join()
}
```
• Invoke the final `roll the sushi`() function and wait until the main coroutine completes:
```kotlin

`print current thread name`()
var sushiCookingJob: Job
sushiCookingJob = launch(newSingleThreadContext("SushiThread")) {
`print current thread name`()
val riceCookingJob = launch {
`cook rice`()
}
println("Current thread is not blocked while rice is being
cooked")
`prepare fish`()
`cut vegetable`()
riceCookingJob.join()
`roll the sushi`()
}
runBlocking {
sushiCookingJob.join()
}
```
• Measure the total time for function execution and log it to the console:
```kotlin

`print current thread name`()
var sushiCookingJob: Job
val time = measureTimeMillis {
sushiCookingJob = launch(newSingleThreadContext("SushiThread")) {
`print current thread name`()
val riceCookingJob = launch {
`cook rice`()
}
println("Current thread is not blocked while rice is being
cooked")
`prepare fish`()
`cut vegetable`()
riceCookingJob.join()
`roll the sushi`()
}
runBlocking {
sushiCookingJob.join()
}
}
println("Total time: $timems")
```

_How it works..._

The preceding code is going to print the following text to the console:
```text

Running on main
Running on SushiThread
Current thread is not blocked while rice is being cooked
Starting to cook rice on ForkJoinPool.commonPool-worker-1
Starting to prepare fish on SushiThread
Fish prepared
Starting to cut vegetables on SushiThread
Vegetables ready
Rice cooked
Starting to roll the sushi on SushiThread
Sushi rolled
Total time: 12089 ms
```
In the beginning, we start a new coroutine running on a background thread with the launch() function call. 
We also create a handle to the Job instance returned by the launch() function under the var sushiCookingJob: Job variable.

The launch() function starts a new coroutine instance on a default CoroutineContext instance. However, we are able to pass our desired CoroutineContext as an additional parameter to the launch() function. 
When targeting the JVM platform, by default the launch() function starts a coroutine on a pool of background threads, which corresponds to the CommonPool context constant. 
We can also run the coroutine on a single thread by passing a context result of the newSingleThreadContext() function.
If you are working with UI frameworks, such as Android, Swing, or JavaFx, you can run a coroutine on a UI context as well. 
The UI context is related to the main thread responsible for user-interface updates. 
There are different modules that provide the UI context implementation dedicated to a specific framework. 
You can learn more about framework-specific UI programming with coroutines in the following official guide: https://github.com/Kotlin/kotlinx.coroutines/blob/master/ui/coroutines-guide-ui.md.
Inside the main coroutine, we are starting a new one and invoking the `cook rice()` function inside it. 
We are storing a handle to the Job instance corresponding to the coroutine handling the `cook rice`() function under the val riceCookingJob: Job variable.
At this point, the rice-cooking task begins to run concurrently on a pool of threads.

**Next**, we are invoking two functions—`prepare fish`() and `cut vegetable`(). 
As you can see in the console output, those functions are executed sequentially.
The vegetable-cutting task starts right after the fish-preparation completes.
If we'd like to run them concurrently, we'd need to start each one inside a new coroutine.

**Finally**, we wait for the completion of the rice-cooking task by calling a join() function on the riceCookingJob variable.
Here, the join() function suspends the primary sushiCookingJob coroutine until riceCookingJob is complete.
Right after the primary coroutine gets unblocked, the last `roll the sushi`() function is invoked.
In order to await the primary coroutine completion, we need to invoke a join() function on the sushiCookingJob instance after starting it on the main thread.
However, we are not able to call the join() function outside of a coroutine scope. We need to call it inside a new blocking coroutine started with a runBlocking() function.

The coroutines framework is designed to allow us to execute tasks in a non-blocking way. Although we are able to write non-blocking code inside a coroutine's scope, we need to provide a bridge to the original thread inside the application that starts the primary coroutine.
We are able to connect the non-blocking coroutine scope with the blocking world outside using the runBlocking() function.
 The runBlocking() function starts a new coroutine and blocks the current thread until its completion.
It is designed to bridge regular blocking code to libraries that are written in suspending style. 
For example, it can be used in main() functions and in tests.
Coroutines can be seen as lightweight thread replacements. Coroutines are lightweight in terms of resource consumption. 
For example, we can start a million coroutines concurrently with ease, where, after a second, each of them logs the current thread name to the console:
```kotlin

runBlocking {
(0..1000000).map {
launch {
delay(1000)
println("Running on ${Thread.currentThread().name}")
}
}.map { it.join() }
}
```
The preceding code will complete in about 10 seconds on a standard computer. 
In contrast, if we try to run this code using threads, we get the OutOfMemoryError: unable to create new native thread exception.

**See also**

• You can follow up by reading the **Using coroutines for asynchronous, concurrent tasks execution with results handling** recipe.
It shows you how to asynchronously schedule functions that return the results.



###Using coroutines for asynchronous, concurrent tasks execution with results handling

In this recipe, we are going to explore how to use the coroutines framework in order to run asynchronous operations concurrently, and learn how to handle the results they return properly. We are going to schedule two tasks and run them in the background using two coroutines.
The first task is going to be responsible for displaying the progress-bar animation.
The second one is going to simulate long-running computations. 
In the end, we are going to print the results returned by the second task to the console.

**Getting ready**

The first step to start working with Kotlin Coroutines is to add a core framework dependency to the project:
```groovy

implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:0.23.3'
```
The preceding code declares the kotlinx-coroutines-core dependency in a Gradle build script, which is used in the sample project (https://github.com/PacktPublishing/Kotlin-Standard-Library-Cookbook).
In the current recipe, we are going to make use of the following two functions: 
```kotlin

private suspend fun `calculate the answer to life the universe and everything`(): Int {
delay(5000)
return 42
}
 
private suspend fun `show progress animation`() {
val progressBarLength = 30
var currentPosition = 0
while (true) {
print("\r")
val progressbar = (0 until progressBarLength)
.map { if (it == currentPosition) " " else "░" }
.joinToString("")
print(progressbar)
 
delay(50)
 
if (currentPosition == progressBarLength) {
currentPosition = 0
}
currentPosition++
}
}
```
The first one is simulating an expensive computation that delays a thread for five seconds and returns the result in the end.
The second one is responsible for displaying an infinite progress-bar animation.
We are going to start both operations concurrently and wait for the result returned by the first one.
After we get the result, we will print it to the console.
We will also use the following function to log a current thread name to the console:
```kotlin

private fun `print current thread name`() {
println("Running on ${getCurrentThreadName()}")
println()
}
 
private fun getCurrentThreadName(): String = Thread.currentThread().name
```

_How to do it..._

• Start by logging a current thread name to the console:
```kotlin

`print current thread name`()
```
• Start a coroutine responsible for displaying progress-bar animation from the background:
```kotlin

`print current thread name`()
 
launch {
println("Starting progressbar animation on ${getCurrentThreadName()}")
`show progress animation`()
}
```
• Start a coroutine responsible for running the `calculate the answer to life the universe and everything`() function in the background:
```kotlin

`print current thread name`()
 
launch {
println("Starting progressbar animation on ${getCurrentThreadName()}")
`show progress animation`()
}
 
val future = async {
println("Starting computations on ${getCurrentThreadName()}")
`calculate the answer to life the universe and everything`()
}
 
println("${getCurrentThreadName()}thread is not blocked while tasks are in progress")
```
• Wait for the result returned by the future coroutine and print it to the console:
```kotlin

`print current thread name`()
 
launch {
println("Starting progressbar animation on ${getCurrentThreadName()}")
`show progress animation`()
}
 
val future = async {
println("Starting computations on ${getCurrentThreadName()}")
`calculate the answer to life the universe and everything`()
}
 
println("${getCurrentThreadName()}thread is not blocked while tasks are in progress")
 
runBlocking {
println("\nThe answer to life the universe and everything: ${future.await()}")
`print current thread name`()
}
```

_How it works..._

Our code is going to display the progress-bar animation for five seconds and then print the result of the `calculate the answer to life the universe and everything()` function once it completes the simulated calculations:
```text

Running on main
Starting progressbar animation on ForkJoinPool.commonPool-worker-1
Starting calculation of the answer to life the universe and everything on ForkJoinPool.commonPool-worker-2
main thread is not blocked while background tasks are still in progress
░░░ ░░░░░░░░░░░░░░░░░░░░░░░░░░
The answer to life the universe and everything: 42
Running on main
```
We start the execution of `calculate the answer to life the universe and everything()` in the background task using the async() function. 
It just starts a new coroutine and returns an instance of a Deferred<T> class. 
The generic T type corresponds to the type of object that is returned by async(). 
An instance of the Deferred<T> type is just a pointer to the future result delivered by the coroutine. 
It's a representation of asynchronous programming constructs, called futures or promises.
We are able to evaluate the value of a Deferred object by calling the await() function on it. 
However, we are not able to call the await() function outside a coroutine scope. 
We need to call it inside a new blocking coroutine started with a runBlocking() function.
The coroutines framework is designed to allow us to execute tasks in a non-blocking way.
Although we are able to write non-blocking code inside a coroutine's scope, we need to provide a bridge to the original thread inside the application that starts the primary coroutine. 
We are able to connect the non-blocking coroutine scope with the blocking world outside using the runBlocking() function. 
The runBlocking() function starts a new coroutine and blocks the current thread until its completion. 
It is designed to bridge regular blocking code to libraries that are written in suspending style. 
For example, it can be used in main() functions and in tests.
As far as the progress-bar animation is concerned, we are scheduling it in the background using the launch() function.
launch() is responsible for starting a new coroutine, however, it does not care about delivering the final results.

**There's more...**

You may have noticed our predefined functions are marked with the suspend modifier declared before the fun keyword, for example, suspend fun `show progress animation`().
The reason behind this is that we need to declare explicitly that the function is going to run inside the coroutine scope to be able to use coroutine-specific features inside the function's body. 
In our case, we are using the delay() function, which can be invoked only inside a coroutine scope.
It is responsible for pausing the coroutine for a given amount of time without blocking the current thread.







**See also**

•  You can investigate another usage of the delay() function in the **Applying coroutines for asynchronous data processing** recipe. 
You can also explore different use cases of suspending functions in the Easy coroutines cancelation recipe.

• If you'd like to learn more about concurrent, asynchronous tasks-scheduling with coroutines, you can take a look at the **Using coroutines for asynchronous, concurrent tasks execution with results handling** recipe. 
It explains how to schedule both sequential and concurrent tasks running in a common coroutine.

###Applying coroutines for asynchronous data processing

In this recipe, we are going to implement a generic extension for the Iterable type, which will provide a replacement for the Iterable<T>.map() function.
Our implementation of the Iterable<T>.mapConcurrent() function is going to allow data-mapping-operation optimization by running it concurrently with coroutines.
**Next**, we are going to test our concurrent mapping function implementation by employing it to perform a simulation of a time-expensive operation applied to each of the elements of a sample Iterable object.

_How to do it..._

• Implement an extension function for the generic Iterable<T> class responsible for handling the mapping operation of its elements concurrently:
```kotlin

suspend fun <T, R>Iterable<T>.mapConcurrent(transform: suspend (T) ->R) =
this.map {
async { transform(it) }
}.map {
it.await()
}
```
• Simulate time-consuming mapping operations applied to the sample Iterable range elements:
```kotlin

runBlocking {
(0..10).mapConcurrent {
delay(1000)
it * it
}
}
```
• Print the mapped elements to the console:
```kotlin

runBlocking {
(0..10).mapConcurrent {
delay(1000)
it * it
}.map { println(it) }
}
```
• Measure the total time of the concurrent-mapping operation's execution and log it to the console:
```kotlin

runBlocking {
val totalTime = measureTimeMillis {
(0..10).mapConcurrent {
delay(1000)
it * it
}.map { println(it) }
}
println("Total time: $totalTimems")
}
```

_How it works..._

Let's start by analyzing the effects of applying the mapConcurrent() function we implemented at the beginning to transform elements of the (0..10) range of integers. 
In the lambda block passed to themapConcurrent function, we are simulating a long-running processing operation suspending the coroutine for one second, using the delay(1000) function and returning a square of the original integer value.
Our code is going to print the following results to the console:
```text

0
1
4
9
16
25
36
49
64
81
100
Total time: 1040 ms
```
Our implementation of the Iterable.mapConcurrent() extension function takes a functional parameter, transform: suspend (T) -> R, which represents an operation that is going to be applied to each element of the original of the Iterable object.
Under the hood, in order to perform data transformation concurrently, there is a new coroutine started for each of the original elements using the async() function, and the transform function is applied to them.
At this point, the original Iterable<T> instance has been transformed to the Iterable<Deferred<T>> type.
Next, the instances of the consecutive Deferred type, returned by invocations of async(), are synchronized and transformed to the generic R type by calling the await() functions on them.
In the end, we have an Iterable of the desired R type returned.
As you can see, in the output of our example, the transformation of 10 integer numbers using the Iterable.mapConcurrent() function took roughly one second on a standard computer. 
You can try running the same transformations using the standard Iterable.map() and it will take around 10 seconds.
In order to simulate the delay inside the transform lambda block passed to the mapConcurrent() function, we use the delay() function with a specified time value passed. 
The delay() is suspending the coroutine for a given amount of time, but it's not blocking a thread.
The transform block is being executed for each of the elements on the pool of background threads. 
Whenever one coroutine is suspended, another one is starting to run in place of the first one. 
If we replace the non-blocking delay(1000) call with the blocking Thread.sleep(1000) function, our example will finish in about four seconds. It is still a big win compared to the standard Iterable.map() function which doesn't run concurrently by default.
The coroutines framework is designed to allow us to execute tasks in a non-blocking way. 
Although we are able to write non-blocking code inside a coroutine's scope, we need to provide a bridge to the original thread inside the application that starts the primary coroutine.
We are able to connect the non-blocking coroutine scope with the blocking world outside using the runBlocking() function. The runBlocking() function starts a new coroutine and blocks the current thread until its completion.
It is designed to bridge regular blocking code to libraries that are written in suspending style.
For example, it can be used in main() functions and in tests.







**See also**

• If you'd like to learn more about the basics of the extension function mechanism, you can take a look at the Extending functionalities of classes recipe in Chapter 2, Expressive Functions and Adjustable Interfaces

###Easy coroutine cancelation

In this recipe, we are going to explore how to implement a coroutine that allows us to cancel its execution.
We are going to create an infinite progress-bar animation running in the console in the background using a coroutine.
Next, after a given delay, we are going to cancel the coroutine and test how the animation behaves.

**Getting ready**

The first step to start working with Kotlin Coroutines is to add a core framework dependency to the project:
```groovy

implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:0.23.3'
```
The preceding code declares the kotlinx-coroutines-core dependency in a Gradle build script, which is used in the sample project (https://github.com/PacktPublishing/Kotlin-Standard-Library-Cookbook).

_How to do it..._

• Implement a suspend function responsible for displaying an infinite progress-bar animation in the console:
```kotlin

private suspend fun `show progress animation`() {
val progressBarLength = 30
var currentPosition = 0
while (true) {
print("\r")
val progressbar = (0 until progressBarLength)
.map { if (it == currentPosition) " " else "░" }
.joinToString("")
print(progressbar)
 
delay(50)
 
if (currentPosition == progressBarLength) {
currentPosition = 0
}
currentPosition++
}
}
```
• Launch the `show progress animation`() function inside a new coroutine:
```kotlin

runBlocking {
val job = launch { `show progress animation`() }
}
```
• Delay the parent thread by five seconds:
```kotlin

runBlocking {
val job = launch { `show progress animation`() }
delay(5000)
}
```
• Cancel the progress-bar animation job:
```kotlin

runBlocking {
val job = launch { `show progress animation`() }
delay(5000)
job.cancel()
println("Cancelled")
}
```
• Wait for the job to complete and log the completion event to the console:
```kotlin

runBlocking {
val job = launch {`show progress animation`()}
delay(5000)
job.cancel()
job.join()
println("\nJob cancelled and completed")
}
```

_How it works..._

In the end, our code is going to display a progress-bar animation for five seconds and then stop it. 
We are scheduling the `show progress animation`() function to run in the background by invoking it inside a new coroutine instance created by the launch() function. 
We are storing a handle to a Job instance returned by the launch() function under the job variable.

Next, we are suspending the outer runBlocking() coroutine scope by five seconds with the delay(5000) call.
Once the delay() function resumes coroutine execution, we call the cancel() function on the coroutine Job responsible for displaying the progress-bar animation. 
Under the hood, inside the `show progress animation`() function, we are running an infinite while loop, which updates the last console line with a new progress-bar animation state every 50 milliseconds. 
However, as you can verify by running the example, the animation stops immediately after the corresponding Job responsible for running it gets canceled, even though, after the cancellation, we invoke the join() function to wait for its completion.
You can also make use of a Job extension function, called cancelAndJoin(), that combines the cancel() and join() calls together. 
However, if you don't want to wait for the actual coroutine stop-event, a simple cancel() call is enough.

**See also**

• If you'd like to explore the basics of the coroutines framework, take a look at the Using coroutines for asynchronous concurrent-tasks execution and Using coroutines for asynchronous concurrent-tasks execution with results-handling recipes

###Building a REST API client with Retrofit and a coroutines' adapter

In this recipe, we are going to explore how to employ coroutines to interact with remote endpoints using REST APIs.
We are going to implement a REST client using the Retrofit library, allowing us to communicate over HTTP with the GitHub API asynchronously.
Finally, we will use it in practice to fetch GitHub repositories search results for a given search query.


**Getting ready**

The first step to start working with Kotlin Coroutines is to add a core framework dependency:
```groovy
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:0.23.3'
```
In order to make use of the Retrofit library with the coroutines adapter plugin, we also need to add the following dependencies to our project:
```groovy

implementation 'com.squareup.retrofit2:retrofit:2.4.0'
implementation 'com.squareup.retrofit2:converter-gson:2.4.0'
implementation 'com.jakewharton.retrofit:retrofit2-kotlin-coroutines-experimental-adapter:1.0.0'
```
The preceding code declares the required dependencies in a Gradle build script, which is used in a sample project (https://github.com/PacktPublishing/Kotlin-Standard-Library-Cookbook).
The retrofit module provides the core Retrofit library implementation. 
converter-gson adds a Gson plugin that enables automatic conversion of the JSON response to Kotlin model-data classes. 
The retrofit2-kotlin-coroutines-experimental-adapter module provides an adapter for async REST calls, allowing us to wrap the response using the Kotlin Coroutines Deferred type.
In this recipe, we are going to use the GitHub REST API, which is available publicly.
We are going to communicate with an endpoint responsible for returning search results containing GitHub repositories for a given search query.
You can find detailed endpoint docs here: https://developer.github.com/v3/search/#search-repositories.
The /search/repositories endpoint allows us to access the remote resources using the GET method and passing the desired search phrase under the key, called q. 
For example, the URL with the GET request for repositories matching the "live.parrot" search phrase would look like this: https://api.github.com/search/repositories?q=parrot.live. 
The results delivered by the endpoint are formatted using the JSON format.
You can check out how the raw response looks like by opening the example URL in your browser or using the curl command-line tool: curl https://api.github.com/search/repositories?q=parrot.live.



_How to do it..._

• Declare data classes modeling the server response:
```kotlin

data class Response(@SerializedName("items")
val list: Collection<Repository>)
data class Repository(val id: Long?,
val name: String?,
val description: String?,
@SerializedName("full_name") val fullName:
String?,
@SerializedName("html_url") val url: String?,
@SerializedName("stargazers_count") val stars:
Long?)
```
• Declare an interface modeling the GitHub endpoint usage:
```kotlin

interface GithubApi {
@GET("/search/repositories")
fun searchRepositories(@Query("q") searchQuery: String):
Deferred<Response>
 
}
```
• Instantiate the GithubApi interface using the Retrofit class:
```kotlin

val api: GithubApi = Retrofit.Builder()
.baseUrl("https://api.github.com/")
.addConverterFactory(GsonConverterFactory.create())
.addCallAdapterFactory(CoroutineCallAdapterFactory())
.build()
.create(GithubApi::class.java)
```
• Make a call to the endpoint using the GithubApi instance and pass "kotlin" as a search phrase:
```kotlin

val api: GithubApi = Retrofit.Builder()
.baseUrl("https://api.github.com/")
.addConverterFactory(GsonConverterFactory.create())
.addCallAdapterFactory(CoroutineCallAdapterFactory())
.build()
.create(GithubApi::class.java)
 
api.searchRepositories("Kotlin")
```
• Wait for the response and get a reference to the obtained list of Repository class objects:
```kotlin

val api: GithubApi = Retrofit.Builder()
.baseUrl("https://api.github.com/")
.addConverterFactory(GsonConverterFactory.create())
.addCallAdapterFactory(CoroutineCallAdapterFactory())
.build()
.create(GithubApi::class.java)
 
val downloadedRepos = api.searchRepositories("Kotlin").await().list
```
• Sort the repositories list by the number of their stars count in decreasing order, and print them to the console:
```kotlin

val api: GithubApi = Retrofit.Builder()
.baseUrl("https://api.github.com/")
.addConverterFactory(GsonConverterFactory.create())
.addCallAdapterFactory(CoroutineCallAdapterFactory())
.build()
.create(GithubApi::class.java)
 
val downloadedRepos = api.searchRepositories("Kotlin").await().list
downloadedRepos
.sortedByDescending { it.stars }
.forEach {
 it.apply {
  println("$fullName$stars\n$description\n$url\n")
 }
}
```

_How it works..._

As a result, our code is going to send a request to the server, fetch and process the response, and print the following results to the console:
```text

JetBrains/kotlin 

23051
The Kotlin Programming Language
https://github.com/JetBrains/kotlin
 
perwendel/spark 

7531
A simple expressive web framework for java. News: Spark now has a kotlin DSL https://github.com/perwendel/spark-kotlin
https://github.com/perwendel/spark
 
KotlinBy/awesome-kotlin 

5098
A curated list of awesome Kotlin related stuff Inspired by awesome-java. 
https://github.com/KotlinBy/awesome-kotlin
 
ReactiveX/RxKotlin 

4413
RxJava bindings for Kotlin
https://github.com/ReactiveX/RxKotlin
 
JetBrains/kotlin-native 

4334
Kotlin/Native infrastructure
https://github.com/JetBrains/kotlin-native
```
 
...
We have started by implementing model classes that represent the data returned in the server's JSON response.
You may have seen that some of the properties are marked with the @SerializedName() annotation. 
The aim of this annotation is to indicate to the Gson library that the specified property should be deserialized from a JSON field which name matches the value passed to @SerializedName(). 

**Next**, we are declaring an interface, GithubApi, that represents the methods we want to use to communicate with the endpoint. 
We've declared a single method, called searchRepositories, which takes a String parameter that corresponds to the search-query value required by the repositories search endpoint.
We've also marked the searchRepositories method with the @GET annotation, which specifies the REST method type to use and a path to the endpoint.
The searchRepositories method should return an instance of a Deferred<Response> type, representing a future result of an asynchronous call.
Implementation of the GithubApi interface is generated by the Retrofit library internally. 
In order to obtain the GithubApi instance, we need to instantiate the Retrofit type and configure it with the endpoint's URL address and mechanisms responsible for JSON deserializing and performing asynchronous calls to the server. 

**Finally**, we call Retrofit.create(GithubApi::class.java) to obtain the GithubApi instance. That's it!
In order to execute the actual call to the server, we need to call the GithubApi.searchRepositories() function:
api.searchRepositories("Kotlin")

**Next**, in order to obtain a list of Repository objects from the response, we need to wait for the completion of the async call to the server and response parsing:
```kotlin

val downloadedRepos = api.searchRepositories("Kotlin").await().list
```

Finally, we post-process the list of repositories obtained from the response. We are sorting it by the stars count, in decreasing order, and printing it to the console with the following code:
```kotlin

val downloadedRepos = api.searchRepositories("Kotlin").await().list
downloadedRepos
.sortedByDescending { it.stars }
.forEach {
 it.apply {
  println("$fullName$stars\n$description\n$url\n")
 }
}
```

**See also**

• If you'd like to explore the basics of the coroutines framework, take a look at the Using coroutines for asynchronous concurrent-tasks execution and **Using coroutines for asynchronous, concurrent tasks execution with results handling** recipes. 
You can learn more about the Retrofit library by exploring its homepage, http://square.github.io/retrofit/, which contains useful examples.

###Wrapping third-party callback-style APIs with coroutines

Often third-party libraries offer callback-style asynchronous APIs. 
However, the callback functions are considered to be an anti-pattern, especially whenever we are dealing with a number of nested callbacks.
In this recipe, we are going to learn how to deal with libraries that provide callback-style methods by transforming them easily into suspending functions that can be run using coroutines.

**Getting ready**

The first step to start working with Kotlin Coroutines is to add the core framework dependency to the project:
```groovy

implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:0.23.3'
```
The preceding code declares the kotlinx-coroutines-core dependency in a Gradle build script, which is used in the sample project (https://github.com/PacktPublishing/Kotlin-Standard-Library-Cookbook). 
As far as the recipe task is concerned, let's assume we have a class called Result, defined as follows:
```kotlin
data class Result(val displayName: String)
```
Here is the getResultsAsync() function, which simulates the third-party callback-style API:
```kotlin
fun getResultsAsync(callback: (List<Result>) ->Unit) =
thread {
val results = mutableListOf<Result>()
 
// Simulate some extensive bacground task
Thread.sleep(1000)
 
results.add(Result("a"))
results.add(Result("b"))
results.add(Result("c"))
 
callback(results)
}
```
The getResultsAsync() function just starts a background thread, delays it for a second, and invokes a callback function passed to it as an argument delivering the list of the Result class object to it.

_How to do it..._

• Wrap the getResultsAsync() function with the suspend function, returning the results directly:
```kotlin

suspend fun getResults(): List<Result>=
suspendCoroutine { continuation: Continuation<List<Result>>->
getResultsAsync { continuation.resume(it) }
}
```

• Start a coroutine and invoke the getResults() suspending function inside it:
```kotlin

val asyncResults = async {
getResults()
}
```
• Wait for the results and print them to the console:
```kotlin

val asyncResults = async {
getResults()
}
 
println("getResults() is running in bacground. KotlinTest thread is not blocked.")
asyncResults.await().map { println(it.displayName) }
println("getResults() completed")

```
_How it works..._

In the end, our code is going to print the following output to the console:
```text

getResults() is running in background. 
KotlinTest thread is not blocked.
a
b
c
getResults() completed
Total time elapsed: 1029 ms
```

We've successfully managed to transform the callback-style getResultsAsync(callback: (List<Result>) -> Unit) function into the clean form of a suspending function returning the results directly–suspend fun getResults(): List<Result>.
In order to get rid of the original callback argument, we have used the suspendCoroutine() function provided by the standard library. 
The suspendCoroutine() function takes the block: (Continuation<T>) -> Unit function type as an argument. The Continuation interface is designed to allow us to resume the coroutine paused by a suspending function.


When the suspendCoroutine function is called inside a coroutine, it captures its execution state in a Continuation instance and passes this continuation to the specified block as an argument. To resume execution of the coroutine, the block may invoke either continuation.resume() or continuation.resumeWithException().
We invoke the original getResultsAcync() function inside the lambda passed to the suspendCoroutine() function, and we call the continuation.resume(it) function in the callback lambda blocked passed to the getResultsAsync() function as an argument:
```kotlin

suspend fun getResults(): List<Result>=
suspendCoroutine { continuation: Continuation<List<Result>>->
getResultsAsync { continuation.resume(it) }
}
```
As the result, the coroutine inside which getResults() is called will become suspended until the callback lambda is executed internally in the getResultsAsync() function.

**See also**

• If you'd like to explore the basics of the coroutines framework, take a look at the Using coroutines for asynchronous, concurrent tasks execution and **Using coroutines for asynchronous, concurrent tasks execution with results handling** recipes
