@file:Suppress("UselessCallOnCollection")

package Atlas.i

private interface Iterable {
    var mutableIterable: MutableIterable<String>

    /**
     * Classes that inherit from this interface can be represented as a sequence of elements that can
     * be iterated over.
     * @param T the type of element being iterated over.
     * The iterator is covariant on its element type.
     */
    private fun myIterable() {
        "Argentina".asIterable().apply {
            all { it == 'g' } // check each index equal some specific value.
            any { it == 'g' } // check if some value existing in any index.
            asSequence().run {
                /**@see Atlas.s.Sequence */
            }
            associate { it to it.code } // {A=65, r=114, g=103, e=101 ...}
            associateBy { it.code } // {65=A, 114=r, 103=g, 101=e, ...}
            associateByTo(mutableMapOf()) { it.code }
            associateWith { it.code } // {A=65, r=114, g=103, e=101,... }
            associateWithTo(mutableMapOf()) { it }
            chunked(2) { it } // Splits this collection into several lists
            contains('n') // check if the text has specific char or CharSequence.
            count { it == 'n' } // count the size of "it".
            distinct() //  Return Set containing.
            distinctBy { }
            elementAt(0) // return the element of index.
            elementAtOrElse(2) { 't' } // , or 'z' if the index is out of bounds of this collection.
            elementAtOrNull(2) //, or null if the index is out of bounds of this collection.
            first() // to get first index.
            firstOrNull() // to get first index or null.
            firstNotNullOf { it=='t' } // to get first index of "it".
            firstNotNullOfOrNull { it=='t' } //  or null if no non-null value was produced.
            find { it == 'g' } // to find and get "it" from the text if it's existing, or null if wasn't.
            findLast { it == 'g' }
            flatMap { "123".asSequence() } // is usually useful for flattening one-to-many relationships, [1,2,3,1,2,3,1,2,3...].
            flatMapTo(mutableListOf()) { "".asSequence() }
            flatMapIndexed { index: Int, c: Char -> "".asSequence() }
            flatMapIndexedTo(mutableListOf()) { index: Int, c: Char -> "".asSequence() }
            forEach { print(it) }
            forEachIndexed { index, any -> println("$index; $any") }
            fold("") { acc, any -> acc + any } // Accumulates value starting with initial value and applying operation from left to right to current accumulator value and each element.
            foldIndexed("") { index, acc, any -> acc + any + index }
            runningFold(""){ acc, c -> c + acc } // return a list has every result operation between two values.
            runningFoldIndexed(""){index, acc, c -> c+acc+index }
            filter { it == 'g' } // for get only "it" from the string.
            filterNot { it == 'g' } // for get all the text except "it".
            filterIndexed { index, it -> it == it } // filter with index.
            filterTo(mutableListOf()) { it == it } // filter to destination.
            filterIndexedTo(mutableListOf()) { index, it -> it == it } // filter to destination with index.
            filterNotTo(mutableListOf()) { it == it } // filter not to te destination.
            groupBy { it.isUpperCase()  } // {true=[A], false=[r, g, e, n, t, i, n, a]}
            groupByTo(mutableMapOf(), { it }, { it.code }) // {A=[65], r=[114], g=[103], e=[101],.. }
            groupingBy { it }.run {
                /**@see Atlas.g.Grouping*/
            }
            indexOf('s') // return index of char 's'.
            indexOfFirst { it == 'g' }
            indexOfLast { it == 'g' }
            iterator().run {
                /**@see Atlas.i.Iterator*/
            }
            joinTo(StringBuffer("B "),"-", "<", ">", 5, "..!") // <B a-b-c-d-e-..!>
            joinToString("-", "<", ">", 5, "..!") // <a-b-c-d-e-..!>
            last() // last index value.
            lastOrNull() // , if is empty return null.
            lastIndexOf('t') //  last index of the element.
            map { it.code } // [65, 114, 103, 101, 110,... ]
            mapTo(mutableListOf()) { }
            mapNotNull { } // Returns a list containing only the non-null.
            mapNotNullTo(mutableListOf()) { }
            mapIndexed { index, any -> any;index } // map with indexed.
            mapIndexedNotNull { index, any -> any; index }
            mapIndexedNotNullTo(mutableListOf()) { index, any -> any; index }
            maxOrNull() // return max value or null if there are no elements.
            maxByOrNull { it.isLowerCase() }
            this.maxWithOrNull { t, t2 -> t.compareTo(t2) } // FIXME: 27/07/2021
            minOrNull() // return min value or null if there are no elements.
            minByOrNull { it.isLowerCase() }
            this.minWithOrNull { t, t2 -> t.compareTo(t2) } // FIXME: 27/07/2021
            minus('z')
            minusElement('z')
            none { it == 'z' } // return true if the char not existing in the string.
            reduce { acc, c -> c } // Accumulates value starting with the first element and applying operation from left to right
                                     // to current accumulator value and each element.
                                        // And te only different between reduce and fold is fold has initial value and reduce don't.
            reduceOrNull { acc, c -> c } // , Or null if the collection is empty.
            reduceIndexed { index, acc, c -> c }
            runningReduce { acc, c -> c } // return a list has result every step operation between the accumulator and element.
            runningReduceIndexed { index, acc, c -> c }
            scan("") { acc, c -> c + acc } // Similar to runningFold() but this has init value.
            scanIndexed("") { index, acc, c -> acc + c + index }
            single { it == 't' } // Returns the single element if existing, or throw exception.
            singleOrNull() // , or null if not exist.
            sumOf { it.code } // sum all elements in the iterable by "it".
            sortedBy{ it } // Return 'it' in the last of this sequence.
            sortedByDescending { it==it } // Returns a list of all elements sorted descending according to natural sort order of the value returned by specified selector function.
            plus('*') // Returns a list containing and then the given element.
            plusElement('*')
            toCollection(mutableListOf()).run {
                /**@see collections.lists*/
            }
            toHashSet() // Returns a new HashSet of all elements.
            toList().run {
                /**@see collections.lists*/
            }
            toMutableList() // Returns a new MutableList filled.
            toSet() // Returns a Set of all elements.
            toSortedSet() // Returns a Set of all elements.
            toMutableSet() // Returns a new MutableSet.
            windowed(3) // [str, tri, rin, ing].
            withIndex() // Returns a lazy Iterable that wraps each element with his index.
            zip("123".toList()) // Returns a list of pairs built from the elements of this collection and other collection with the same index.
            shuffled() // Returns a new list with the elements of this list randomly shuffled.
            partition { it == 'g' } // Splits the original collection into a pair of lists,cbf
            zipWithNext { a: Char, b: Char -> "$a$b" } // Return list has all operations between each two elements.

            // special for iterator.
            union("...".asIterable()) // Returns a set containing all distinct elements from both collections.
            spliterator().run {
                /**@see AtlasJ.JSpliterator*/
            }
            reversed()
        }
        // collection type of integer only.
        listOf(1,2,3,4,5,6,7,8,9).asIterable().average() // Returns an average value of elements in the collection.

        mutableIterable.run {
            this.removeAll { it == ".." }
            this.retainAll { it == ".." }
        }
    }

}
