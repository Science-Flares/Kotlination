package mockk

import io.mockk.InternalPlatformDsl

object InternalPlatformDsl_ {
    init {
        with(InternalPlatformDsl){
            this.counter()
//            this.classForName()
            this.coroutineCall {  }
//            this.deepEquals()
//            this.dynamicCall()
//            this.dynamicGet()
//            this.dynamicSet()
//            this.dynamicSetField()
//            this.identityHashCode()
//            this.makeAccessible()
            this.threadLocal {  }
//            this.unboxChar()
        }
    }
 }
