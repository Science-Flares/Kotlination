package Atlas.i

import kotlin.collections.IndexedValue

interface IndexedValue {
     var indexedValue: IndexedValue<Int>
    fun main() {
       with(indexedValue){
           index
           value
       }
    }
}