package collections

import java.util.*
import kotlin.collections.AbstractMap

/**
 A collection that holds pairs of objects (K and V) and supports efficiently retrieving
 the value corresponding to each key.
 Map keys are unique; the map holds only one value for each key.
*/

@Suppress("UNCHECKED_CAST")
internal fun maps(){
    // Map. read-write.
    val map: Map<Int,String> =  mapOf(1 to "one",2 to "two",3 to "three")
    with(map){
        size
        keys // Returns a read-only Set of all keys in this map.
        values // Returns a read-only Collection of all values in this map.
        entries // [1=one, 2=two, 3=three].
        getValue(1)
        getOrElse(1) { "some else" }
        getOrDefault(1,"default") //
        containsKey(1)
        containsValue("one")
        withDefault { it==1  } /* TODO: Returns a wrapper of this read-only map, having the implicit default value provided with the specified function defaultValue. */
        filterTo(mutableMapOf<Any, Any>()) { it == it }
        filterNotTo(mutableMapOf<Any, Any>()) { it == it }
        filterValues { it == it.uppercase(Locale.getDefault()) }
        filterKeys { it < 3 }
        mapKeys { entry: Map.Entry<Any, Any> -> entry } // {1=one=one, 2=two=two, 3=three=three}.
        mapKeysTo(mutableMapOf()) { entry: Map.Entry<Any, Any> -> entry }
        mapValues { entry: Map.Entry<Any, Any> -> entry } // {1=1=one, 2=2=two, 3=3=three}.
        mapValuesTo(mutableMapOf()) { entry: Map.Entry<Any, Any> -> entry }
        toMap()
        toMutableMap() // see the MutableMap next below.
        toSortedMap().run{
            /**@see AtlasJ.JSortedMap*/
        }
        forEach { (t, u) -> t }
        count() // Returns the number of entries in this map.
        filter { it.key > 0 }
        filterTo(mutableMapOf()) { it.key > 0 }
        flatMap { entries } // TODO: 04/12/2021
        flatMapTo(mutableListOf()) { entries } // TODO: 04/12/2021
        isNotEmpty() // Returns true if this map is not empty.
        iterator() // Returns an Iterator over the entries in the Map.
        map { } // Returns a list containing the results of applying the given transform function to each entry in the original map.
        mapTo(mutableListOf()){ it }
        mapNotNull { it } // ..., except the nulls
        mapNotNullTo(mutableListOf()) { it }
        maxOf { it.value } // else minOf()
        maxByOrNull { it.value }
        maxOfOrNull { it.value }
        maxOfWith ({ sec ,fir -> fir.compareTo(sec) },{it.value}) //  compare between tow elements.
        maxOfWithOrNull({ sec,fir->fir.compareTo(sec)}) { it.value } // ..., or null if there are no entries.
        toList() // Returns a List containing all key-value pairs.
        firstNotNullOf { it.value=="" }
        firstNotNullOfOrNull { it.value=="" }
    }


    // Map.Entry
    val entry = map as Map.Entry<Int,Int>
    entry.run {
        this.value // return the value of this pair.
        this.key // return the key of this pair.
        this.component1() // key.
        this.component2() // value.
        this.toPair().run {
        }
    }

    // MutableMap. read-write.
    mutableMapOf<Int,String>().apply {
        compute(1) { i: Int, s: String? -> "$s-$i" } // one-1
        computeIfAbsent(5) { i: Int -> "$i is Absent" } // 5 is Absent.
        computeIfPresent(1) { i: Int, s: String -> "$s-$i" } // one-1, if the key out return null.
        this[4] = "four" //
        putAll(mapOf(5 to "five",6 to "six"))
        getOrPut(1) { "ONE" } //
        putIfAbsent(1,"one")
        merge(1,"value") { s, s2 -> s+s2 } // make operation between old value and new one.
        clear() // Removes all elements in this map.
        this.replace(1,"old","new") // return true if succeed, false if the key or value not correct.
        this.replaceAll { t, u -> when {
            t > 5 -> "..."
            else -> u
        } }
        this.remove(1,"...") // Removes the entry for the specified key only if it is mapped to the specified value.
                             // Returns: true if entry was removed
        this.iterator() // Returns a MutableIterator over the mutable entries in the MutableMap.
        this.withDefault { "..." } // TODO: 26/06/2021
    }

    // HashMap.
    hashMapOf(null to null)

    /*The Map is an interface, and HashMap is a class of the Java collection framework.
The Map interface can be implemented by using its implementing classes. In comparison, the HashMap class implements the Map interface.
The Map contains unique key-pair values. But, the HashMap can hold duplicate values.
The Map does not allow null values. But the HashMap can have one null key and multiple values.
The Map has two implementations, which are HashMap and TreeMap. Whereas HashMap implements Map interface and extends AbstractMap class.
There is no difference between the Map and HashMap objects.
*/
    // LinkedHashMap.
    linkedMapOf(null to null)



    // MutableEntry.
    object : MutableMap.MutableEntry<Nothing, Nothing> {
        override val key: Nothing
            get() = TODO("Not yet implemented")
        override val value: Nothing
            get() = TODO("Not yet implemented")

        override fun setValue(newValue: Nothing): Nothing {
            TODO("Not yet implemented")
        }
    }

    // AbstractMap.
    object : AbstractMap<Nothing,Nothing>() {
        override val entries: Set<Map.Entry<Nothing, Nothing>>
            get() = TODO("Not yet implemented")
    }

    // AbstractMutableMap.
    object : AbstractMutableMap<Nothing,Nothing>(){
        override fun put(key: Nothing, value: Nothing): Nothing? {
            TODO("Not yet implemented")
        }

        override val entries: MutableSet<MutableMap.MutableEntry<Nothing, Nothing>>
            get() = TODO("Not yet implemented")
    }
}