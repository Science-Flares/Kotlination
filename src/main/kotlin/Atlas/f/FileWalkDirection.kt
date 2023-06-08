package Atlas.f

private class FileWalkDirection {
    /**An enumeration to describe possible walk directions.*/
    lateinit var fileWalkDirection: FileWalkDirection
    init {
        kotlin.io.FileWalkDirection.BOTTOM_UP
        kotlin.io.FileWalkDirection.TOP_DOWN
        kotlin.io.FileWalkDirection.values()
    }
}