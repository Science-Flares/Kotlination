package mockk

import io.mockk.CapturingSlot

class CapturingSlot_<K:Any> {
   lateinit var kk : CapturingSlot<K>

    init {
        kk.run {
            captured
            isNull
            isCaptured
            clear()
        }
    }
 }