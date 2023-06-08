package cio

/* Sealed Classes are used for representing restricted class hierarchies, when a value can have one
of the types from a limited set, but cannot have any other type.
They are, in a sense, an extension
of enum classes: the set of values for an enum type is also restricted, but each enum constant
exists only as a single instance, whereas a subclass of a sealed class can have multiple instances
which can contain state.
*/
// sealed class.
private sealed class SealedClass {

    // sub-classes.
    object First : SealedClass()
    data class Second(val int: Int) : SealedClass()

    // Usage.
    companion object {
        fun eval(seal: SealedClass) = when(seal) {
            is Second -> println("...")
            is First -> println("...")
        }
    }
}
