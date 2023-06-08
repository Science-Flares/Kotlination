##Friendly I/O Operations

In this chapter, we will cover the following recipes:

• Reading the contents of a file

• Ensuring stream closing with the use function

• Reading the contents of a file line by line

• Writing the contents to a file

• Appending a file

• Easy files copying

• Traversing files in a directory


###Introduction

This chapter focuses on explaining the Kotlin approach to working with **JVM** File, `InputStream`, and `OutputStream` types. 
We are going to explore the group of extension functions offered by the standard library under the `kotlin.io` package, which focus on enhancing support for **I/O** operations.
Note that at the moment, with the **Kotlin version 1.2**, the following recipes are applicable only to code targeting the **JVM** platform. 

###Reading the contents of a file

In this recipe, we are going to retrieve the contents of a file as text and print it to the console.
We are going to use the standard library `File.readText()` extension function, returning a String representing the text content of the given `File` instance. 


**Getting ready**

Make sure you have a sample non-empty file included in your project to read its contents. 
You can clone the sample project provided with the book's **GitHub** repository: https://github.com/PacktPublishing/Kotlin-Standard-Library-Cookbook.
In this recipe, we are going to use the `file1.txt` file located in the src/main/resources directory in the sample project. 

_How to do it..._

• Import the `File.separator` constant and assign an alias to it:
```kotlin
import java.io.File.separator as SEPARATOR
```
• Declare a String storing a path to the file we are going to read:
```kotlin
val filePahtName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}file1.txt"
```
• Instantiate a `File` using the specified path:
```kotlin
val filePahtName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}file1.txt"
val file = File(filePahtName)
```
• Read the text from the file and print it to the console:
```kotlin
val filePahtName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}file1.txt"
val file = File(filePahtName)
val fileText: String = file.readText()
println(fileText)
```

_How it works..._

The `readText()` extension function is returning the String value representing the text of the given file.
This is a convenient way of reading the file contents since it wraps the **low-level** logic of reading bytes from the `FileInputStream` class. 
Under the hood, before reading the bytes of the file, the function checks whether the file has the proper size to be stored in memory.
Keep in mind that, if the file size is too large, `OutOfMemoryError` is thrown. 
Whenever the file is too big to be processed at once, you should access its content using `BufferedReader`. 
You can easily obtain the BufferedReader instance by calling the `File.bufferedReader()` extension function.
The `readText()` function can also take the `charset: Charset` argument, which by default is set to the `Charsets.UTF_8` value. 
If you'd like to use another charset, you can specify it by passing a proper one as the charset argument.
You can find the available charset types inside the `kotlin.text.Charsets` object.
You can also find them listed in the official documentation: https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-charsets.
You may have noticed we are using the `File.separator` constant instead of the hardcoded `/` char. 
Thanks to that, we can be sure the correct directory-separating character will be used on different platforms.
For the sake of brevity, you can import `File.separator` with an alias, for example import `java.io.File.separator` as separator.

**See also**

• You can also check out the **Reading the contents of a file** line by line recipe, which explains how to read the text content of a file line by line effectively

###Ensuring stream closing with the use function

Whenever we are accessing the contents of a **File** via `FileInputStream` or `FileOutputStream`, we should remember to close them once we've finished working on the file.
Unclosed streams may lead to memory leaks and a significant decrease in performance. 
In this recipe, we are going to explore how to employ the `use()` extension function offered by the standard library under the `kotlin.io` package for automatic stream closing.

**Getting ready**

Make sure you have a sample non-empty file included in your project to read its contents. 
You can clone the sample project provided with the book's **GitHub** repository: https://github.com/PacktPublishing/Kotlin-Standard-Library-Cookbook. 
In this recipe, we are going to use the `file1.txt` file located in the src/main/resources directory in the sample project.

_How to do it..._

•  Import the `File.separator` constant and assign an alias to it:
```kotlin
import java.io.File.separator as SEPARATOR
```
• Declare a String storing a path to the file we are going to read:
```kotlin
val filePahtName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}file1.txt"
```
• Instantiate a `FileInputStream` for the `file1.txt` file:
```kotlin
val filePahtName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}file1.txt"
val stream = File(filePahtName).inputStream()
```
• Read the bytes from the stream inside the use() function:
```kotlin
val fileName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}file1.txt"
val stream = File(fileName).inputStream()
stream.use {
    it.readBytes().also { println(String(it)) }
}
```

_How it works..._

First, we create the `FileInputStream` instance using the `File.inputStream()` extension function.
Next, we invoke the `use()` extension function on our stream instance, passing a lambda block containing operations we want to perform on the stream as the argument.


