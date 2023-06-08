package Atlas.a
import kotlin.annotation.AnnotationTarget.*
/**
 This class work with the annotation class @Target(),
 and Contains the list of code elements which are the possible annotation targets
 */
private enum class AnnotationTarget {  ;
    init {
        ANNOTATION_CLASS
        CLASS
        CONSTRUCTOR
        EXPRESSION
        FIELD
        FILE
        FUNCTION
        LOCAL_VARIABLE
        PROPERTY
        PROPERTY_GETTER
        PROPERTY_SETTER
        TYPE
        TYPEALIAS
        TYPE_PARAMETER
        VALUE_PARAMETER
    }
}