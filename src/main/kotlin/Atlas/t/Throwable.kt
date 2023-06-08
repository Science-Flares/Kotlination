package Atlas.t

import kotlin.Throwable

class Throwable{
lateinit var throwable: Throwable
    init {
    throwable.run {
        this.localizedMessage
        this.stackTrace
        this.suppressed
        this.cause
        this.message
        this.addSuppressed(Throwable())
        this.fillInStackTrace()
        this.initCause(Throwable())
        this.printStackTrace()
    }
}
}