Under the hood, after invoking the lambda expression, the `use()` function calls the `close()` function on the stream variable.
We can check that, when we try to access the file using the stream variable once again, we will get a `java.io.IOException: Stream` Closed exception thrown. 
The `use()` function extends any type that implements the Closeable interface. 
It takes a lambda block as the argument, passing an instance of the closeable resource to the lambda as the parameter. 
The use function returns the value returned by the lambda block.
Under the hood, there is a `try…catch` block being used where the `close()` function of the resource is invoked inside the finally block.

###Reading the contents of a file line by line

In this recipe, we are going to retrieve the contents of a file as a set of consecutive text lines.
We are going to use the standard library extension function, `File.readLines()`, to return a `List` of a `String` type representing the next lines of the given `File` instance.

**Getting ready**

Make sure you have a sample non-empty file included in your project to read its contents.
You can clone the sample project provided with the book at the **GitHub** repository: https://github.com/PacktPublishing/Kotlin-Standard-Library-Cookbook.
In this recipe, we are going to use the `file1.txt` file located in the src/main/resources directory in the sample project.

_How to do it..._

• Import the File.separator constant and assign an alias to it:
```kotlin
import java.io.File.separator as SEPARATOR
```
• Declare a String storing a path to the file we are going to read:
```kotlin
val filePahtName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}file1.txt"
```
• Instantiate a File using the specified path:
```kotlin
val filePahtName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}file1.txt"
val file = File(filePahtName)
```
• Read the text from the file and print it to the console:
```kotlin
val filePathName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}file1.txt"
val file = File(fileName)
val fileLines = file.readLines()
fileLines.forEach { println(it) }
```

_How it works..._

The `readLines()` extension function returns the `List<String>` instance representing the lines of text of the given file. 
This is a convenient way of reading the file contents since it wraps the **low-level** logic of reading bytes from the `FileInputStream` class.
Keep in mind that, if the file size is too large, `OutOfMemoryError` is thrown. 
Whenever the file is too big to be processed at once, you should access its content using `BufferedReader`.
You can easily obtain the BufferedReader instance by calling the `File.bufferedReader()` extension function.
The `readLines()` function can also take the `charset: Charset` argument, which by default is set to the `Charsets.UTF_8` value.
If you'd like to use another charset, you can specify it by passing a proper one as the charset argument. 
You can find the available charset types inside the `kotlin.text.Charsets` object. 
You can also find them listed in the official documentation: https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-charsets.
You may have noticed we are using the `File.separator` constant instead of the hardcoded `"/"` char. 
Thanks to that, we can be sure the correct directory-separating character will be used on different platforms. For the sake of brevity, you can import `File.separator` with an alias, for example import java.io.File.separator as separator.

**See also**

• You can also check out the **Reading the contents of a file** recipe, which explains how to retrieve the text contents of the file at once as the String value

###Writing the contents to a file

In this recipe, we are going to learn how to easily create a new `File` and write text to it.
We are going to use the `File.writeText()` extension function offered by the standard library.
Then, we are going to verify whether the file was successfully created and whether it contains the proper contents by printing it to the console.

_How to do it..._

• Import the File.separator constant and assign an alias to it:
```kotlin
import java.io.File.separator as SEPARATOR
```
• Specify the path to the new file we are going to create:
```kotlin
val fileName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}temp_file"
```
• Instantiate the file using the specified file path:
```kotlin
val fileName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}temp_file"
val file = File(fileName)
```
• Write the text to the file using the `writeText()` function inside the `apply` block:
```kotlin
val fileName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}temp_file"
val file = File(fileName)
file.apply {
    val text =
        "\"No one in the brief history of computing " +
                "has ever written a piece of perfect software. " +
                "It's unlikely that you'll be the first.\"- Andy Hunt"
    writeText(text)
}
```
• Print the contents of `temp_file` to the console:
```kotlin
val fileName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}temp_file"
val file = File(fileName)
file.apply {
    val text =
        "\"No one in the brief history of computing " +
                "has ever written a piece of perfect software. " +
                "It's unlikely that you'll be the first.\"- Andy Hunt"
    writeText(text)
}
file.readText().apply { println(this) }
```

_How it works..._

As the result of executing the preceding code, a new `temp_file file` is going to be created under the src/main/resources directory. 
Keep in mind that in case the `temp_file` already exists it is going to be overridden. 
Next, with the help of the `writeText()` function its contents are going to be printed to the console:
```text
"No one in the brief history of computing has ever written a piece of perfect software. 
It's unlikely that you'll be the first." - Andy Hunt
```

The `writeText()` function wraps the `java.io.FileOutputStream` API, providing a neat way of writing content to the file. 
Under the hood, it accesses `FileOutputStream` inside the `use()` function, so you can be sure that it autocloses any streams that are opened during the write operation.
If the text you want to write to the file is too large to be processed at once, you can use the `BufferedWriter` API to allow you to write and append the file. 
You can easily obtain an instance of `BufferedWriter` using the `File.bufferedWriter()` extension function.

