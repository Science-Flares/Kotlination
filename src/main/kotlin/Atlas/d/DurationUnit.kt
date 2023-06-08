package Atlas.d

import java.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toTimeUnit

private class DurationUnit {
    @ExperimentalTime
    lateinit var durationUnit: DurationUnit

    @ExperimentalTime
    fun main() {
        durationUnit.toTimeUnit().run {
            toNanos(0L)
            toSeconds(0L)
            toMinutes(0L)
            toMillis(0L)
            toMicros(0L)
            toHours(0L)
            toDays(0L)
            timedWait("", 0L)
            toChronoUnit()
            timedJoin(Thread(), 0L)
            sleep(0L)
            convert(Duration.ZERO)
        }

        DurationUnit.NANOSECONDS
        DurationUnit.MICROSECONDS
        DurationUnit.MILLISECONDS
        DurationUnit.SECONDS
        DurationUnit.MINUTES
        DurationUnit.HOURS
        DurationUnit.DAYS
    }
}