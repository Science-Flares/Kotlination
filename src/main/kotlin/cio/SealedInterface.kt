package cio

sealed interface SealedInterface{
    // sealed interface.
//    which works on interfaces in the same way it works on classes
        interface Mercedes
        interface Audi
        interface VW
        interface BMD

    companion object {
        fun ss(si: SealedInterface) = when(si) {
            is Mercedes -> println("mercedes models")
            is Audi -> println("audi models")
            is VW -> println("vw models")
            is BMD -> println("bmd models")
            else -> {}
        }
    }
}

