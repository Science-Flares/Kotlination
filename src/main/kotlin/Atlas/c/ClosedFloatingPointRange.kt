package Atlas.c

import kotlin.ranges.ClosedFloatingPointRange

private class ClosedFloatingPointRange {
    lateinit var closedFloatingPointRange: ClosedFloatingPointRange<Int>

    init {
        closedFloatingPointRange.lessThanOrEquals(1, 2) // -> 1 <= 2
    }
}