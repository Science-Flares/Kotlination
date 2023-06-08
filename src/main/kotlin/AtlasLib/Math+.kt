package AtlasLib

import kotlin.math.sign
 class `Math+` {


    // Factorial Number.(n!)
    //

     fun factorial(`!`: Int = readLine()!!.toInt()): Any {
        if (`!` < 0) return Double.NaN
        return (`!` downTo 1).toList().fold(1) { i, acc -> acc * i }
     }

    // Fibonacci Atlas.s.Sequence.
    //
    fun fibonacciSequence(num: Long = readLine()!!.toLong()): ArrayList<Long>? {
        val sequence = mutableMapOf(1 to arrayListOf(0L, 1L), 0 to arrayListOf(0L), -1 to arrayListOf(0L, 1L))
        if (num.sign == 1) for (ss in 2L..num) {
            sequence[1]!!.add(sequence[1]!!.zipWithNext { a, b -> a + b }.last())
        }
        else if (num.sign == -1) for (ss in -2L downTo num) {
            sequence[-1]!!.add(sequence[-1]!!.zipWithNext { a, b -> a - b }.last())
        }
        return sequence[num.sign]
    }

    // Lucas sequence.
    //
    fun lucasSequence(num: Long = readLine()!!.toLong()): ArrayList<Long>? {
        val sequence = mutableMapOf(1 to arrayListOf<Long>(2, 1), 0 to arrayListOf<Long>(2), -1 to arrayListOf<Long>(2, -1))
        if (num.sign == 1) for (ss in 2..num) {
            sequence[1]!!.add(sequence[1]!!.zipWithNext { a, b -> a + b }.last())
        }
        else if (num.sign == -1) for (ss in -2 downTo num) {
            sequence[-1]!!.add(sequence[-1]!!.zipWithNext { a, b -> a - b }.last())
        }
        return sequence[num.sign]
    }
}