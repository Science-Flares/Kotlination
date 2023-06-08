package k

import kotlin.reflect.KDeclarationContainer

interface KDeclarationContainer {
     var kDeclarationContainer: KDeclarationContainer
    fun main() {
        kDeclarationContainer.members
    }
}