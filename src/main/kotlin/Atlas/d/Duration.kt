package Atlas.d

import kotlin.time.*
import kotlin.time.Duration

private class Duration {

    @ExperimentalTime
    fun main() {
        val duration = Duration

        duration.INFINITE.run {
            this.absoluteValue // Returns the absolute value.
            this.toDouble(DurationUnit.NANOSECONDS) // How much Nano's or other duration in this value.
            this.div(0) // (÷)
            this.times(0) // (×)
            this.minus(Duration.ZERO) // (-)
            this.plus(Duration.ZERO) // (+)
            this.isFinite()
            this.isInfinite()
            this.isPositive()
            this.isNegative()
            this.unaryMinus() // Returns the negative of this value.
            this.toIsoString() // Returns an ISO-8601 based string representation of this duration, PT(presented).
            this.toInt(DurationUnit.SECONDS) // Return converting integer number.
            this.toDouble(DurationUnit.SECONDS) // Return converting double number
            this.toString() // Return the converted value by the low ... until the seconds,(`d`,`h`,`m`,`s`,`ms`,`us`,or`ns`).
            this.inWholeNanoseconds // Convert to Nano's.
            this.inWholeMilliseconds // Convert to Mill's.
            this.toComponents { days, hours, minutes, seconds, nanoseconds -> this } // TODO
        }
        duration.ZERO.run {
        }
        /** Converts the given time duration [value] expressed in the specified [sourceUnit] into the specified [targetUnit]. */
        duration.convert(value = 1.0, sourceUnit = DurationUnit.DAYS, targetUnit = DurationUnit.HOURS)

    }
}
