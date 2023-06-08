package Atlas.s

interface Sequence {
    /**
     * A sequence that returns values through its iterator.
     * The values are evaluated lazily, and the sequence is potentially infinite.
     * Most Sequence members is familiar wit iterable, so you can go back to Iterable to find
     * the descriptions of the members, except constrainOnce() and ifEmpty(),
     * So Then What's the different between Iterable and Sequence.
          -> Sequence are "processed" lazily, Iterable eagerly.
     */

   private fun mySequence() {

        "Madagascar".asSequence().apply {

            // special for sequence.
            ifEmpty { sequenceOf() } // when this sequence be empty, performed the default function that's must be Sequence type.
            this.constrainOnce()  // TODO: 28/07/2021
            onEach { println(it) } // performs the given action on each element and returns the collection itself afterward.
            onEachIndexed { index, c ->  }

            //
            all { it == it }
            any { it == it }
            asIterable().run {
                /** @see Atlas.i.Iterable */
            }
            associateBy { Pair(it, it) }
            associateWith { it }
            associateByTo(mutableMapOf()) { any -> any }
            associateWithTo(mutableMapOf()) { it }
            associateWith { any -> any }
            associateWithTo(mutableMapOf()) { any -> any }
            chunked(2) { it == it }
            contains('s')
            count()
            distinct()
            distinctBy { }
            elementAt(0)
            elementAtOrElse(2) { }
            elementAtOrNull(2)
            first()
            firstOrNull()
            find { it == 's' }
            findLast { it == 's' }
            flatMap { this }
            flatMapTo(mutableListOf()) { this }
            flatMapIndexed { index: Int, c: Char -> this }
            flatMapIndexedTo(mutableListOf()) { index: Int, c: Char -> this }
            forEach { print(it) }
            forEachIndexed { index, any -> }
            fold("") { acc, any -> acc + any }
            foldIndexed("") { index, acc, any -> acc + any + index }
            filter { it == it }
            filterNot { it == it }
            filterIndexed { index, it -> it == it }
            filterIsInstance<Any>()
            filterIsInstanceTo(mutableListOf(""))
            filterTo(mutableListOf()) { it == it }
            filterNotTo(mutableListOf()) { it == it }
            filterNotNull()
            groupBy { it == 's' }
            groupByTo(mutableMapOf(), { it }, { it })
            groupingBy { it }
            indexOf(Any())
            indexOfFirst { it == it }
            indexOfLast { it == it }
            iterator()
            joinTo(StringBuffer())
            joinToString()
            last()
            lastOrNull()
            lastIndexOf('t')
            map { }
            mapTo(mutableListOf()) { }
            mapNotNull { }
            mapNotNullTo(mutableListOf()) { }
            mapIndexed { index, any -> }
            mapIndexedNotNull { index, any -> any; index }
            mapIndexedNotNullTo(mutableListOf()) { index, any -> any; index }
            maxOrNull()
            maxByOrNull { it }
            maxWithOrNull { t, t2 -> t.compareTo(t2) }
            minOrNull()
            minByOrNull { it.isLowerCase() }
            minWithOrNull { t, t2 -> t.compareTo(t2) }
            minus('z')
            minusElement('z')
            none { c: Char -> c == 'z' }
            reduce { acc, c -> acc }
            reduceOrNull { acc, c -> c }
            reduceIndexed { index, acc, c -> c }
            scan("") { acc: String, c: Char -> c+acc }
            scanIndexed("") { index, acc, c -> c+acc+index }
            runningReduce { acc, c -> c }
            runningReduceIndexed { index, acc, c -> c }
            single { it == it }
            singleOrNull()
            sumOf { it.code }
            sortedBy { null }
            sortedByDescending { null }
            plus('z')
            plusElement('z')
            partition { it == it }
            toCollection(mutableListOf<Any>())
            toHashSet()
            toList()
            toMutableList()
            toSet()
            toSortedSet()
            toMutableSet()
            windowed(3)
            withIndex()
            zip(this).run {
                unzip()
            }
            zipWithNext { a: Char, b: Char -> a + "" + b }
            shuffled()
        }
    }
}