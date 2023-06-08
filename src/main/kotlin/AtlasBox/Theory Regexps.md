## Theory: Regexps

Have you ever had to find and replace something specific in a long text? Or maybe you have looked for a contact in a phone book? Perhaps, you have even had experience solving searching or comparison problems with strings. Well, even if you haven't, you will probably encounter situations like this in the future. Knowing how to work with **regular expressions**, or **regex**, will be a big help.

You are already familiar with the basics of regex language. In this topic, we are going to see how regular expressions are represented in Kotlin.

##### Creating a regular expression

Let's look at two possible ways to make a regex instance in Kotlin.

The first way is creating a `String` instance and then calling the method `toRegex()`, which will make a regex out of that string:

```kotlin
val string = "cat" // creating the "cat" string
val regex = string.toRegex() // creating the "cat" regex
```

Another way is calling the `Regex` constructor:

```kotlin
val regex = Regex("cat") // creating a "cat" regex
```

The result will be the same: we’ll get the required regular expression. For now, we can assume that these options are equally good, and choosing one over the other is a matter of personal preference.

##### Simple matching

Now let’s see how we can actually use regexes. The first method we will consider is `matches()`. It is used for finding a **full** match, that is, the whole string has to match the pattern.

Take a look at the signature: `fun String.matches(regex: String): Boolean`. As you see, it takes a regex, calls the string that will be matched against the regular expression, and returns the boolean result.

If you want more example, check out the following code snippet:

```kotlin
val regex = Regex("cat") // creating the "cat" regex
    
val stringCat = "cat"
val stringDog = "dog"
val stringCats = "cats"

println(stringCat.matches(regex))   // true
println(stringDog.matches(regex))   // false
println(stringCats.matches(regex))  // false
```

As you can see in the example above, when our regex is just a sequence of simple characters, the result will be true only when the string matches the regex perfectly.

This might seem a little excessive: after all, we could simply compare two strings, which would be faster and easier. This is true, but remember that the true power of regular expressions is the option to use special characters that can define a certain pattern. This pattern can be matched against multiple strings at once. Let's now move on to some more interesting cases.

##### The dot character

This special character is already familiar to you: the dot `.` matches any single character including letters, digits, spaces, and so on. The only character it cannot match is the newline character `\n`. Other control characters such as `\b` and `\t` will still match.

Consider the following examples of using the dot:

```kotlin
val regex = Regex("cat.") // creating the "cat." regex

val stringCat = "cat."
val stringEmotionalCat = "cat!"
val stringCatWithSpace = "cat "
val stringCatN = "cat\n"

println(stringCat.matches(regex))   // true
println(stringEmotionalCat.matches(regex))   // true
println(stringCatWithSpace.matches(regex))  // true
println(stringCatN.matches(regex))  //false
```

It's not complicated but certainly useful. For example, it can come in handy when you need to find the same word that appears in the text in different forms.

##### The question mark

The question mark `?` is a special character that denotes **optionality**. It means “the preceding character or nothing”.

The following example illustrates how it works:

```kotlin
val regex = Regex("cats?") // creating the "cats?" regex

val stringCat = "cat"
val stringManyCats = "cats"

println(stringCat.matches(regex))   // true
println(stringManyCats.matches(regex))   // true
```

You can also combine the dot character `.` and the question mark `?` in one regex pattern. This combination basically means that there can be any single character or no character at all:

```kotlin
val regex = Regex("cat.?") // creating the "cat.?" regex

val stringCat = "cat"
val stringManyCats = "cats"
val stringEmotionalCat = "cat!"
val stringCot = "cot"

println(stringCat.matches(regex))   // true
println(stringManyCats.matches(regex))   // true
println(stringEmotionalCat.matches(regex))  // true
println(stringCot.matches(regex))   // false
```

This can make your work much easier, but wait a second: what if you need to find the actual dot or the question mark?

##### The escape character

If part of the actual regex happens to be a special character, this can be managed with the escape character. When you create strings with special characters, you can escape them using a double backslash `\\`:

```kotlin
val regex = Regex("cats\\?") 

val stringCat = "cat"
val stringManyCats = "cats"
val stringEmotionalCats = "cats?"

println(stringCat.matches(regex))   // false
println(stringManyCats.matches(regex))   // false
println(stringEmotionalCats.matches(regex))  // true
```

In the example above, the question mark was interpreted as a question mark and nothing more.

So, if you need to find a special character such as a dot or a question mark, you can escape it with `\\`. The characters on which you used the escape symbol `\\` will not be interpreted as special.

##### Conclusion

Regular expressions are a powerful and necessary tool for working with strings in Kotlin. Now you can create `Regex` instances and use the `matches()` function that checks for a full match. Remember the special characters you can use:

- The dot `.` matches any single character.
- The question mark `?` means “the preceding character or nothing".
- Double backslash `\\` is an escape symbol that helps you turn a special character into ordinary part of your regex.

Confidence in using regexes will prove very useful in the future. Kotlin regular expressions have many more interesting possibilities and complex cases: we will consider them in the future topics.