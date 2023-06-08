@file:Suppress("ALL")

package Variables

import java.util.*

/**
Using the String type for creating text and control it.
String is kotlin Class has overriding 4 function and 1 variable,
but all others attachment in String class came from Comparable,
and CharSequence interfaces and the extensions functions and some from java.String
 */
// Note: Some Attachment has void run block,
// you can find those in the path of package.

val str = String.CASE_INSENSITIVE_ORDER // TODO A Comparator that orders strings ignoring character case.

private fun some() {
    //
    """..."""
    val string = "\n-> next line," +
            "\r-> hide all the chars before it" +
            "\b the Unicode BACKSPACE symbol (U+0008) | hide the last char in output." +
            "\t->  the Unicode TAB symbol (U+0009) " +
            "\$-> the Unicode DOLLAR sign (U+0024)" +
            "\'-> the Unicode apostrophe symbol (U+0027)" +
            "\\-> the Unicode backslash symbol (U+005C)" +
            "\"-> the Unicode double quotation symbol (U+0028)" +
            "\u1FC8-> unicode."

    buildString {
        append("...")
        appendLine("...")
        appendRange("...", 0, 3)
        clear()
        deleteAt(0)
        deleteRange(0, 3)
        insertRange(0, "...", 0, 3)
        set(0, 'a')
        setRange(0, 3, "...")
        toCharArray(charArrayOf())
        appendCodePoint(0) // Return the Unicode of this number.
        capacity() // TODO("why +16")
        ensureCapacity(0) // TODO: 31/05/2021
    }

    // String Attachment:
    with(string) {
        byteInputStream().run {
        }
        chars().run {
            /**@see AtlasJ.JStream.intStream*/
        }
        compareTo("string") // Compare by the size.
        codePoints().run {
            /**@see AtlasJ.JStream.intStream*/
        }
        codePointAt(1) // Return the decimal of the index.
        codePointBefore(1) // Return the decimal of the index before that index.
        codePointCount(1, 2) // Return a count between start_index and end_index.
        contentEquals("") // Check if the value is a same.
        coerceIn("st", "string") // Return the max.Value if the text get it, else min.Value.
        coerceAtMost("string")
        coerceAtLeast("st")
        drop(0) // Remove n indexes from start.
        dropLast(0) // Remove from the end.
        dropWhile { it == 's' } // Remove all 's' char from the start.
        dropLastWhile { it == 'g' } // Remove all 's' char from the end.
        lowercase() // Make All the String as lowercase.
        endsWith("ing") // Check if the text end with some char or char_sequence.
        encodeToByteArray().run {
            /**@see Variables.byteArr*/
        }
        filter { it == 's' } // for get only "it" from the string.
        filterNot { it == 's' } // for get all the text except "it".
        filterIndexed { index: Int, c: Char -> c == c + index }
        filterIndexedTo(StringBuilder("")) { index, c -> c == c + index }
        format()
        /**@see AtlasJ.JFormat*/
        this[0] // Get the value of the index 0.
        hashCode() // Equality -> s[0]*31^(n-1) + s[1]*31^(n-2) + ...
        intern()
        /** @see AtlasBox.Interning_Of_String*/
        this::class.run {
            /**@see Atlas.k.KClass*/
        }
        javaClass.run {
            /**@see AtlasJ.JClass*/
        }
        length // Index size.
        orEmpty() // Returns the string if it is not null, or the empty string otherwise.
        offsetByCodePoints(
            0,
            3
        ) // TODO: Returns the index within this String that is offset from the given index by codePointOffset code points. Unpaired surrogates within the text range given by index and codePointOffset count as one code point each.
        plus("value") // return this string with that value plus.
        padEnd(1, '*') // *string.
        padStart(1, '*') // string*
        partition { true } // Splits the original string into a pair of strings, {true}==(string, ), {false}== ( ,string).
        prependIndent() //  Prepends indent to every line of the original string.
        uppercase() // Making All the String as capital.
        reader().run {
        }
        regionMatches(
            0,
            "string",
            0,
            6
        ) // Returns true if the specified range in this string is equal to the specified range in another string.
        replace('s', '*', false) // *tring
        replaceAfter('r', "*", this) // str*
        replaceAfterLast('r', "*", this) // str*
        replaceBefore('r', "*", this) // *ing
        replaceBeforeLast('r', "*", this) // *ing
        replaceRange(0, 3, "*") // *ing
        replaceFirst('t', '*', false) // s*ring
        replaceFirstChar { uppercase() } // String, or lowercase() -> string.
        replaceIndent("") // Return newIndent in front the text with the text have indent or not.
        replaceIndentByMargin(
            "",
            ""
        ) // If the indent of the text equal the marginPrefix then will replace it with newIndent.
        removeSuffix("ing") //
        removePrefix("str") //
        removeSurrounding("st", "ng") // -> ri.
        removeRange(0..3) //
        rangeTo("other") // string..other.
        runCatching { } // Return the result if it's success, but throw to exception if it's failure.
        reversed() // -> return all the text as reversed.
        slice(0..2) //
        startsWith("") // Returns true if this string starts with the specified prefix.
        substring(3) //
        substringBefore('i') //
        substringBeforeLast('i')
        substringAfter('r') //
        substringAfterLast('r')
        toBoolean() // Returns true if the orchid.contents of this string is equal to the word "true", ignoring case, and false otherwise.
        toByteArray().run {
            /**@see Variables.byteArr*/
        }
        toCharArray().run {
            /**@see Variables.charArray*/
        }
        toBooleanStrict() // Returns true if the content of this string is equal to the word "true", false if it is equal to "false".
        toBooleanStrictOrNull() // , and null otherwise.
        toBigDecimal() // Parses the string as a java.math.BigDecimal number and returns the result.
        toBigDecimalOrNull() //  or null if the string is not a valid.
        toBigInteger() //         ⎫
        toBigIntegerOrNull() //   ⎪
        toByte() //               ⎪
        toUByte() //              ⎪
        toByteOrNull() //         ⎪
        toUByteOrNull() //        ⎪
        toDouble() //             ⎪
        toDoubleOrNull() //       ⎪
        toInt() //                ⎬- Parses the string as a Type number and returns the result.
        toIntOrNull() //          ⎪
        toFloat() //              ⎪
        toFloatOrNull() //        ⎪
        toLong() //               ⎪
        toLongOrNull() //         ⎪
        toULong() //              ⎪
        toULongOrNull() //        ⎪
        toShort() //              ⎪
        toShortOrNull() //        ⎪
        toUShort() //             ⎪
        toUShortOrNull() //       ⎭
        lowercase()
        toPattern().run {
            /**@see AtlasJ.JPattern*/
        }
        toRegex().run {
            /** @see RegularExpression.kt */
        }
        take(3) // Take numbers of the values, (n:3) -> str.
        takeLast(0) // Take from the last.
        takeIf { it == "string" } // Returns this value if it satisfies the given predicate or null, if it doesn't.
        takeUnless { it == "string" } // Returns this value if it _does not_ satisfy the given predicate or null, if it does.
        takeWhile { it == 's' } // Returns a string containing the first characters that satisfy the given predicate.
        takeLastWhile { it == 'g' } // Returns a string containing last characters that satisfy the given predicate.
        trim() // Returns a string having leading and trailing whitespace removed.
        trimStart() // Returns a string having leading whitespace removed from the start.
        trimEnd() // Returns a string having leading whitespace removed from the End.
        trimMargin("str") //
        trimIndent() //
    }
}
