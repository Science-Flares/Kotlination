package AtlasJ

import java.io.FileWriter
import java.io.Reader
import java.io.Writer

class `JReader&Writer` {
    lateinit var reader: Reader
    lateinit var writer: Writer

    init {
        Reader.nullReader()

        //
        with(reader) {
            this.read() // Return codePoint of the first index.
            this.ready() // Check if this object can read.
            this.readLines() // Return List<String> of lines.
            this.readText() // Return all the text.
            this.mark(1) // todo
            this.markSupported() // todo
            this.close()
            this.reset()
            this.skip(1L) // Skip n indexes.
            this.transferTo(FileWriter("")) // todo
            this.copyTo(FileWriter("")) // todo
            this.buffered().run {
            }
            this.forEachLine {it}
            this.use {it} // it as object.
            this.useLines {it} // it as a line.
        }

        //
        writer.run {
            this.write(1)
            this.buffered()
        }

    }
}