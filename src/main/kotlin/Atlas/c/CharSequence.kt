package Atlas.c

import kotlin.CharSequence

private interface CharSequence {

    /**
     * Represents a readable sequence of [Char] values.
     */
    private fun attachmentOfTheCharSequence() {
         val charSequence : CharSequence = "Canada"
         with(charSequence){
            this.chunkedSequence(2) { it }
            this.commonPrefixWith("str***") // take the correct CharSequence from the start text. -> str
            this.commonSuffixWith("***ing") //  take the correct CharSequence from the end text. -> ing
            this.endsWith("") // Returns `true` if this char sequence ends with the specified suffix.
            this.findAnyOf(arrayListOf("")) // to find and get any string of the collection.
            this.findLastAnyOf(arrayListOf(""))
            this.foldRight("#") { c: Char, acc: String -> "$c*$acc" }
            this.foldRightIndexed("") { index: Int, c: Char, acc: String -> "$c*$acc$index" }
            this.getOrElse(0) { 'z' } // get the value of the index 0, or else get 'z'.
            this.getOrNull(0) // get the value of the index 0, or else get null.
            this.hasSurrogatePairAt(0)
            this.indices
            this.indexOfAny(arrayListOf("string"))
            this.ifBlank { "Blank!!" } // return "Blank!!" if the string is blank.
            this.isBlank() // check if the string has any value, Note: space=true.
            this.isNotBlank()
            this.isNullOrBlank() // accept the nullable value.
            this.ifEmpty { "Empty!!" } // return "Empty!!" if the string is empty.
            this.isEmpty() // check if the string has any value, Note: space=false.
            this.isNotEmpty()
            this.isNullOrEmpty() // accept the nullable value.
            this.lastIndexOfAny(arrayListOf("string")) // last index number of "string" word.
            this.lines() // making each line in the text as index.
            this.lineSequence() // TODO: 27/05/2021
            this.lastIndex // last index number.
            this.windowedSequence(3)
            this.matches(Regex("")) // Returns `true` if this char sequence matches the given regular expression.
            this.random() // return random char from the string.
            this.randomOrNull() // return randomly Index?.
            this.repeat(2)
            this.substring(0..3)
            this.split("str") // :[, ing] mean:Splits this char sequence to a list of strings around occurrences of the specified delimiters.
            this.splitToSequence("")
            this.subSequence(1..3) // -> tri

            /** Those functions available in iterable,
             * @see Atlas.i.Iterable */
            all { it == 's' }
            any { it == 's' }
            asIterable()
            asSequence()
            associate { it to it.code }
            associateBy { it to it.code }
            associateTo(mutableMapOf()) { Pair(it.isDigit(), it.isLetter()) }
            associateByTo(mutableMapOf()) { c: Char -> c.isLetter() }
            associateWith { c: Char -> c }
            associateWithTo(mutableMapOf()) { c: Char -> c }
            chunked(2) { it }
            contains('s')
            count()
            elementAt(0)
            elementAtOrElse(2) { 'z' }
            elementAtOrNull(2)
            filter { it.isLowerCase() }
            filterNot { it.isLowerCase() }
            filterTo(StringBuffer()){it.isLowerCase()}
            filterNotTo(StringBuilder()) { it.isLetter() }
            filterIndexed { index, it -> it.isLowerCase() }
            filterIndexedTo(StringBuilder()) { index, c -> c.isLetter() }
            first()
            firstOrNull()
            find { it == 's' }
            findLast { it == 's' }
            flatMap { c: Char -> listOf(c) }
            flatMapTo(mutableListOf()) { c: Char -> listOf(c) }
            forEach { print(it) }
            forEachIndexed { index: Int, c: Char -> println(c + index) }
            fold("#") { acc: String, c: Char -> "$acc*$c" }
            foldIndexed("") { index: Int, acc: String, c: Char -> "$acc*$c$index" }
            groupBy { it == 's' }
            groupByTo(mutableMapOf(), { it }, { it })
            groupingBy { it }
            indexOf('a')
            indexOfFirst { it == 's' }
            indexOfLast { it == 'g' }
            iterator()
            last()
            lastOrNull()
            lastIndexOf('t')
            map { '*' }
            mapTo(mutableListOf()) { '*' }
            mapNotNull { '*' }
            mapNotNullTo(mutableListOf()) { '*' }
            mapIndexed { index: Int, c: Char -> "$c-$index" }
            mapIndexedNotNull { index: Int, c: Char -> "$c-$index" }
            mapIndexedNotNullTo(mutableListOf()) { index: Int, c: Char -> "$c-$index" }
            maxByOrNull { it }
            maxByOrNull { it.isLowerCase() }
            maxWithOrNull { t, t2 -> t.compareTo(t2) }
            minOrNull()
            minByOrNull { it.isUpperCase() }
            minWithOrNull { t, t2 -> t.compareTo(t2) }
            none { c: Char -> c == 'z' }
            scan("") { acc: String, c: Char -> "$acc*$c" }
            scanIndexed("") { index, acc, c -> "$acc-$c-$index" }
            single { it == 't' }
            singleOrNull()
            this.sumOf { it.code }
            toCollection(mutableListOf<Any>())
            toHashSet()
            toList()
            toMutableList()
            toSet()
            toSortedSet()
            windowed(3)
            withIndex()
            zip(this).unzip()
            zipWithNext { a: Char, b: Char -> a + "" + b }
        }
    }
}