package AtlasLib

import kotlinx.coroutines.delay
import java.time.LocalTime

class BinaryClock {
    private fun go(): LocalTime = LocalTime.now()
    private fun binary(a:Any) : String {
        return when (a) {
            '1' -> "0001";'2' -> "0010";'3' -> "0011"
            '4' -> "0100";'5' -> "0101";'6' -> "0110"
            '7' -> "0111";'8' -> "1000";'9' -> "1001"
            else -> "0000"
        }
    }
    // seconds.
   private fun s():String {
        return binary(go().second.toString().last())
    }
    private fun ss(): String {
        return if (go().second in 0..9) " FORMAT"
        else binary(go().second.toString()[0])
            .replaceFirst('0',' ')
    }
    // minute.
    private fun m():String {
        return binary(go().minute.toString().last())
    }
    private fun mm(): String {
        return if (go().minute in 0..9) " FORMAT"
        else binary(go().minute.toString()[0])
                .replaceFirst('0',' ')
    }
    // hours.
    private fun h():String {
        return binary(go().hour.toString().last())
    }
    private fun hh(): String {
        return if (go().hour in 0..9) "  00"
        else binary(go().hour.toString()[0])
                .replaceFirst('0',' ')
                .replaceFirst('0',' ')
    }
    //
    suspend fun runIt() {
        var i = 0
        for (cc in 0..Int.MAX_VALUE) {
            println("${hh()[i]} ${h()[i]}  ${mm()[i]} ${m()[i]}  ${ss()[i]} ${s()[i]}")
            i++
            if (i == 4) run {
                println("--------------------") // use this line if you run it on console.
                i = 0
                delay(1000)
//                ProcessBuilder("clear").inheritIO().start() // use this line if you run it on terminal.
            }
        }
    }
}