@file:Suppress("UNREACHABLE_CODE")

package AtlasJ

import java.io.InputStream
import java.io.OutputStream
import java.nio.file.SecureDirectoryStream
import java.util.stream.*
import javax.xml.stream.StreamFilter
import javax.xml.stream.XMLStreamReader


class   JStream {

    lateinit var stream: Stream<String>
    lateinit var intStream: IntStream
    lateinit var longStream: LongStream
    lateinit var doubleStream: DoubleStream
    lateinit var outputStream: OutputStream
    lateinit var baseStream: BaseStream<Int,*>
    lateinit var secureDirectoryStream: SecureDirectoryStream<String>
    lateinit var streamFilter: StreamFilter
    lateinit var inputStream: InputStream
    lateinit var xmlStreamReader: XMLStreamReader

    init {
        with(stream) {
            this.allMatch { it == it }
            this.anyMatch { it == it }
            this.noneMatch { it == it }
            this.count()
//        this.collect()
            this.distinct()
            this.dropWhile { it == it }
            this.filter { it == it }
            this.findAny()
            this.findFirst()
            this.flatMap {this}
            this.flatMapToDouble { doubleStream }
            this.flatMapToInt { intStream }
            this.flatMapToLong { longStream }
            this.forEach { it }
            this.forEachOrdered { it }
            this.limit(100L)
            this.map { it }
            this.mapToLong { 100L }
            this.mapToInt { 100 }
            this.mapToDouble { 100.0 }
            this.max { any, any2 -> 100 }
            this.min { any, any2 -> 100 }
            this.peek { it }
            this.reduce(null)
            this.skip(100L)
            this.sorted()
            this.takeWhile { it === it }
            this.toArray()
        }
        intStream.run {
            this.asDoubleStream()
            this.asLongStream()
            this.average()
            this.boxed()
            this.mapToObj { i: Int -> i }
            this.summaryStatistics()
        }
        baseStream.run {
            this.isParallel()
            this.parallel()
            this.iterator()
            this.onClose(Runnable { })
            this.sequential()
            this.spliterator()
            this.unordered()
        }
        inputStream.run {
            this.available()
            this.mark(100)
            this.markSupported()
            this.read()
            this.reader().run {
                this.encoding
            }
            this.readBytes()
            this.readNBytes(100)
            this.readAllBytes()
            this.reset()
            this.skip(100L)
            this.transferTo(outputStream)
            this.buffered()
            this.bufferedReader()
        }
        outputStream.run {
            this.write(100)
            this.writer().run {
                this.encoding
            }
            this.buffered(DEFAULT_BUFFER_SIZE)
            this.bufferedWriter(Charsets.UTF_8)
        }
        xmlStreamReader.run {
            this.attributeCount
            this.characterEncodingScheme
            this.elementText
            this.encoding
            this.eventType
            this.isCharacters
            this.isEndElement
            this.isStandalone
            this.isStartElement
            this.isWhiteSpace
            this.localName
            this.location
            this.name
            this.namespaceContext
            this.namespaceCount
            this.namespaceURI
            this.piData
            this.piTarget
            this.prefix
            this.text
            this.textStart
            this.textLength
            this.textCharacters
            this.version
            this.close()
            this.getAttributeName(0)
            this.getAttributeLocalName(0)
            this.getAttributeNamespace(0)
            this.getAttributePrefix(0)
            this.getAttributeType(0)
            this.getAttributeValue(0)
            this.getNamespacePrefix(0)
            this.getNamespaceURI(0)
            this.getProperty("")
            this.hasName()
            this.hasNext()
            this.hasText()
            this.next()
            this.nextTag()
            this.isAttributeSpecified(0)
            this.require(0, "", "")
            this.standaloneSet()
        }
        secureDirectoryStream.run {
            this.deleteDirectory("")
            this.deleteFile("")
//        this.getFileAttributeView()
            this.move("",null,"")
//        this.newByteChannel()
//        this.newDirectoryStream()
        }
        streamFilter.accept(xmlStreamReader)

    }
}