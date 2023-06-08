package AtlasTime

import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate

class LocalDate {
    init {
        LocalDate.parse("").apply {
            this.dayOfMonth
            this.dayOfWeek
            this.dayOfYear
            this.month
            this.monthNumber
            this.year
//            this.atStartOfDayIn()
//            this.atTime()
//            this.daysUntil()
//            this.minus()
//            this.plus()
//            this.monthsUntil()
//            this.periodUntil()
//            this.yearsUntil()
            this.toJavaLocalDate()
//            this.until()
        }
    }
}