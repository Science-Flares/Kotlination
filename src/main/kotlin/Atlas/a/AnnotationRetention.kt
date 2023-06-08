package Atlas.a

import kotlin.annotation.AnnotationRetention.*

private enum class AnnotationRetention { ;
    init {
        BINARY
        RUNTIME
        SOURCE
    }
}