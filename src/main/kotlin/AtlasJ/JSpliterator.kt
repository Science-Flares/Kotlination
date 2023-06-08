package AtlasJ

import java.util.*
import java.util.function.Consumer

class JSpliterator {
    lateinit var spliterator: Spliterator<Int>

    init {
        //
        Spliterator.CONCURRENT //
        Spliterator.SUBSIZED //
        Spliterator.SORTED //
        Spliterator.SIZED //
        Spliterator.ORDERED //
        Spliterator.NONNULL //
        Spliterator.IMMUTABLE //
        Spliterator.DISTINCT //
        Spliterator.OfDouble.SUBSIZED
        Spliterator.OfInt.SUBSIZED
        Spliterator.OfLong.SUBSIZED
        Spliterator.OfPrimitive.SUBSIZED

        with(spliterator) {
            this.trySplit()
            this.characteristics()
            this.estimateSize()
            this.forEachRemaining { }
            this.hasCharacteristics(0)
            this.tryAdvance(Consumer { })
            this.exactSizeIfKnown
            this.comparator.run {
                /*  */
            }
        }

        //
        Spliterators.emptySpliterator<Nothing>()
        Spliterators.emptyIntSpliterator()
        Spliterators.emptyDoubleSpliterator()
        Spliterators.emptyLongSpliterator()
        Spliterators.iterator(spliterator)
        Spliterators.spliterator(intArrayOf(),0)
        Spliterators.AbstractSpliterator.CONCURRENT // example.


    }
}

