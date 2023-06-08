package k

import kotlin.reflect.KType
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.isSupertypeOf
import kotlin.reflect.full.withNullability
import kotlin.reflect.jvm.javaType
import kotlin.reflect.jvm.jvmErasure

interface KType {
     var kType: KType
    fun main() {
        kType.run {
            this.arguments
            this.classifier
            this.isMarkedNullable
            this.javaType
            this.jvmErasure
            this.isSubtypeOf(kType)
            this.isSupertypeOf(kType)
            this.withNullability(true)
        }
    }
}