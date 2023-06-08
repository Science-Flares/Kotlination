## Theory: Regexps in use

You already know quite a lot about **regular expressions**. In this topic, we will take a broader look at how they are used, particularly, with the `split` and `replace` functions.

Using regexps in these methods allows us to describe a wider group of text elements. You will appreciate the efficiency of this approach!

##### Regexps and split()

You are already familiar with `split()` from the topics in which we discussed string processing. Let's quickly recap what it is.

This method is used to split a string into parts according to a certain principle: the argument of `split()` defines the sequence of characters around which the string is split. The result of `split()` is a list of strings containing parts of the original string. So let's see what the overloaded `split()` method with the use of regular expressions looks like. You will see that it is quite similar!

The definition looks as follows:

```kotlin
fun CharSequence.split(
    regex: Regex,
    limit: Int = 0
): List
```

Let's consider the arguments. The `regex` argument is responsible for the regular expression which defines the delimiter, and `limit` sets the maximum number of substrings to return. Zero by default means no limit is set.

So, `split()` splits the input char sequence around the matches of a given regular expression.

##### Regexps and split(): example

If you remember, in previous topics we looked at an example of using `split()` in phone number splitting. We split an American phone number into its country code, area code, central office code, and other remaining digits:

```kotlin
val number = "+1-213-345-6789"
val parts = number.split("-") // {"+1", "213", "345", "6789"}
```

Now let's make the previously considered problem more complex. There may be parentheses in the number. Or there may be none. We need to cover both of these cases.

This is where regular expressions help us! Let's compose a regular expression that will match either the separator "`-`", or "`-(`", or "`)-`". We will use `toRegex()` to create a regular expression from a string.

```kotlin
"(-\\(|\\)-|-)".toRegex()
```

Let's check the method for both variants of number spelling:

```kotlin
val number = "+1-213-345-6789"
val brackets = "+1-(213)-345-6789"
// splitting all substrings in number with brackets
val splitBrackets = brackets.split("(-\\(|\\)-|-)".toRegex()) // {"+1", "213", "345", "6789"}
// splitting only two substring
val splitFirstBrackets = number.split("(-\\(|\\)-|-)".toRegex(), 2) // {"+1", "213-345-6789"}
// splitting all substrings in number without brackets
val splitNumber = number.split("(-\\(|\\)-|-)".toRegex()) // {"+1", "213", "345", "6789"}
```

Everything works!

##### Regexps and replace()

Let's look at the use of regexps with another method: `replace()`. As you may remember, this method is used to replace some parts of the original string with new ones:

```kotlin
fun String.replace(
    oldValue: String,
    newValue: String,
    ignoreCase: Boolean = false
): String
```

Accordingly, we can either define the desired parts of the string directly as another string or cover more cases using a regular expression.

Let's look at the overload of `replace()` for regular expressions:

```kotlin
fun CharSequence.replace(
    regex: Regex,
    replacement: String
): String
```

As arguments, it accepts `regex`, which searches for replaceable parts in the text, and the string with which they will be replaced.

##### Regexps and replace(): example

So, let's look at an example of using `replace()` with regexp.

Suppose we have a text in which we need to replace all the digits with the string "[digits]". It will be difficult to define exactly what we want with one string. But with regular expressions, everything is solved very easily:

```kotlin
val withDigits = "The first test flight of Falcon 9 was on June 4, 2010, " +
        "from Cape Canaveral, Florida, and the first resupply mission " +
        "to the ISS was made on October 7, 2012."
val processedString = withDigits.replace("\\d+".toRegex(), "[digits]")
```

It's easy to see that regex `\\d+` matches all occurrences of one or more numbers in the text.

As a result of executing the code above, the following text will be stored in `processedString`:

```
The first test flight of Falcon [digits] was on June [digits], [digits], from Cape Canaveral, Florida, and the first resupply mission to the ISS was made on October [digits], [digits].
```

As you can see, all the numbers in the text have been replaced with the specified string.

##### find() and findAll()

Finally, let's take a look at two more functions that will definitely come in handy.

The `find` function is used to find the **first match** of a regular expression in the input. It searches from the start index of the input string.

Let's make the phone number regex even more versatile and see how it all works:

```kotlin
val regex = """[+]?[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*""".toRegex() // phone number template
val matchResult = regex.find("Her phone number is +1-234-567-89-01. You can also call the second one: +1-111-568-01-01")!!
print(matchResult.value) // +1-234-567-89-01
```



Note that we care about null safety. The text may not contain matches at all, so we always use the `!!` operator or the Elvis operator.



The `findAll` function is required if you want to find **all matches**. It returns all suitable substrings at once:

```kotlin
val regex = """\d{4}-\d{2}-\d{2}""".toRegex() // date template in format YYYY-MM-DD 
val matchResult =
    regex.findAll("Harry was born 1980-07-31 in the Godric's Hollow."
                  + "In 1997-12-24, on Christmas Eve, he returned there" 
                  + "for the legendary Gryffindor sword")
for (matches in matchResult) println(matches.value)
//1980-07-31
//1997-12-24
```

##### Conclusion

In this topic, we have considered the use of regular expressions with the `replace()` and `split()` methods and studied examples of this approach. This offers us more versatile tools for working with text.

And now — good luck with the tasks!