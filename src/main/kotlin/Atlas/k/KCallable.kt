package k

import kotlinx.coroutines.runBlocking
import kotlin.reflect.KCallable
import kotlin.reflect.full.*
import kotlin.reflect.jvm.isAccessible

interface KCallable {
     var kCallable: KCallable<*>
    fun main() {
        kCallable.run {
            this.name
            this.isAbstract
            this.isFinal
            this.isOpen
            this.isSuspend
            this.parameters
            this.returnType
            this.typeParameters
            this.visibility
            this.call()
            this.callBy(mapOf())
            this.run {
                runBlocking {
                    callSuspend()
                    callSuspendBy(mapOf())
                }
            }
            this.isAccessible
            this.extensionReceiverParameter
            this.instanceParameter
            this.valueParameters
            this.findParameterByName("")
        }
    }
}