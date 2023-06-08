package coroutines.internal

import kotlinx.coroutines.internal.OpDescriptor

class OpDescriptor {
    lateinit var opDescriptor:OpDescriptor

    init {
        opDescriptor.perform(1)
    }

}