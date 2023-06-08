package collections

/** A generic unordered collection of elements that does not support duplicate elements. */
fun mySet(){
    val set : Set<Any> = setOf(1,2,2,3,4,14,10,5,6,7,8)
    with(set){
          toTypedArray() // Returns a typed array containing all the elements of this collection.
          plus(1)
          plusElement(1)
          minus(1)
          minusElement(1)
    }
    // MutableSet.
    mutableSetOf(1,2,2,3,4,14,10,5,6,7,8)

    // HashSet.
    hashSetOf<Nothing>()

    // LinkedHashSet.
    linkedSetOf<Nothing>()

    // AbstractSet.
    object : AbstractSet<Nothing>() {
        override val size: Int
            get() = TODO("Not yet implemented")

        override fun iterator(): Iterator<Nothing> {
            TODO("Not yet implemented")
        }
    }

    // AbstractMutableSet.
    object : AbstractMutableSet<Nothing>(){
        override fun add(element: Nothing): Boolean {
            TODO("Not yet implemented")
        }

        override fun iterator(): MutableIterator<Nothing> {
            TODO("Not yet implemented")
        }

        override val size: Int
            get() = TODO("Not yet implemented")
    }
}