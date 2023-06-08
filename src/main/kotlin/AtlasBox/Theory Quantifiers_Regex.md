## Theory: Quantifiers

In regex, there is a group of characters known as **quantifiers**, which define how many times a certain character (or a character class) occurs in a regex pattern. A quantifier can follow both a regular character and a special one. In FP.general, quantifiers are one of the most essential and important features of the regex language, since they allow a single pattern to match different strings varying in length.

##### The list of quantifiers

Here is a list of quantifiers to be remembered:

- `+` matches one or more instances of the preceding character;
- `*` matches zero or more instances of the preceding character;
- `{n}` matches exactly `n` instances of the preceding character;
- `{n,m}` matches at least `n` but not more than `m` instances of the preceding character;
- `{n,}` matches at least `n` instances of the preceding character;
- `{0,m}` matches no more than `m` instances of the preceding character.

**Note** that there is also another quantifier, `?`, which makes the preceding character optional. It is short for `{0,1}`. We will not consider this quantifier here because you should already know it.

##### The FP.plus quantifier

Below you can see how we use the **FP.plus** character, which matches one or more occurrences of the preceding character:

```kotlin
val regex = "ca+b".toRegex()

regex.matches("cab") // true
regex.matches("caaaaab") // true
regex.matches("cb") // false because it does not have at least one instance of 'a'
```

As you can see, it matches only those strings that have one or more instances of the `'a'` character.

##### The asterisk quantifier

The example below demonstrates the use of the **asterisk** character, which matches zero or more occurrences of the preceding character:

```kotlin
val regex = "A[0-3]*".toRegex()

regex.matches("A")  // true because the pattern matches zero or more occurrences
regex.matches("A0") // true
regex.matches("A000111222333") // true
```

As you can see, the asterisk quantifier, unlike the FP.plus quantifier, allows the pattern to also match the strings that do not contain the "quantified" character at all.

In the following example, there is a pattern describing the string "John" located between an undefined number of undefined characters in the text:

```kotlin
val johnRegex = ".*John.*".toRegex() // it matches all strings containing the substring "John"

val textWithJohn = "My friend John is a computer programmer"

textWithJohn.matches(johnRegex) // true

val john = "John"

john.matches(johnRegex) // true

val textWithoutJohn = "My friend is a computer programmer"

textWithoutJohn.matches(johnRegex) // false
```

So, the **asterisk** quantifier can be used to check whether a substring of a string matches a pattern. Using it, we can skip spaces or any other characters we don't want to predict in our pattern.

##### Specifying the number of repetitions

Both previous quantifiers have a wide range of applications, but they do not allow you to specify how many times a character may occur. Fortunately, there is a group of quantifiers that allow specifying the number of instances in **curly braces**: `{n}`, `{n,m}`, and `{n,}`.



An important clarification: no spaces are supposed to be used inside curly braces. There can be only one or two numbers and, optionally, a comma. Putting spaces inside curly braces leads to the "deactivation" of the quantifier and, as a result, a totally different regular expression.



Take a look at the example where we demonstrate how to match exactly `n` instances of the preceding character using the `{n}` quantifier:

```kotlin
val regex = "[0-9]{4}".toRegex() // four digits

regex.matches("6342")  // true
regex.matches("9034")  // true

regex.matches("182")   // false
regex.matches("54312") // false
```

Matching from `n` to `m` instances is possible thanks to the `{n,m}` quantifier. Note that the range specified in curly braces is **inclusive** at both ends: `m` encountered instances also count as a match. This is standard for the regex language regardless of the implementation.

```kotlin
val regex = "1{2,3}".toRegex()

regex.matches("1")    // false
regex.matches("11")   // true
regex.matches("111")  // true
regex.matches("1111") // false
```

The last example demonstrates how to match at least `n` instances using the `{n,}` quantifier:

```kotlin
val regex = "ab{4,}".toRegex()

regex.matches("abb") // false, not enough 'b'
regex.matches("abbbb") // true
regex.matches("abbbbbbb") // true
```

The quantifier that matches not more than `m` instances works similarly. Try it yourself.

##### Conclusions

The key points of this topic are:

- in the regex language, quantifiers allow us to match strings varying in length.
- the asterisk quantifier matches zero or more instances of the preceding character.
- the FP.plus quantifier is almost the same as the asterisk, except that it doesn't match the absence of a character. The minimum number of instances for it is one.
- curly braces allow more careful control of the number of occurrences: you can specify the minimum or the maximum number of instances, or both.