package Atlas.c
import kotlin.text.CharCategory.*

private class CharCategory {
    var cc = 'A'.category

    /**
     * Represents the character FP.general category in the Unicode specification.
     */
    init {
        cc.run {
            code // Must be two characters denotes about category name.
            value // TODO: 16.01.2021  
            contains('a')
            FORMAT //                   ⎫
            COMBINING_SPACING_MARK //   ⎪
            CONNECTOR_PUNCTUATION //    ⎪
            CONTROL //                  ⎪
            CURRENCY_SYMBOL //          ⎪
            DASH_PUNCTUATION //         ⎪
            START_PUNCTUATION //        ⎪
            END_PUNCTUATION //          ⎪
            ENCLOSING_MARK //           ⎪
            DECIMAL_DIGIT_NUMBER //     ⎪
            FINAL_QUOTE_PUNCTUATION //  ⎬- See charCategory package.
            LETTER_NUMBER //            ⎪
            UPPERCASE_LETTER //         ⎪
            MATH_SYMBOL //              ⎪
            MODIFIER_SYMBOL //          ⎪
            MODIFIER_LETTER //          ⎪
            NON_SPACING_MARK //         ⎪
            OTHER_NUMBER //             ⎪
            OTHER_PUNCTUATION //        ⎪
            OTHER_SYMBOL //             ⎪
            PRIVATE_USE //              ⎪
            TITLECASE_LETTER //         ⎪
            OTHER_LETTER //             ⎪
            LOWERCASE_LETTER //         ⎪
            INITIAL_QUOTE_PUNCTUATION //⎪
            UNASSIGNED //               ⎭
            SPACE_SEPARATOR // Whitespace.
            LINE_SEPARATOR // Whitespace.
            PARAGRAPH_SEPARATOR // Whitespace.
            SURROGATE // it.isSurrogate().
        }
    }
}