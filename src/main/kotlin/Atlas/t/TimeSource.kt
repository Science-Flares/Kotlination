package t

import kotlin.time.ExperimentalTime
import kotlin.time.TimeSource

interface TimeSource {
    @ExperimentalTime
     var timeSource: TimeSource
    @ExperimentalTime
    fun main() {
        timeSource.markNow()
    }
}