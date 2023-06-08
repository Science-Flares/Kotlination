package k

import kotlin.KotlinVersion

private class KotlinVersion {
    init {
        KotlinVersion.CURRENT /** [major].[minor].[patch]*/
        KotlinVersion.CURRENT.run {
          major
          minor
          patch
          isAtLeast(major, minor)
      }
    }
}