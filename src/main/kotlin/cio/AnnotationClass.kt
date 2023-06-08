@file:Nationalistic


package cio

import kotlin.annotation.AnnotationRetention.*
import kotlin.annotation.AnnotationTarget.*
import kotlin.concurrent.thread
import kotlin.jvm.Throws
import kotlin.system.exitProcess


// Annotations are means of attaching metadata to code

@Target(
    CLASS,
    ANNOTATION_CLASS,
    TYPE_PARAMETER,
    PROPERTY,
    FIELD,
    LOCAL_VARIABLE,
    VALUE_PARAMETER,
    CONSTRUCTOR,
    FUNCTION,
    PROPERTY_GETTER,
    PROPERTY_SETTER,
    TYPE,
    EXPRESSION,
    FILE,
    TYPEALIAS
)
@Retention(SOURCE) // CLASS, RUNTIME.
annotation class Nationalistic(
    @param:Nationalistic val ytrkmlk:String = "",
    @property:Nationalistic val utkkgvh:String = "",
    @field:Nationalistic val txfgxh:String = "",
    @get:Nationalistic val tfjfjh:String = "",
//    @setparam:Nationalistic val hgkgkoni:String = ""
) {

    companion object {
        @property:Suppress("unused") val jgfhgk = 0

        @set:Nationalistic var qfqlsid:Int = 0

        val jtdkhgch:Int @get:Nationalistic get() = 0
    }
//@delegate:Target
//@setparam:Target
//@receiver:Target

/*
Kotlin supports the following values of the use-site targets that correspond to:

delegate – a field storing a delegated property
field – a field generated for a property
file – a class containing top-level functions and properties defined in that file
get/set – the property getter/setter
param – a constructor parameter
property – the Kotlin’s property, it is not accessible from Java code
receiver – the receiver parameter of an extension function or property*/
}
