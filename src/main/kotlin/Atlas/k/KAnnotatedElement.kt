package k

import kotlin.reflect.KAnnotatedElement

interface KAnnotatedElement{
 var kAnnotatedElement: KAnnotatedElement
fun main() {
    kAnnotatedElement.annotations
}
}