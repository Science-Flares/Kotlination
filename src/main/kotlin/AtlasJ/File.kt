package AtlasJ

import java.io.*
import java.io.File.*
import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.nio.file.FileStore
import java.nio.file.FileSystem
import java.util.logging.FileHandler
import java.util.logging.Level
import java.util.logging.LogRecord
import javax.sound.midi.MidiFileFormat

private class File {
    lateinit var file: File
    lateinit var fileSystem: FileSystem
    lateinit var fileDescriptor: FileDescriptor
    lateinit var fileStore: FileStore
    lateinit var fileFormat: MidiFileFormat
    lateinit var fileChannel: FileChannel
    init {

        // File.
        pathSeparator
        pathSeparatorChar
        separator
        separatorChar
        createTempFile("","")
        listRoots()

        File("script").run {
            this.absoluteFile // Pathname.
            this.absolutePath // Pathname.
            this.canonicalFile // Pathname.
            this.canonicalPath // Pathname.
            this.freeSpace // Returns the number of unallocated bytes in the partition
            this.isAbsolute // todo Tests whether this abstract pathname is absolute.
            this.isDirectory
            this.isFile
            this.isHidden
            this.name
            this.path
            this.parent // Return link of the path.
            this.parentFile // Return link of the path.
            this.totalSpace // Returns the size of the partition
            this.invariantSeparatorsPath // Pathname.
            this.isRooted // todo
            this.extension // Type of this file.
            this.nameWithoutExtension // Name of  this file without type.
            this.canExecute()
            this.canRead()
            this.canWrite()
            this.createNewFile() // Create Empty File.
            this.delete() // Deletes the file or directory denoted by this abstract pathname, then the directory must be empty.
            this.deleteOnExit() // todo
            this.exists() // Return true if this file exist.
            this.list() // Returns an array of strings naming the files and directories in the directory.
            this.listFiles() // Returns an array of abstract pathnames denoting the files in the directory.
            this.lastModified() // Returns the time that the file was last modified by milliseconds.
            this.setLastModified(100L) // todo
            this.length() // Bytes atlasCompose.ui.Size of this abstract file.
            this.mkdir() // Creates the directory named by this abstract pathname.
            this.mkdirs() // todo
            this.renameTo(File("")) // Renames the file denoted by this abstract pathname.
            this.setReadOnly() // disable the modify option.
            this.setExecutable(true) // A convenience method to set the owner's execute permission for this file.
            this.setReadable(true) // A convenience method to set the owner's read permission for this file.
            this.setWritable(true) // A convenience method to set the owner's write permission for this
            this.toPath().run {
                /** @see AtlasJ.JPath */
            }
            this.toURI().run {
                /** @see AtlasJ.URI_URL */
            }
            this.appendBytes(byteArrayOf(49,50,51)) // -> add codepoint to the file.
            this.appendText("") // add a text to the original file.
            this.bufferedReader().run {
                this.lines().run {
                }
                this.readLine() // like readText().
            }
            this.bufferedWriter().run {
                this.newLine() // clean all previous lines.
            }
            this.copyTo(File("")) // Copies this file to the given [target] file.
            this.copyRecursively(File("")) // Copies this file or Directory with all its children to the specified destination [target] path.
            this.deleteRecursively() // Delete this file with all its children.
            this.startsWith(File("")) // return true if starting of the pathname was same.
            this.endsWith(File("")) // return true if ending of the pathname was same.
            this.forEachBlock { buffer // ByteArray.
                                , bytesRead // length.
                                        -> }
            this.forEachLine { line -> println(line) }
            this.inputStream().run {
                this.channel.run {
                }
                this.fd.run {
                }
            }
            this.outputStream().run {
                /* inputStream Attachment */
            }
            this.normalize() // For instance, `File("/foo/./bar/gav/../baaz").normalize()` is `File("/foo/bar/baaz")`.
            this.printWriter().run { // todo How to use those methods on Files??
                this.print("")
                this.printf("")
                this.println("")
                this.format("")
                this.checkError()
            }
            this.reader().run {
                this.encoding // Return name encoding, UTF-8||UTF-16..ex.
            }
            this.readBytes() // work as reverse appendByte()
            this.readLines() // Return List<String> of lines, Note:each line as index.
            this.readText() // Return all the text.
            this.writer().run {
                this.encoding
            }
            this.writeBytes(byteArrayOf()) // work like appendByte()
            this.writeText("") // overwriting the existing text, like Alternative.
            this.resolve("") // todo: For instance, `File("/foo/bar").resolve("gav")` is `File("/foo/bar/gav")`.
            this.resolveSibling("") // todo: For instance, `File("/foo/bar").resolveSibling("gav")` is `File("/foo/gav")`.
            this.relativeTo(File("")) // subtract the joint path between those two pathname.
            this.relativeToOrNull(File("")) // like relativeTo() or `null` if this and base paths have different roots.
            this.relativeToOrSelf(File("")) // ...     ...       or `this` if this and base  ...  ...     ...     ...
            this.toRelativeString(File("")) // ...    ...      or throws `IllegalArgumentException` if this and base ... ...    ...    ...
            this.walk(FileWalkDirection.TOP_DOWN).run {
                /** @see Atlas.f.FileTreeWalk */
            }
            this.walkTopDown().run {
                /** @see Atlas.f.FileTreeWalk */
            }
            this.walkBottomUp().run {
                /** @see Atlas.f.FileTreeWalk */
            }
        }
        //
        fileSystem.run {
            this.fileStores
            this.isOpen
            this.isReadOnly
            this.rootDirectories
            this.separator
            this.userPrincipalLookupService
            this.getPath("")
            this.getPathMatcher("")
            this.newWatchService()
            this.provider()
            this.supportedFileAttributeViews()
        }
        //
        FileDescriptor.err
        FileDescriptor.`in`
        FileDescriptor.out
        fileDescriptor.run {
            this.valid()
            this.sync()
        }
        //
        MidiFileFormat.UNKNOWN_LENGTH
        fileFormat.run {
            this.byteLength
            this.divisionType
            this.microsecondLength
            this.resolution
            this.type
            this.getProperty("")
            this.properties()
        }
        //
        FileHandler("").run {
            this.encoding
            this.errorManager
            this.filter
            this.formatter
            this.level
            this.close()
            this.flush()
            this.isLoggable(LogRecord(Level.ALL,""))
            this.publish(LogRecord(Level.ALL,""))
        }
        //
        FilePermission("","").run {
            this.actions
            this.name
            this.checkGuard(null)
            this.implies(null)
            this.newPermissionCollection()
        }
        //
        FileReader("").run {
            this.encoding
            /* see Reader attachment. */
        }
        //
        FileWriter("").run {
            this.write(49)
            this.buffered()
        }
        //
        fileStore.run {
            this.blockSize
            this.isReadOnly
            this.totalSpace
            this.unallocatedSpace
            this.usableSpace
            this.name()
            this.type()
            this.getAttribute("")
//            this.getFileStoreAttributeView()
            this.supportsFileAttributeView("")
        }
        fileChannel.run {
            this.lock()
            this.tryLock()
            this.force(true)
            this.map(FileChannel.MapMode.PRIVATE,1L,1L)
            this.read(ByteBuffer.wrap(byteArrayOf()))
            this.transferFrom(null,1L,2L)
            this.transferTo(1,1,null)
            this.write(ByteBuffer.wrap(byteArrayOf()))
        }
    }
}