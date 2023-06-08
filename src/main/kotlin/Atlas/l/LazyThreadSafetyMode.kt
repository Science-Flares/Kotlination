package Atlas.l

import kotlin.LazyThreadSafetyMode

private class LazyThreadSafetyMode {
    init {
        LazyThreadSafetyMode.NONE
        LazyThreadSafetyMode.PUBLICATION
        LazyThreadSafetyMode.SYNCHRONIZED
    }
}