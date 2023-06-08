@file:Suppress("UNREACHABLE_CODE", "LABEL_NAME_CLASH", "UNUSED_CHANGED_VALUE")

// loops and control flow, allow you repeating the same jop many times.
fun loop(){

    // for.
    loop@for (ss in 0..10) {
        // local for.
        for (cc in 0..3) {
            println(cc)
        }
        if (ss == 5) continue // use continue by if condition to skip index [5].
        else if (ss == 9) break || return
        println(ss)
    }

    // when.
for (ss in 0..10){
    when (ss){
        0 -> println("start")
        10 -> println("end")
        else -> throw ExceptionInInitializerError()
    }
    println(ss)
}

    // while.
    var loo = 0
    while (loo <= 10){
        if (loo==2 && loo==7) continue
        println(loo)
        loo++ // or loo+=1
    }

    // do while.
    var char = 'a'
    loop@ do {
        when (char) {
            'g' -> continue@loop
            's' -> continue@loop
        }
        println(char)
        char++
    }while (char <= 'z')


}



