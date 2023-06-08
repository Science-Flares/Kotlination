package t

import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.TimeMark

abstract class TimeMark {
    @ExperimentalTime
    lateinit var timeMark: TimeMark
    @ExperimentalTime
    fun main() {
        timeMark.run {
            this.elapsedNow()
            this.hasNotPassedNow()
            this.hasPassedNow()
            this.minus(Duration.ZERO)
            this.plus(Duration.ZERO)
        }
    }
}