package AtlasJ

import java.util.*

@Suppress("UNREACHABLE_CODE")
class JSortedMap {
    lateinit var sortedMap: SortedMap<Any,Any>
    init {
        sortedMap.run {
            this.comparator()
            this.firstKey()
            this.lastKey()
            this.headMap("")
            this.subMap("","")
            this.tailMap("")
        }
    }
}