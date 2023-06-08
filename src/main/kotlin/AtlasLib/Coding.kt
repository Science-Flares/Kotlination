package AtlasLib

import java.util.*
import kotlin.math.absoluteValue

class Coding {
    // convert the text to binary.
    internal fun textToBinary(text: String) = text.forEach {
        val binary = Integer.toBinaryString(it.code)
        print("$binary ")
    }
    // convert the text to hex.
    internal fun textToHex(text: String): String {
        text.forEach {
            val hex = Integer.toHexString(it.toInt())
            print("${hex.uppercase(Locale.getDefault())} ")
        }
        return ""
    }
    // convert the text to octal.
    internal fun textToOctal(text: String) = text
        .forEach {
            val octal = Integer.toOctalString(it.toInt())
            print("$octal ")
        }

    // convert the integer to binary.
    // normal number.
    internal fun integerToBinary(nm: Int) = (Integer.toBinaryString(nm))

    // massive number:
    private val arr0 = CodingList().mounting()
    internal fun integerToBinary(num: String): Int {
        return if (num.length >= 31 && num.startsWith('1')) {
            num.takeLast(31)
                .dropWhile { it == '1' }
                .reversed()
                .replace('0', '*').replace('1', '0').replace('*', '1')
                .toList()
                .zip(arr0)
                .filter { it.first == '1' }
                .sumOf { it.second.unaryMinus() }

        } else num.reversed()
            .toList()
            .zip(arr0)
            .filter { it.first == '1' }
            .sumOf { it.second.absoluteValue }
    }

    // convert the integer to octal.
    internal fun integerToOctal(nm: Int) = Integer.toOctalString(nm)

    // convert double to Binary.
    internal fun doubleToBinary(double:Double): String {
        var num = double
        val int = Integer.toBinaryString(num.toInt())
        val fraction = StringBuilder("")
        for (ss in 0..1000) {
            num = (num - num.toInt()) * 2 //For take the fraction after a point.
            if (num == 0.0) break
            fraction.append(num.toInt().absoluteValue)
        }
        return "$int.$fraction"
    }
}