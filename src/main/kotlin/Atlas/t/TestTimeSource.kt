package Atlas.t

import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.TestTimeSource

class TestTimeSource {
    @ExperimentalTime
    lateinit var testTimeSource: TestTimeSource
    @ExperimentalTime
    fun main() {
        testTimeSource.plusAssign(Duration.ZERO)
    }
}