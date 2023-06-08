import kotlinx.coroutines.runBlocking
import kotlin.reflect.full.*
import kotlin.reflect.jvm.*

/*
Reflection is a set of language and library features that allows for introspecting the structure of
your own program at runtime.
Kotlin makes functions and properties rst-class citizens in the
language, and introspecting them (i.e. learning a name or a type of property or function at
runtime) is closely intertwined with simply using a functional or reactive style.
*/
lateinit var reflection:String
@OptIn(ExperimentalReflectionOnLambdas::class)
@ExperimentalStdlibApi
fun myReflection () {

    with(::reflection){
        get()
        getDelegate() // todo
        annotations // todo
        getter // todo
        isAbstract
        isConst
        isFinal
        isOpen
        isLateinit
        isSuspend // work with the function.
//        isInitialized
        isAccessible //
        visibility
        name
        parameters
        typeParameters // todo
        valueParameters // todo
        instanceParameter // todo
        extensionReceiverParameter // todo
        returnType // work with the functions.
        javaField // private static final java.lang.String StringKt.go
        javaClass.run {
        }
        javaGetter //public static final java.lang.String StringKt.getGo()
        call() // todo
        callBy(mapOf()) // todo
        runBlocking {
           this@with.callSuspend(0) // todo
           this@with.callSuspendBy(mapOf()) // todo
        }
        hasAnnotation<JvmName>() // TODO
        findAnnotation<JvmName>() // TODO
        findParameterByName("") // TODO
        reflect() // TODO
    }

}

