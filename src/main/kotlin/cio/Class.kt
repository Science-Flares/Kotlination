package cio

// The normally class can create objects many times from it.

// In the kotlin you can inheritance only one class,
// and you can inheritance many interfaces.
private open class Class(): AbstractClass() , Interface {
    // Use final keyword to break the overloading function.
    final override fun somefun(): Int {
        // Use super keyword for implement the overloading function.
        return super.somefun()
    }

    // If you need to write a function that can be called without having a class instance but needs access
    // to the internals of a class (a factory method), you can write it as a member of an
    // object declaration inside that class.
    companion object {
        fun pass() = 35456345
    }
    // Note: the companion object not allowed in the inner class.

    // Generic class.
     class General<out T,in E,Y>{
        // out using in the variable type and function return.
        val genericProperty: T? = null

        // in using only in parameter of the function.
        fun genericFunction(ss: E): T? = null

        // in and out in the same time.
        val genericArray : Array<Y?>? = null
    }
    // inline class.
    // Similar of inline function job, but in inline class you mast create only one variable param.
    @JvmInline
    value class InClass(val flo:Float)

    // Anonymous Class (Any).
    private val anonymousClass = object:Any() {/*...*/ }

}

