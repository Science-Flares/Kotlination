package Atlas.k

import kotlin.reflect.KVisibility

private class KVisibility {
    val some = this::class.visibility // get the visibility of class.
    init {
        // the visibility can be one of those 4 odds:
        KVisibility.INTERNAL
        KVisibility.PRIVATE
        KVisibility.PROTECTED
        KVisibility.PUBLIC
    }
}