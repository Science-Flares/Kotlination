package Atlas.d
import kotlin.DeprecationLevel
private class DeprecationLevel {
    /**
     * This class used in the annotation class @Deprecated'()
     * that by default WARNING value.
     * */
    lateinit var deprecationLevel: DeprecationLevel

    init {
        deprecationLevel
        DeprecationLevel.ERROR
        DeprecationLevel.HIDDEN
        DeprecationLevel.WARNING
        DeprecationLevel.values()
    }
}