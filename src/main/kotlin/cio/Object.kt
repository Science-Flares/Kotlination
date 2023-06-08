package cio

// Normally The object have only one usage,
// And this the whole different between the class.
private object Object  {

    val str : Any by lazy { "..." }

    // Anonymous Object.
    private val anonymousObject = object {/*...*/}

}