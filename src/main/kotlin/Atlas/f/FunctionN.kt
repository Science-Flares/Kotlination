package Atlas.f

import kotlin.jvm.functions.FunctionN

private interface FunctionN {
    companion object{
        private val <E> FunctionN<E>.funN get() = this.invoke()
    }
}