You can also pass the additional `charset: Charset` argument to `writeText()`, which by default is equal to the `Charsets.UTF_8` value. 
If you'd like to use another charset, you can specify it by passing a proper one as the charset argument. 
You can find the available charset types inside the `kotlin.text.Charsets` object.
You can also find them listed in the official documentation at https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-charsets.

**See also**

• Check out the **Appending a file** recipe to learn how to modify a file's content in a flexible way by appending it

###Appending a file

In this recipe, we are going to learn how to easily create a new `File` and write text to it by appending its content a number of times.
We are going to use the `File.appendText()` extension function offered by the standard library.
Then, we are going to verify whether the file was successfully created and whether it contains the proper content by printing it to the console.

_How to do it..._

• Import the `File.separator` constant and assign an alias to it:
```kotlin
import java.io.File.separator as SEPARATOR
```
• Specify the path to the new file we are going to create:
```kotlin
val fileName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}temp_file"
```
• Instantiate the file using the specified file path:
```kotlin
val fileName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}temp_file"
val file = File(fileName)
```
• Delete the file if it already exists:
```kotlin
val fileName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}temp_file"
val file = File(fileName)
if (file.exists()) file.delete()
```
• Append the file with the next `String` values:
```kotlin
val fileName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}temp_file"
val file = File(fileName)
if (file.exists()) file.delete()
 
file.apply {
    appendText("\"A language that doesn't affect the way you think ")
    appendText("about programming ")
    appendText("is worth knowing.\"")
    appendText("\n")
    appendBytes("Alan Perlis".toByteArray())
}
```
• Print the file's contents to the console:
```kotlin
val fileName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}temp_file"
val file = File(fileName)
if (file.exists()) file.delete()
 
file.apply {
    appendText("\"A language that doesn’t affect the way you think ")
    appendText("about programming ")
    appendText("is worth knowing.\"")
    appendText("\n")
    appendBytes("Alan Perlis".toByteArray())
}
 
file.readText().let { println(it) }
```
_How it works..._

As a result of executing the preceding code, a new `temp_file file` is going to be created under the src/main/resources directory, and its content is going to be printed out to the console:
```text
"A language that doesn’t affect the way you think about programming is worth knowing."
Alan Perlis
```
The `appendText()` and `appendBytes()` functions wrap the `java.io.FileOutputStream` API, providing a neat way of appending content to the file.
Under the hood, they access `FileOutputStream` inside the `use()` function, so you can be sure that it autocloses any streams that are opened during the write operation. 
If the text you want to write to the file is too large to be processed at once, you can use the `BufferedWriter` **API**, which allows you to write and append the file. 
You can easily obtain an instance of `BufferedWriter` using the `File.bufferedWriter()` extension function.
You can also pass the additional `charset: Charset` argument to the `appendText()` function, which by default is equal to the `Charsets.UTF_8` value. 
If you'd like to use another charset, you can specify it by passing a proper one as the charset argument.
You can find the available charset types inside the `kotlin.text.Charsets` object.
You can also find them listed in the official documentation at https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.text/-charsets.

###Easy file copying

In this recipe, we are going to explore a neat way of copying a file's contents into a new file.
We are going to obtain a sample `File` instance from the specified path and copy its content into the new file. 
Finally, we are going to print the contents of both files to the console to verify the operation.



**Getting ready**

Make sure you have a sample non-empty file included in your project to read its contents. 
You can clone the sample project provided with the book at the **GitHub** repository: https://github.com/PacktPublishing/Kotlin-Standard-Library-Cookbook. 
In this recipe, we are going to use the `file2.txt` file located in the src/main/resources directory in the sample project. 

_How to do it..._

• Import the `File.separator` constant and assign an alias to it:
```kotlin
import java.io.File.separator as SEPARATOR
```
• Instantiate a `File` instance for the specified `file2.txt` path:
```kotlin
val sourceFileName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}file2.txt"
val sourceFile = File(sourceFileName)
```
• Create a new `File` called `file2_copy.txt`:
```kotlin
val sourceFileName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}file2.txt"
val sourceFile = File(sourceFileName)
 
val targetFileName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}file2_copy.txt"
val targetFile = File(targetFileName)
```
• If `file2_copy.txt` exists, delete it:
```kotlin
val sourceFileName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}file2.txt"
val sourceFile = File(sourceFileName)
 
val targetFileName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}file2_copy.txt"
val targetFile = File(targetFileName)
 
if (targetFile.exists()) targetFile.delete()
```

