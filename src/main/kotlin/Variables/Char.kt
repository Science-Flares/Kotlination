package Variables


lateinit var charIterator:CharIterator
lateinit var charArray: CharArray

//
const val character: Char = 'z'
@ExperimentalStdlibApi
fun myChar(){
    val char = Char
    with(char){
        SIZE_BYTES // 2
        SIZE_BITS // 16
        MAX_VALUE // '\uFFFF'
        MIN_VALUE // '\u0000'
        MAX_SURROGATE // MAX_LOW_SURROGATE
        MIN_SURROGATE // MIN_HIGH_SURROGATE
        MAX_HIGH_SURROGATE // '\uDBFF'
        MIN_HIGH_SURROGATE // '\uD800'
        MAX_LOW_SURROGATE // '\uDFFF'
        MIN_LOW_SURROGATE // '\uDC00'
    }
    fun character() {
        with(character) {
            category.run {
                /** @see Atlas.c.CharCategory*/
            }
            directionality.run {
                /** @see Atlas.c.CharDirectionality*/
            }
            code /// Return the code of this char.
            isTitleCase() // Returns true if this character is a title case letter.
            isDefined() // Returns true if this character (Unicode code point) is defined in Unicode.
            isDigit() // Returns true if this character (Unicode code point) is a digit.
            isLetter() // Returns true if this character.
            isLetterOrDigit() // Returns true if this character or number.
            isWhitespace() // Returns `true` if the character is whitespace.
            isSurrogate() // if this unicode between '\uD800' & '\uDFFF'.
            isHighSurrogate() // if this unicode between '\uD800' & '\uDBFF'
            isLowSurrogate() // if this unicode between '\uDC00' & '\uDFFF'
            isISOControl() /* it's spacial unicode has to ranges from \u0000..\u001F and from \u0080..\u009F.
                                this unicode hasn't unique symbol but control option like (Ctrl, Fin, Alt...) */
            isJavaIdentifierPart() /* A character may be part of a Java identifier if any of the following are true −
                                        it is a letter
                                        it is a currency symbol (such as '$')
                                        it is a connecting punctuation character (such as '_')
                                        it is a digit
                                        it is a numeric letter (such as a Roman numeral character)
                                        it is a combining mark
                                        it is a non-spacing mark */
            isJavaIdentifierStart() /* A character may start a Java identifier if and only if one of the following conditions is true −
                                        isLetter(char) returns true
                                        getType(char) returns LETTER_NUMBER
                                        char is a currency symbol (such as '$')
                                        char is a connecting punctuation character (such as '_'). */
                isIdentifierIgnorable() /* The following Unicode characters are ignorable in a Java identifier or a Unicode identifier −
                                        ISO control characters that are not whitespace
                                        in '\u0000'..'\u0008'
                                        in '\u000E'..'\u001B'
                                        in '\u007F'..'\u009F'
                                        all characters that have the FORMAT FP.general category value */
            this.isUpperCase() // Returns true if this character is upper case.
            this.isLowerCase() // Returns true if this character is lower case.
            this.inc() // Increments this value.
            this.dec() // Decrements this value.
            this.minus(0) // Subtracts the other Int/Char value from this value resulting an Int/Char.
            this.plus(0) // Adds the other Int value to this value resulting a Char.
            this.rangeTo('z') // Creates a range from this value to the specified other value.
            this.digitToInt() // Returns the numeric value of the decimal digit that this Char represents.
            this.digitToIntOrNull() // Some of digitToInt(), or null if this Char is not a valid decimal digit.
            this.downTo('a') // Returns a progression from this value down to the specified to value with the step -1.
            this.until('z')
            this.lowercase() /* Converts this character to lower case using Unicode mapping rules of the invariant locale.
                             This function supports one-to-many character mapping,
                             thus the length of the returned string can be greater than one.
                             For example, '\u0130'.lowercase() returns "\u0069\u0307", where '\u0130' is the LATIN CAPITAL LETTER I WITH DOT ABOVE character (İ).
                             If this character has no lower case mapping, the result of toString() of this char is returned.*/
            this.lowercaseChar() /* Converts this character to lower case using Unicode mapping rules of the invariant locale.
                                  This function performs one-to-one character mapping.
                                  To support one-to-many character mapping use the lowercase function.
                                  If this character has no mapping equivalent, the character itself is returned.*/
            this.uppercase() /* Converts this character to upper case using Unicode mapping rules of the invariant locale.
                                This function supports one-to-many character mapping,
                                thus the length of the returned string can be greater than one.
                                 For example, '\uFB00'.uppercase() returns "\u0046\u0046", where '\uFB00' is the LATIN SMALL LIGATURE FF character (ﬀ).
                                  If this character has no upper case mapping, the result of toString() of this char is returned.*/
            this.uppercaseChar() /* Converts this character to upper case using Unicode mapping rules of the invariant locale.
                                    This function performs one-to-one character mapping.
                                     To support one-to-many character mapping use the uppercase function.
                                      If this character has no mapping equivalent, the character itself is returned.*/
            this.titlecase() /*Converts this character to title case using Unicode mapping rules of the invariant locale.
                                This function supports one-to-many character mapping,
                                 thus the length of the returned string can be greater than one.
                                  For example, '\uFB00'.titlecase() returns "\u0046\u0066", where '\uFB00' is the LATIN SMALL LIGATURE FF character (ﬀ).
                                   If this character has no title case mapping, the result of uppercase is returned instead.*/
            this.titlecaseChar() /* Converts this character to title case using Unicode mapping rules of the invariant locale.
                                    This function performs one-to-one character mapping.
                                     To support one-to-many character mapping use the titlecase function.
                                      If this character has no mapping equivalent, the result of calling uppercaseChar is returned.*/
        }
    }

    // CharArray. It's like any Array but only for Char values.
   with(charArray){
       this[0]
//       this.set()
       this.size
       this.iterator()
   }

    // charRange.
    val charRange:CharRange = 'a'..'z'
    charRange.run {
         random() // get some char from the range randomly.
         randomOrNull()
         contains('z')
    }

    // charProgression.
    val charProgression = charRange as CharProgression
    charProgression.run {
    // look to intProgression.
    }

    // charIterator.
    charIterator.nextChar() // Returns the next value in the sequence without boxing.



}
