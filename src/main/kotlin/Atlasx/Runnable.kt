package coroutines

import kotlinx.coroutines.Runnable


class Runnable {
    lateinit var runnable: Runnable

    init {
        runnable.run()
    }
}