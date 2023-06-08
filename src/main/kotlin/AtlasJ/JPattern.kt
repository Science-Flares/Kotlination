package AtlasJ

import java.util.regex.Pattern

class JPattern {
    lateinit var pattern: Pattern

    init {
        /** ... */
        fun pattern() {
            Pattern.CANON_EQ
            Pattern.CASE_INSENSITIVE
            Pattern.COMMENTS
            Pattern.DOTALL
            Pattern.LITERAL
            Pattern.MULTILINE
            Pattern.UNICODE_CASE
            Pattern.UNICODE_CHARACTER_CLASS
            Pattern.UNIX_LINES
            Pattern.compile("")
            Pattern.matches("", "")
            Pattern.quote("")

        }
        // ...
        with(pattern) {
            this.asMatchPredicate()
            this.asPredicate()
            this.flags()
            this.matcher("")
            this.pattern()
            this.split("")
            this.splitAsStream("")
            this.toRegex().run {
            }
        }

    }
}