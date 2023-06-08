package Atlas.k

import kotlin.reflect.full.*
import kotlin.reflect.jvm.jvmName

interface KClass {
   fun main() {
      val kClass = this::class
         with(kClass){
            this.constructors // TODO: 29/05/2021
            this.primaryConstructor // TODO: 29/05/2021
            this.isAbstract // abstract class.
            this.isCompanion // companion object.
            this.isData // data class.
            this.isOpen // open class.
            this.isFinal // mean can't inheritance or overriding this class.
            this.isInner // inner class.
            this.isSealed // sealed class.
            this.nestedClasses // the classes inside this class.
            this.objectInstance // TODO: 29/05/2021
            this.sealedSubclasses // The sealed classes that inheritance this class.
            this.simpleName // String
            this.jvmName // java.lang.String
            this.qualifiedName // kotlin.String
            this.supertypes // The inheritance classes and interface.
            this.superclasses // -> supertypes.
            this.allSuperclasses // TODO: 29/05/2021
            this.allSupertypes // TODO: 29/05/2021
            this.typeParameters // TODO: 29/05/2021
            this.visibility // PUBLIC | PROTECTED | PRIVET | INTERNAL
            this.java // class java.lang.String
            this.javaObjectType // -> java
            this.javaPrimitiveType // null
            this.companionObject // class kotlin.String$Companion
            this.companionObjectInstance // hashcode.
            this.members // A member is a function and properties that is defined inside a class or object.
            this.declaredMembers // TODO: 29/05/2021
            this.functions // All The functions inside this class.
            this.declaredFunctions // functions that user have built them itself.
            this.staticFunctions
            this.memberFunctions // that functions inside this class
            this.declaredMemberFunctions // TODO: 29/05/2021
            this.memberExtensionFunctions // The extension function inside this class.
            this.declaredMemberExtensionFunctions // TODO: 29/05/2021
            this.staticProperties // TODO: 29/05/2021
            this.memberProperties // The Properties on this class
            this.declaredMemberProperties // TODO: 29/05/2021
            this.memberExtensionProperties // TODO: 29/05/2021
            this.declaredMemberExtensionProperties // TODO: 29/05/2021
            this.cast(0) // TODO: 29/05/2021
            this.safeCast(0) // TODO: 29/05/2021
            this.isSubclassOf(String::class) // TODO: 29/05/2021
            this.isSuperclassOf(String::class) // TODO: 29/05/2021
            this.createInstance() // TODO: 29/05/2021
            this.isInstance("") // TODO: 29/05/2021
        }
    }
}
