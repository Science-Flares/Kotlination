package AtlasTime

import kotlinx.datetime.DateTimeUnit

class DateTimeUnit {
    init {
        with(DateTimeUnit){
            NANOSECOND
            MICROSECOND
            MILLISECOND
            SECOND
            MINUTE
            HOUR
            DAY
            WEEK
            MONTH
            QUARTER
            YEAR
            CENTURY
        }
    }
}