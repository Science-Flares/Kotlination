import kotlin.properties.Delegates

// The properties have two different terms.
// val: the value on val property not allowed changing.
private val ONE = 2020
fun main() {
//    ONE = 2021 // FIXME: error!
}
// the const mean constant or fix property using only on val in the objects and top-level property
// the const normally has Uppercase name.

// var: the value on var property allowed changing.
private var two = 100

// getter and setter.
private object setterandgetter{
        var three :Int = 0
            get() {
                return 0
            }
            set(value) {
                field = 10
            }
}
// lateinit.
lateinit var late:Any

// Using the property for creating object class or function or another property.
private val boss = Any()

// Create Extension property.
private val Any.go get() = 0
// this mean we're creating property for the Boss class out the class.

// Generic Extension property.
private val <Y> Array<Y>.fame: Y? get() = null

// Gave the property keyword name.
private lateinit var `var`:Any
// Note: this also can work with the function and the class.

// by.
private val some:Any? by lazy { null }
// or
private val del by Delegates
    .observable("") {
            property,
            oldValue,
            newValue ->
        newValue
}

/*
 const.
The `const` keyword is used to declare those properties which are immutable in nature.
`const` property following.
- must be at top-level or member of object or member of a companion object
- must be initialised with a `String` type or primitive type, No fun No class
- no custom getter
*/

const val con = "constant"

// Why use "const" when we can use "val"?
// It's all depending on your status,
// If you needed immutable value don't change even in the "runtime" (Strings,Numbers,Chars) use "const",
// And "const" don't allow any function and class and interface because all of them mutable even in the "runtime",
// Otherwise oyu can use only "val" for mutable value "compileTime" and "runtime".
