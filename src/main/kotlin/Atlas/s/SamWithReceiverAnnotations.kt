package Atlas.s

import kotlin.script.extensions.SamWithReceiverAnnotations

annotation class SamWithReceiverAnnotations {

    // sample.
    companion object {
        @SamWithReceiverAnnotations("Suppress","Synchronized")
        private class Sample

        @JvmStatic
        fun main(args: Array<String>) {
            Sample()
        }
    }
}
