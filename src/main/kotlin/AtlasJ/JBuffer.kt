package AtlasJ

import java.nio.*

class JBuffer {
    lateinit var buffer: Buffer
    lateinit var charBuffer: CharBuffer
    lateinit var byteBuffer: ByteBuffer

    init {
        with(buffer){
            this.isDirect
            this.isReadOnly
            this.array()
            this.arrayOffset()
            this.capacity() /* Inside the Buffer, there is a backing byte[] or something that behaves much like one.
                               The capacity is its size.
                               The capacity indexes the first slot past the end of the buffer.*/
            this.limit() /* When filling the buffer, the limit is the same as the capacity.
                            When emptying the buffer, it is one past the last filled
                            byte in the buffer.*/
            this.position() /* When filling the buffer, the position points just past the last byte filled in the buffer.
                               When emptying the buffer, the
                               position points just past the last byte written from the buffer.*/
            this.duplicate()
            this.clear()
            this.flip()
            this.hasArray()
            this.hasRemaining()
            this.mark()/* The mark is an optional bookmark to let you record an interesting spot in the Buffer
                          that you want to return to later.
                          When you take a mark() it records current position and when you call reset() it restores that position. */
            this.remaining()
            this.reset()
            this.rewind()
            this.slice()
        }

        CharBuffer.allocate(0)
        CharBuffer.wrap(charArrayOf())
        with(charBuffer){
            this[0]
            this.get()
            this.put('0')
            this.asReadOnlyBuffer()
            this.compact()
            this.mismatch(null)
            this.order()
        }

        /* Bytebuffer, What is:
         The biggest problem in understanding ByteBuffer is presuming that it is cleverer than it really is.
         ByteBuffer is a baffling class.
          It is a bit like a RAM-based RandomAccessFile.
          It is also a bit like a ByteArrayList without the auto_grow feature, to let you deal with partly filled byte[] in a consistent way.
         It looks at first as if it might be a traditional circular squirrel cage buffer but it is not.
         There is no circularity.
         Nor is it some kind of virtual moving window on a file.
         It is not like a pipe, designed to simulate a giant stream with a finite buffer.
         There is no queuing of any kind.
         It is not even as clever as COBOL double buffering, which you might suspect by it having a flip method.
         It has no asynchronous look-ahead.
         It is extremely low level. nio should probably have been call LLIO (low level io).
         You must explicitly clear and fill the buffer and explicitly read/or write it.
          It is up to you to avoid overfilling the buffer.
         It is a very mundane buffer. The only thing that makes it much different from a raw byte[] is the way may be backed
         something that only looks like a byte[] but is not really, e.g. An entire memory mapped file or direct I/O buffer contain
        part of a file.*/
        /**
        [How_It_Works]
        You would expect ByteBuffer to have a length() method, it does not. Instead it has a several length-like concepts:
        mark <= position <= limit <= capacity.
        Just what do these term mean in English? Working right to left:
        */
        ByteBuffer.allocate(0)
        ByteBuffer.allocateDirect(0)
        ByteBuffer.wrap(byteArrayOf())
        with(byteBuffer){
            /** ByteBuffer has All the CharBuffer Attachment an addition to...*/
            this.char // and all others variables except string.
            this.putChar('0') // and all others variables except string.
            this.alignedSlice(0)
            this.alignmentOffset(0,0)
            this.asCharBuffer() // and all others variables except string.
        }

        /*StringBuffer in Java is a mutable sequence of characters.
        The difference between String and StringBuffer is that the StringBuffer is mutable
        while String is immutable.
        it means that the StringBuffer object’s content can be changed
        or modified using its methods.*/

    }
}