## Theory: Debugging simple constructs

In this topic, we are going to learn how to use a debugger to test and debug simple constructs like `if` statements and loops. Of course, the functionality that we'll cover is not limited to just loops: you will be able to apply these tools to solve a wide range of problems.

##### If statements

First of all, we might want to test if the program visits a branch of an `if` statement at all.

It happens from time to time that we assume the boolean expression in an `if` statement to evaluate to `true`, and for some reason get unexpected results. In such a situation, it is sometimes useful to test if the code inside `if` is really executed.

Let's take a look at the following simple program:

```kotlin
fun whatever() {
    var greeting = "Hello world"
    if (greeting.contains("e")) {
        greeting += "!"
        println(greeting)
    }
    println("Shutting down")
}
```

In order to find out if the code inside `if` gets executed, do the following:

1. Click the gutter against the first line in the `if` block. A red circle appears: this is a line breakpoint. It will tell the debugger to suspend the program execution whenever this line is executed.
   ![img](https://ucarecdn.com/8bf49ac6-8846-4051-bc79-0d9beb2925ca/)
2. Click the **Debug** button that is located next to **Run** and **Build Configurations**. Apart from building, installing, and running the app (like **Run**), **Debug** will also attach a debugger.
   ![img](https://ucarecdn.com/ac07d0b1-1de7-4aaa-8cc3-0dedbae692c5/)
   If your app is already running, you have another option: to attach a debugger to the running process without restarting.
   ![img](https://ucarecdn.com/4c9fef5b-2755-4094-8468-272f34cf29d2/)
3. A checkmark appears on top of the breakpoint circle. This means that the program has loaded and instrumented the corresponding class successfully. Thus, such a breakpoint is valid and the program can reach it.
4. The line with the breakpoint turns blue. It means that the program was just about to execute it, but it was suspended and now it waits for commands from you.
   ![img](https://ucarecdn.com/a8090bdd-f184-4094-ac15-883058b66231/)
   The suspended state allows you to examine the variables, control the execution step-by-step, and even alter its outcome. This is, however, a subject for further topics. For now, we've just made sure that the program has executed the `if` in question. If it hadn't, then we wouldn't have stopped at this line, because the program would've never reached the line with the breakpoint.
   Let's resume the program so that it can execute the remaining statements and terminate. You can do this by pressing **Resume** in the **Debug** tool window.
   ![img](https://ucarecdn.com/b99170b3-1ac8-4d70-a1d1-86610b499170/)

You can use this technique to debug any code, not only `if` statements. For example, if you want to test if a certain method gets executed, just put a breakpoint to see if you stop there. If you don't, then you know that the program hasn't executed this line and hasn't called the method.

This might seem very simple, and you may ask why we need a debugger for that. The answer is that in real life you sometimes have to deal with very convoluted code that leads to subtle bugs. This is where even the most basic debugger features like this one can save you a lot of time.

##### Loops

Let's learn how we can debug loops. In this example, we are going one step further and will add some breakpoint conditions and examine the state of the program. Sounds thrilling, doesn't it?

Just like with `if` statements, you can set a breakpoint inside a loop, and the program will suspend it each time it executes the line, that is, with each iteration of the loop. While this may still be useful for short loops, the problem arises when a loop is going to iterate, say, 1000 times, and we are only interested in some specific iteration. It would take a very determined person to continuously resume a program 1000 times. We have a better way, though.

Here's our example:

```kotlin
fun whatever() {
    val rangeStart = 'C'
    val rangeEnd = 'Y'
    val findLetter = 'Q'
    for (c in rangeStart..rangeEnd) {
        if (c == findLetter) {
            System.out.printf(
                "Character %s is within range %s-%s",
                findLetter, rangeStart, rangeEnd
            )
            return
        }
    }
    System.out.printf(
        "Character %s is within range %s-%s",
        findLetter, rangeStart, rangeEnd
    )
}
```

Let's say we are interested in the iteration that tests the character `H`:

1. As in the previous example, set a breakpoint somewhere in the loop.
   ![img](https://ucarecdn.com/a29cddaf-579f-441e-9d94-2f013c1b4d52/)
2. Right-click the breakpoint, and specify the condition: `c == 'H'`. The condition is just a boolean expression that must evaluate to `true` in order for the program to stop at this breakpoint. Note that the program evaluates the condition in the context of the code that the breakpoint is set in. For example, the condition `c == 'H'` will not work for a breakpoint outside the loop, because the variable `c` is not visible there.
   ![img](https://ucarecdn.com/e21a7d93-dc88-43a6-ac47-1b81d7cbded5/)
3. Start debugging by either clicking Debug or attaching a debugger to the existing process.
4. The program stops in the loop. In contrast with the previous example, the debugger has not suspended the program every time it's hit the breakpoint. We didn't stop until the loop was checking the letter `H`.
   ![img](https://ucarecdn.com/553d869d-6e4c-4a26-b7de-b9516ccb8864/)
5. Let's examine the variables in the **Debug** tool window. Its purpose is to show you information related to the current state of the program and suggest a way to interact with it. For now, let's look at the **Variables** tab. It provides the information on all variables accessible in the current context. Here, you can see the values of `rangeStart`, `rangeEnd` and `findLetter`, as well as the loop's local variable `c`.
6. **Resume** the application, letting it run further.

##### Conclusion

We have started to scratch the surface by debugging some basic examples, yet it's enough to solve some of the real-world problems.

Speaking of problems, the knowledge you've just gained can help you solve some of the tasks from other topics times faster than it would take you if you didn't use a debugger. For example, when they ask you what the contents of the array will be after the n-th iteration of a sorting loop: now, with the help of a debugger, you have a simpler way of solving this.

As we have already mentioned, the debugger can be used in various ways, not necessarily related to finding bugs. After you gain some experience, it will surely become one of your most indispensable tools.

See you in further topics!