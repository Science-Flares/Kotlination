package cio

/**
 Enumeration, it's class have constants in,
 this constants have relationship with each other like days of thee week,
 Enum class is abstract class,
 normally the Enum constrictor is privet,
*/
enum class Enum (mothsOfSeasons: String) {

    // The contents.
    WINTER("december-january-february"),
    SPRING("march-april-may"),
    SUMMER("june-july-august"),
    AUTUMN("september-october-november");
    lateinit var enum: kotlin.Enum<*>

    fun qsdqsd() {
        enum.run {
            this.name
            this.ordinal
        }
    }
}
