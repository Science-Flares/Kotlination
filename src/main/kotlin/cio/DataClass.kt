package cio

// the condition in data class is mast put unless one parameter

data class DataClass constructor(val person:String="", val rate:Int=0){

    fun dataFunction() {
        println("Mr.$person has $rate rate.")
    }
}