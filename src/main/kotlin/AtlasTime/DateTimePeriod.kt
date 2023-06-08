package AtlasTime

import kotlinx.datetime.DateTimePeriod

class DateTimePeriod {
    init {
        DateTimePeriod.parse("").apply {
            this.nanoseconds
            this.seconds
            this.minutes
            this.hours
            this.days
            this.months
            this.years
        }
    }
}