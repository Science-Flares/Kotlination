lateinit var regex: Regex

fun regularExpressionFun() {
/**
 regular Expression: is class has lot methods for control the input(type,size).
     import:
     \\d  index for one number.
     \\w  index one number or one char(uppercase ,lowercase).
     \\s  space.
     |   or -> (\\d) | (\\w).
     +  -> (\\d+) for add unless one number or more.
     \\+   for input + symbol itself.
     *  -> (\\d*) for add numbers or null.
     \\*   for input * symbol itself.
     ?  -> \\d? for input one number or null.
     \\?   for input ? symbol itself.
     []  -> [A-Z],[0-9] for make range.
     {}  -> \\d{3} -> \\d\\d\\d ,3 index.
     ()   for Aggregation -> (\\d[a-z]\\w)+.
*/
    //
    Regex.escape("")
    Regex.escapeReplacement("")
    Regex.fromLiteral("")
    //
    with(regex){
        this.matches("")
        this.options
        this.pattern
        this.containsMatchIn("")
        this.find("")
        this.findAll("")
        this.matchEntire("")
        this.toPattern().run {
        }
    }

    // kotline@gmail.com
    println("Enter Your Emile: \t")
    val email= readLine().toString()
    val joy= Regex("(\\w+)*@(\\w+)*\\.(\\w+)*")
    // or.
    val high= ".\\w*.".toRegex()

    println(joy.matches(email))
    // if we want add space or dot or any symbol [\\+...].
    val ijoy= "\\w+([\\.\\+\\-\\_\\s]\\w+)*@(\\w+)*\\.(\\w+)*".toRegex()
    println(ijoy.matches(email))
    // add ? for input something only one time.
    val yjoy= "\\w+([\\.\\+\\-\\_\\s]\\w+)?@(\\w+)*\\.(\\w+)*".toRegex()
    println(yjoy.matches(email))

    // for add same string or null like password.
    println("Enter Your Password \t")
    val pass= readLine() as String
    val me= Regex("password+")
    println(me.matches(pass))

}