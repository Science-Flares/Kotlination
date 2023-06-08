package AtlasJ

import java.time.format.ResolverStyle
import java.time.temporal.*
import java.util.*

class JTemporal {
    lateinit var temporal: Temporal
    lateinit var temporalAccessor: TemporalAccessor
    lateinit var temporalUnit: TemporalUnit
    lateinit var temporalAmount: TemporalAmount
    lateinit var temporalAdjuster: TemporalAdjuster
    lateinit var temporalField: TemporalField

    init {
        temporal.run {
            this.isSupported(temporalUnit)
            this.minus(temporalAmount)
            this.plus(temporalAmount)
            this.with(temporalAdjuster)
            this.until(this,temporalUnit)
        }
        temporalAmount.run {
            this.units
            this.get(temporalUnit)
            this.addTo(temporal)
            this.subtractFrom(temporal)
        }
        temporalAccessor.run {
            this.get(temporalField)
            this.getLong(temporalField)
            this.isSupported(temporalField)
            this.range(temporalField)
        }
        temporalUnit.run {
            this.duration
            this.isDateBased
            this.isDurationEstimated
            this.isTimeBased
            this.addTo(null,0L)
            this.between(temporal,temporal)
            this.isSupportedBy(temporal)
        }
        temporalAdjuster.adjustInto(temporal)
        temporalField.run {
            this.baseUnit
            this.isDateBased
            this.isTimeBased
            this.rangeUnit
            this.range()
            this.adjustInto(null,0L)
            this.getDisplayName(Locale.CANADA)
            this.getFrom(temporalAccessor)
            this.isSupportedBy(temporalAccessor)
            this.rangeRefinedBy(temporalAccessor)
            this.resolve(mutableMapOf(),temporalAccessor,ResolverStyle.SMART)
        }
    }
}