• Copy the contents of `file2.txt` to the `file2_copy.txt` file:
```kotlin
val sourceFileName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}file2.txt"
val sourceFile = File(sourceFileName)
 
val targetFileName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}file2_copy.txt"
val targetFile = File(targetFileName)
 
if (targetFile.exists()) targetFile.delete()
 
sourceFile.copyTo(targetFile)
```
• Print both files to the console for verification:
```kotlin
val sourceFileName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}file2.txt"
val sourceFile = File(sourceFileName)
 
val targetFileName = "src${SEPARATOR}main${SEPARATOR}resources${SEPARATOR}file2_copy.txt"
val targetFile = File(targetFileName)
 
if (targetFile.exists()) targetFile.delete()
 
sourceFile.copyTo(targetFile)
 
File(sourceFileName).readText().apply { println(this) }
File(targetFileName).readText().apply { println(this) }
```

_How it works..._

You can run the sample code to verify that, after invoking the `copyTo()` extension function, both files contain the same text content.
In our case, we get the following output:
```text
"Testing can show the presence of errors, but not their absence." - E. W. Dijkstra
"Testing can show the presence of errors, but not their absence." - E. W. Dijkstra
```

Under the hood, the `copyTo()` function reads InputStream in the source file to the buffer and writes it to the `OutputStream` target file. 
Internally, streams are being accessed inside the `use()` function block, which closes them automatically after the operation finishes.
Apart from the target `File` instance, the `copyTo()` function takes two optional `parameters—overwrite: Boolean`, which is set to false by default, and `bufferSize: Int`, which is assigned to the default value. 
Keep in mind that, whenever some directories on a way to the target file are missing, they will be created. 
Also, if the target file already exists, the `copyTo()` function will fail, unless the override argument is set to true. 

• When the overwrite parameter is set to true and target points to a directory, it will be replaced only if it is empty. 

• If you invoke `copyTo()` on a File instance that points to a directory, it will be copied without its content.
Only an empty directory will be created under the target path. 

• The `copyTo()` function doesn't preserve copied file attributes, that is, the creation/modification date and permissions.

###Traversing files in a directory

In this recipe, we are going to explore how to traverse files in a given directory.
We are going to obtain a `FileTreeWalk` class instance from a given `File` pointing to the directory.
We are going to iterate through all the files inside the given directory, including any nested subdirectories. 
We will also filter the files to exclude those without the `.txt` extension and print their paths and contents to the console.

**Getting ready**

Make sure you have the sample files with the `.txt` extension included in your project. 
You can **clone** the sample project provided with the book at the **GitHub** repository: https://github.com/PacktPublishing/Kotlin-Standard-Library-Cookbook.
In this recipe, we are going to use the src/main/resources directory and its contents from the sample project.


_How to do it..._

• Import the `File.separator` constant and assign an alias to it:
```kotlin
import java.io.File.separator as SEPARATOR
```
• Obtain the `FileTreeWalk` instance from the `File` pointing to the src/main/resources directory:
```kotlin
val directoryPath = "src${SEPARATOR}main${SEPARATOR}resources"
val fileTreeWalk: FileTreeWalk = File(directoryPath).walk()
```
• Iterate through the all non-empty `.txt` files and print:
```kotlin
val directoryPath = "src${SEPARATOR}main${SEPARATOR}resources"
 
val fileTreeWalk: FileTreeWalk = File(directoryPath).walk()
fileTreeWalk
.filter { it.isFile }
.filter { it.extension == "txt" }
.filter { it.readBytes().isNotEmpty() }
.forEach {
    it.apply {
        println(path)
        println(readText())
        println()
    }
}
```

_How it works..._

We start by instantiating the `File` type instance that references the src/main/resources directory and invoking the `walk()` function on it.
`walk()` returns the `FileTreeWalk` instance, which is a **high-level** abstraction layer over the filesystem and allows us to iterate through the files and subdirectories of the original `File` object.
`FileTreeWalk` extends a `s.Sequence<File>` interface and provides an `Iterator<File>` implementation, which allows us to iterate through the files and apply any transforming operations to them, in the same way we do while working with collections. 


**Next**, we apply a few filtering operations—removing the `File` objects referencing directories, removing files that don't contain the `.txt` extension, and removing any empty files from the sequence.

**Finally**, we use the `forEach()` function to print the paths of the consecutive files together with their contents.
As you can observe, the default order provided by the `FileTreeWalk` sequence is from top to bottom.
We can define a reversed sequence by calling the `walk()` function with a direction parameter set to `FileWalkDirection.BOTTOM_UP`.
There are also two out-of-the-box specialized variants of the `walk()` function `available—File.walkTopDown()` and `File.walkBottomUp()`.
The first one returns the `FileTreeWalk` instance with the direction property set to `FileWalkDirection.TOP_DOWN`, and the second one sets direction to `FileWalkDirection.BOTTOM_UP`.
