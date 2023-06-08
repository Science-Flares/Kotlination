package cio


// In kotlin language using the interface just for Implementing a class or object.
// the interface can't inheritance a class, but can inheritance many interfaces.

interface Interface {
    // property.
    val stunning: Int get() = 100

    // function.
    fun inter() = "interface"

    // Generic Interface.
    interface GenericInterface<EE> : List<EE>, Iterator<EE>, Iterable<EE> {

        // property.
        val lovely: EE? get() = null

        // function.
        fun function(a: EE): EE = a
    }

    //
    fun interface vidas {
        fun ss()
    }

}
