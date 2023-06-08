package AtlasTime

import kotlinx.datetime.TimeZone
import kotlinx.datetime.toJavaZoneId

class TimeZone {
    init {
        TimeZone.apply {
            this.UTC
            this.availableZoneIds
            this.currentSystemDefault()
            this.of("").apply {
                this.id
//                this.offsetAt()
                this.toJavaZoneId()
            }
        }
    }
}