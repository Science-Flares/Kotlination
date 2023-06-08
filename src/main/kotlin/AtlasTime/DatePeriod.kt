package AtlasTime

import kotlinx.datetime.DatePeriod
import kotlinx.datetime.toJavaPeriod

class DatePeriod {
    init {
        DatePeriod.parse("").toJavaPeriod()
    }
}