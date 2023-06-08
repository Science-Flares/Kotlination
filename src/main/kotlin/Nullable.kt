private fun some(){
    val ss :Int? = readLine()?.toIntOrNull()

    // ! not.
    ss!=null

    // ?. ...
    if (ss!=null) print("not null") // or do something.
    // or
    ss ?.let { print("not null") }

    // ?: Elvis_Operator
    if (ss==null) print("null!!") // or do something.
    // or
    ss ?: print("null!!")

    //  ?. with ?:
     ss ?.let { print("not null") } ?: print("null!!")

}
