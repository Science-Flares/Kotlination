package coroutines

import kotlinx.coroutines.AbstractCoroutine
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@coroutines.InternalCoroutinesApi
class AbstractCoroutine {
    @OptIn(InternalCoroutinesApi::class)
    @coroutines.InternalCoroutinesApi
    lateinit var abstractCoroutine: AbstractCoroutine<*>

     init {
         abstractCoroutine.start()

     }

}