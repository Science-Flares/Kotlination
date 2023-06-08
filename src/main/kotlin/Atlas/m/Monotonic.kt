package m

import kotlin.time.ExperimentalTime
import kotlin.time.TimeSource
@Deprecated("don't have attachment!")
object Monotonic {
    @ExperimentalTime
    lateinit var monotonic: TimeSource.Monotonic
    @ExperimentalTime
    fun main() {
        monotonic
    }
}
