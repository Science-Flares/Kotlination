package cio

// The abstract keyword use for make the class not allowed to create objects from,
// This allowed just for inheritance and not necessarily to overloads the the methods inside.
internal abstract class AbstractClass constructor(name: String, rate: Int) {

    // The secondary constructor can inheritance the other constructor in the same class.
    protected constructor(id: Int = 0) : this("unknown", 10) {

    }
    init {
        this.Inner().kot
        println("...")
    }
    // local class.
    // When you create class inside class and you want to get the functions inside,
    // this class you'll need to add inner keyword to the inside class.
    inner class Inner : AbstractClass() {
        val kot = KotlinVersion.CURRENT
    }
    //
    open fun somefun() = KotlinVersion.CURRENT.major

}