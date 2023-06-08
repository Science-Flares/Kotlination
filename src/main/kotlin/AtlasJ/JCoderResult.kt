package AtlasJ

import java.nio.charset.CoderResult

class JCoderResult {
    private lateinit var coderResult: CoderResult

    init {
        CoderResult.OVERFLOW
        CoderResult.UNDERFLOW
        CoderResult.malformedForLength(1)
        CoderResult.unmappableForLength(1)

        with(coderResult){
            this.isError
            this.isMalformed
            this.isOverflow
            this.isUnderflow
            this.isUnmappable
            this.length()
            this.throwException()
        }
    }
}