package Atlas.c

import kotlin.collections.Collection


private interface Collection {
    var mutableCollection: MutableCollection<Char>
    var matchNamedGroupCollection: MatchNamedGroupCollection
    var matchGroupCollection: MatchGroupCollection

    /**
     * A generic collection of elements.
     * Methods in this interface support only read-only access to the collection;
     * read/write access is supported through the [MutableCollection] interface.
     * @param E the type of elements contained in the collection.
     * The collection is covariant on its element type.
     */
    private fun <E> coll() {
        val collection: Collection<Int> = listOf() // setOf, mapOf.
        with(collection) {
            size // Size of the elements.
            indices // Return the range between first and last element.
            this.parallelStream().run {
            // TODO Returns a possibly parallel Stream with this collection as its source.
                /** @see AtlasJ.JStream */
            }
            this.stream().run {
            // TODO Returns a sequential Stream with this collection as its source.
                /** @see AtlasJ.JStream */
            }
            contains(0) // Return true if the element exist in this collection.
            containsAll(listOf())
            isEmpty() // Return true if this collection is empty.
            isNotEmpty() // Return true if this collection is Not empty.
            count { it == 0 } // Count the size of "it".
            plus(0) // Returns an array containing all elements of the original array and then all elements of the given elements array.
            plusElement(3) // Returns an array containing all elements of the original array and then the given element.
            random() // Returns a random element from this collection.
            randomOrNull() // , or null if this collection is empty.
            toMutableList()
            toTypedArray()
        }
        with(mutableCollection) {
            this.remove('z')
            this.removeAll(listOf())
            this.removeIf { it == 'z' } // TODO: 30/07/2021
            this.add('*')
            this.addAll(listOf('*'))
            this.plusAssign(listOf())
            this.minusAssign(listOf())
            this.retainAll(listOf('a','b')) // return all the shared elements with element collection.
            this.clear()
        }
        matchGroupCollection[0] // TODO: 07/11/2021
        matchNamedGroupCollection[0] // TODO: 07/11/2021
    }
}