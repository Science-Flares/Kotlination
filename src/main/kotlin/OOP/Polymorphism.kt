package OOP

/** polymorphism. mean using the function by different forms out the class. */

open class Polymorphism {
    open fun function() {
        println(KotlinVersion.CURRENT) // kotlin version.
    }

}

class KlassOne : Polymorphism(){
    override fun function() {
        super.function()
        println(KotlinVersion.CURRENT.patch)
    }
}

class KlassTwo : Polymorphism() {
    override fun function() {
        super.function()
        println(KotlinVersion.CURRENT.major)
    }

}

class Green{
    fun greenFunction(a: Polymorphism){
        a.function()
    }
}

fun main() {

    // polymorphism as simple.
    val poly = Polymorphism()
    poly.function()

    val one = KlassOne()
    one.function()

    val tow = KlassTwo()
    tow.function()

    // polymorphism as Array.
    val arr = Array(2){ Polymorphism() }
    arr[0] = KlassOne()
    arr[1] = KlassTwo()
    for (ss in 0..arr.size){
        arr[ss].function()
    }

    // another polymorphism forms.
    val s = Green()
    s.greenFunction(one)
    s.greenFunction(tow)
    // mean: the element of the function accept the 'Object' of class oop.Polymorphism,
    // and the classes they inherited it.

}

