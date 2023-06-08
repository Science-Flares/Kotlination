package Atlas.c

import kotlin.ranges.ClosedRange

private interface ClosedRange {
     var closedRange: ClosedRange<Int>

    fun main(){
        closedRange.run {
            this.endInclusive
            this.start
            this.contains(1)
            this.isEmpty()
        }
    }
}