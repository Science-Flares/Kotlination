package Atlas.c

import kotlin.text.CharDirectionality.*

private class CharDirectionality {
    private val cd = 'D'.directionality
    /**
       Represents the Unicode directionality of a character, left to right or the opposite.
     */
    init {
        cd.value // TODO: 16.01.2021  
        
        LEFT_TO_RIGHT
        LEFT_TO_RIGHT_EMBEDDING
        LEFT_TO_RIGHT_OVERRIDE
        RIGHT_TO_LEFT
        RIGHT_TO_LEFT_ARABIC
        RIGHT_TO_LEFT_EMBEDDING
        RIGHT_TO_LEFT_OVERRIDE
        ARABIC_NUMBER
        BOUNDARY_NEUTRAL
        NONSPACING_MARK
        OTHER_NEUTRALS
        COMMON_NUMBER_SEPARATOR
        PARAGRAPH_SEPARATOR
        SEGMENT_SEPARATOR
        POP_DIRECTIONAL_FORMAT
        UNDEFINED 
        WHITESPACE
        EUROPEAN_NUMBER_SEPARATOR
        EUROPEAN_NUMBER
        EUROPEAN_NUMBER_TERMINATOR
    }
}
