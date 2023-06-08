package AtlasJ

import java.net.URI
import java.net.URL

class URI_URL {
    lateinit var uri: URI
    lateinit var url: URL
    init {
        URI.create("")
        uri.run {
            this.authority
            this.fragment
            this.host
            this.isAbsolute
            this.isOpaque
            this.path
            this.port
            this.query
            this.rawAuthority
            this.rawFragment
            this.rawPath
            this.rawQuery
            this.rawSchemeSpecificPart
            this.rawUserInfo
            this.scheme
            this.schemeSpecificPart
            this.userInfo
            this.normalize()
            this.parseServerAuthority()
            this.relativize(URI(""))
            this.resolve("")
            this.toASCIIString()
            this.toURL().run {
            }
        }

        URL.setURLStreamHandlerFactory {protocol: String? -> null }
        url.run {
            this.authority
            this.content
            this.defaultPort
            this.file
            this.host
            this.ref
            this.query
            this.userInfo
            this.sameFile(this)
            this.toExternalForm()
            this.toURI()
        }
    }
}