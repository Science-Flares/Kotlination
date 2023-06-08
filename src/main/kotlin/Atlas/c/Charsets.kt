package Atlas.c

import kotlin.text.Charsets

private object Charsets {
    lateinit var charsets: Charsets

    // charsets.
    init {
        with(charsets) {
        this.US_ASCII.run {
            /** @see AtlasJ.JCharset */
        }
            this.ISO_8859_1
            this.UTF_8
            this.UTF_16
            this.UTF_16BE
            this.UTF_16LE
            this.UTF_32
            this.UTF_32BE
            this.UTF_32LE
        }
    }
}