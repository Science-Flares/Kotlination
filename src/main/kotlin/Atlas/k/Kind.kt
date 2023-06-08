package Atlas.k

import kotlin.reflect.KParameter

class Kind {
    lateinit var kind: KParameter.Kind
    init {
        KParameter.Kind.EXTENSION_RECEIVER
        KParameter.Kind.INSTANCE
        KParameter.Kind.VALUE
    }
}