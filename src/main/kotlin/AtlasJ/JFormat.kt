package AtlasJ

import java.text.Format

class JFormat {
    lateinit var form: Format

    init{

        Format.Field.INPUT_METHOD_SEGMENT
        Format.Field.LANGUAGE
        Format.Field.READING

        //
        form.run {
            format("")
            parseObject("")
            formatToCharacterIterator("")
        }

        /**
        format is very import and useful option in the kotlin,
        some result in big operation will be long but not necessary to show the all ,
        for this, we will use format option.
         */

        // add `f` for float number to print n numbers after the period.
        // pi -> 3.141592653589793
        println("%.2f".format(Math.PI))

        // for remove all the string except the firsts characters.
        println("%.4s".format("land rover"))

        // for remove all numbers except the firsts.
        println("%.5s".format(123456789))

        // for inserts a newline of string.
        println("%n".format(1985))

        // for changing the digit with unicode value.
        println("%c".format(1579))


    }
}