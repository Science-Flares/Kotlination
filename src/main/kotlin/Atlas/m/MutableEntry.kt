package m

class MutableEntry {
    lateinit var mutableEntry: MutableMap.MutableEntry<Int,Int>
    init {
        mutableEntry.setValue(1)
    }
}