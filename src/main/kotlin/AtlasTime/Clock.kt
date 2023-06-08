package AtlasTime

import kotlinx.datetime.Clock
import kotlinx.datetime.asTimeSource
import kotlinx.datetime.todayAt
import kotlin.time.ExperimentalTime

@Suppress("UNUSED_EXPRESSION", "EXPERIMENTAL_IS_NOT_ENABLED")
interface Clock {
    @OptIn(ExperimentalTime::class)
    companion object{
        init {
            Clock.System.now().apply {
            }
            Clock::todayAt
            Clock::asTimeSource
        }
    }
}