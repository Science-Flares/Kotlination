## Theory: What is a bug

A **software bug** is a problem that causes a program to crash or produce invalid output. There are many reasons for software bugs; the most common of them are human mistakes in software design, coding, or understanding of the requirements.

A program is usually called **stable** if it doesn't have a lot of obvious bugs. If the program has a large number of bugs that affect the functionality and cause incorrect results, it is considered **buggy** or **unstable**. The user cannot successfully interact with such software. It is important to find bugs **before** users start interacting with your program.

##### Bug etymology

You've probably already heard the term "bug" and can imagine what it means. Here is a little story about this.

The first computer bug was discovered by Grace Murray at Harvard University in 1947, while she was working on Mark II computer. During the investigation of an issue, an actual moth was found between the contact of the relays. The moth was removed and added to the logbook, which now is located at the Smithsonian Institution's National Museum of American History in Washington, D.C.

So, computer errors are often called bugs. This term is used to describe any incorrect program behavior until the specific nature of the error is determined.

![img](https://ucarecdn.com/0bd7a5c3-0cd4-4c6a-86f1-6e2a35ac84af/)



Some Internet sources give a different story of this term. You can google it if you are interested. Let us know if you find a story most reliable to you.



##### Why do programs have bugs?

Developers often say that a program without bugs doesn't exist. There are some common reasons for bugs in software:

- communication issues in the team;
- misunderstanding of the requirements;
- software complexity;
- programming errors (programmers, like anyone else, can make mistakes);
- time pressure;
- use of unfamiliar technologies;
- an error in a third-party library (yes, that happens too).

During this course, you will mainly encounter bugs caused by misunderstanding of the given requirements or programming errors.

##### Avoiding bugs

It is almost impossible to avoid all bugs in a large program, but it is possible to reduce their number. Here we give you five steps that can help to do that in both educational courses and industrial programming.

1. **Make sure you know what to do.** As a programmer, you need to understand the requirements of a program that you are going to work on. If you have doubts, you can always find some help on the Internet or among fellow developers.
2. **Decompose a program** into small units that are easy to look through and understand. A good architecture reduces software complexity, and, as a consequence, the number of errors.
3. **Write easy-to-read-code** and follow all the standards of the language. It will also allow you to make fewer errors.
4. **Run the program** with boundary input values. Do not forget to consider different cases: 0 or a huge number as an input value, 0 or 1 element as an input sequence. Such cases often reveal bugs.
5. **Write automated tests** that will check the program at the build time.

We will not discuss automated tests in this topic, but we will return to that later. At this moment, you can simply create a set of input values and run the program manually (as it was described in step 4).

##### Debugging

Suppose you know that your program does not work correctly for some input values. To fix this bug, you need to find it in the code and then make some changes.

To locate a buggy place, you can:

- read the code and try to understand what it does for the input values;
- start the debugger and see the current values of variables and the control flow of the program;
- print the current state of the program in critical parts of the code (logging) and then analyze it.

The combination of the approaches above will allow you to find most of the bugs in your program.

##### Conclusion

Let's sum this topic up. A bug is the incorrect behavior of a program. However, there are ways to reduce the number of bugs. For example, understanding the requirements of the program, following the standards of the language, building a clear-cut architecture of the program, checking the program with boundary values, and using automated tests.

If you still happen to have a bug, first of all, you need to locate it in the code. For that, you can use a debugger or logging, or try to understand what the code does with input values by reading it. Use these tips, and, most likely, you will avoid writing buggy programs and spending time debugging them.