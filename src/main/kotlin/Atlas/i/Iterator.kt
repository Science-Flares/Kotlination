package Atlas.i


private interface Iterator {
    var mutableiterator: MutableIterator<String>
    var mutableListIterator: MutableListIterator<String>

    /**
     * An iterator over a collection or another entity,
     * that can be represented as a sequence of elements.
     * Allows to sequentially access the elements.
     */
    fun some() {
        "string".asIterable().iterator().apply {
            this.hasNext()
            this.next()
            this.forEach { }
            this.forEachRemaining { }
            this.asSequence()
            this.withIndex()
        }

        "string".toList().listIterator().apply {
            this.hasPrevious()
            this.previous()
            this.previousIndex()
            this.nextIndex()
        }

        mutableiterator.run {
            this.remove()
        }

        mutableListIterator.run {
        this.add("")
        this.set("")
        }

    }
}