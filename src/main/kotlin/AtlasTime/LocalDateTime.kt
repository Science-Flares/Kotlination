package AtlasTime

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime

class LocalDateTime {
    init {
        LocalDateTime.parse("").apply {
            year
            monthNumber
            month
            dayOfMonth
            dayOfWeek
            dayOfYear
            hour
            minute
            second
            nanosecond
            this.date
//            this.toInstant()
            this.toJavaLocalDateTime()
        }

    }
}