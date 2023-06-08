package Atlas.c

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.getPolymorphicElement
import kotlin.coroutines.minusPolymorphicKey

interface CoroutineContext {
    val cc : CoroutineContext
    val ele : CoroutineContext.Element
    val ke : CoroutineContext.Key<*>

    @ExperimentalStdlibApi
    fun some() {
        with(cc){
            this.fold(0) { i: Int, element: CoroutineContext.Element -> i }
            minusKey(ke)
            plus(this)
            this[ke]
        }
        with(ele){
            key
            minusPolymorphicKey(ke)
            getPolymorphicElement(ke)
        }
    }
}