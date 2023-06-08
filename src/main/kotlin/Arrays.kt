
private fun arr(){

    val arr = Array(3){'s'}
    arr[0]='a'
    arr[1]='b'
    arr[2]='c'

    arr.apply {
        component1() // Returns 1st element from the array.
        component2()
        component3()
        component4()
        component5()
        binarySearch('.') // Searches this Array for the provided [element] using the binary search algorithm.
        contentEquals(arrayOf()) // Returns true if the two specified arrays are structurally equal to one another,
        contentDeepEquals(arrayOf()) // Returns true if the two specified arrays are deeply equal to one another,
        contentHashCode() // Returns a hash code based on the orchid.contents of this array as if it is List.
        contentDeepHashCode() // like contentHashCode(), And Nested arrays are treated as lists too.
        contentToString() // Returns a string representation of the orchid.contents of the specified array as if it is List.
        contentDeepToString() // like contentToString, And Nested arrays are treated as lists too.
        isArrayOf<String>() // Checks if array can contain element of type T.
        copyOf(3) // Returns new array which is a copy of the original array, resized to the given newSize.
        copyOfRange(0,3) // Returns a new array which is a copy of the specified range of the original array.
        fill('a') // Fills this array or its subrange with the specified element value.
        reversedArray() // Returns an array with elements of this array in reversed order.
        sliceArray(0..3) // Returns an array containing elements at indices in the specified indices range.
        union(arrayListOf()) // Returns a set containing all distinct elements from both collections.
        intersect(arrayListOf()) // Returns a set containing all elements that are contained by both this array and the specified collection.
        subtract(arrayListOf()) // Returns a set containing all elements that are contained by this array and not contained by the specified collection.
        filterIsInstance<Char>() // Returns a list containing all elements that are instances of specified class, this normally work in shuffled collection.
        filterIsInstanceTo(mutableListOf('1','2','3')) // Appends all elements that are instances of specified class to the given destination -> [1,2,3,a,b,c,d,e,f,...] // note:the elements that happens is supposed to be as same type.
        distinct() // Return Set containing.
        distinctBy {it.lowercase()}
        joinTo(StringBuffer("***"),"-", "<", ">", 5, "...!") // <***a-b-c-d-e-...!>
        joinToString("-", "<", ">", 5, "...!") // <a-b-c-d-e-...!>
        plus('1') // Returns an array containing all elements of the original array and then all elements of the given elements array.
        plusElement('3') /* Returns an array containing all elements of the original array and then the given element.
         well then what's the different between this and plus,
         if you get back to the resource you'll see this is inline function return the operator function plus and all the operations happen in this last. */
        sort() // Sorts the array in-place according to the natural order of its elements.
        sortBy { it.uppercaseChar() }
        sortWith { a, b -> a.compareTo(b) } // TODO: 10/06/2021
        sorted() // Returns a list of all elements sorted according to their natural sort order.
        sortedBy{ it==it } // Return 'it' in the last of this sequence.
        sortedWith { a, b -> a.compareTo(b) } // TODO: 17.01.2021
        sortedArray() // Like sorted().
        sortedArrayWith { a, b -> a.compareTo(b) } // TODO: 17.01.2021
        sortedDescending() // Returns a list of all elements sorted descending according to their natural sort order.
        sortedByDescending { it==it } // Returns a list of all elements sorted descending according to natural sort order of the value returned by specified selector function.
        sortedArrayDescending() // Returns an array with all elements of this array sorted descending according to their natural sort order.
    }

    //arrayOfNulls. Returns an array of objects of the given type with the given size, initialized with null values.
    val arrThree = arrayOfNulls<Int>(5)
    arrThree.requireNoNulls() // Returns an original collection containing all the non-null elements, which mean if this collection has null elements it will exception them.

    // emptyArray.
    val arrFour = emptyArray<Any>()

    // Array that has function type.
    val arrayFun : Array<(Int) -> Int> = arrayOf({ a:Int -> a*a})

    // Array that has Array type.
    val arrayArr : Array<Array<String>> = arrayOf(arrayOf(""),arrayOf(""))

    //  out/in type. todo
    val arrayPro : Array<in Int> = Array(5){ 0 }

    // ArrayDeque. Resizable-array implementation of the deque data structure.
    // The name deque is short for "double ended queue" and is usually pronounced "deck".
    // The collection provide methods for convenient access to the both ends.
    val arrDeq = ArrayDeque<Any>(3)
    with(arrDeq){
        addFirst("...")
        addLast("...")
        first()
        last()
        firstOrNull()
        lastOrNull()
        removeFirst()
        removeLast()
        removeFirstOrNull()
        removeLastOrNull()
    }
}