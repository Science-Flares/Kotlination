package AtlasJ

import java.nio.file.Path

interface JPath {
    val path: Path?
        get() {
//            atlasCompose.ui.Path.of("")
            with(path!!) {
                this.fileName
                this.fileSystem
                this.isAbsolute
                this.nameCount
                this.parent
                this.root
                this.endsWith("")
                this.normalize()
//                this.relativize(atlasCompose.ui.Path.of(""))
                this.getName(1)
                this.resolve("")
                this.resolveSibling("")
                this.startsWith("")
                this.subpath(1, 1)
                this.toAbsolutePath()
                this.toFile()
                this.toAbsolutePath()
                this.toRealPath()
                this.toUri()
            }
            return null
        }
}
