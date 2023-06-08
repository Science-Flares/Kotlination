package AtlasJ

import java.nio.ByteBuffer
import java.nio.CharBuffer
import java.nio.charset.Charset
import java.nio.charset.CharsetDecoder
import java.nio.charset.CharsetEncoder
import java.nio.charset.CodingErrorAction
import java.nio.charset.spi.CharsetProvider

class JCharset {
   private lateinit var charset: Charset
   private lateinit var charsetDecoder: CharsetDecoder
   private lateinit var charsetEncoder: CharsetEncoder
   private lateinit var charsetProvider: CharsetProvider

    init {
        Charset.availableCharsets().run {
        }

        Charset.defaultCharset()
        Charset.forName(null)
        Charset.isSupported(null        )

        //
        with(charset){
            this.isRegistered
            this.aliases() // TODO: 27/05/2021
            this.canEncode() // TODO: 27/05/2021
            this.contains(charset("..."))
            this.name() // name of this charset.
            this.displayName() // name of this charset.
            this.decode(ByteBuffer.allocate(0)).run {
            }
            this.encode("...").run {
            }
            this.newDecoder().run {
            }
            this.newEncoder().run {
            }
        }

        //
        with(charsetEncoder){
            this.averageBytesPerChar()
            this.canEncode('a')
            this.charset()
            this.encode(CharBuffer.wrap(charArrayOf()))
            this.flush(ByteBuffer.wrap(byteArrayOf())).run {
            }
            this.isLegalReplacement(ByteArray(0))
            this.malformedInputAction()
            this.maxBytesPerChar()
            this.onMalformedInput(CodingErrorAction.IGNORE)
            this.onUnmappableCharacter(CodingErrorAction.IGNORE)
            this.replaceWith(ByteArray(0))
            this.replacement()
            this.reset()
            this.unmappableCharacterAction()
        }

        //
        with(charsetDecoder){
            /** Decoder has All the Attachment Encoder an addition to...*/
            this.isAutoDetecting
            this.isCharsetDetected
            this.decode(ByteBuffer.wrap(byteArrayOf()))
            this.detectedCharset()
        }

        //
        with(charsetProvider){
            this.charsets()
            this.charsetForName("")
        }

    }

}