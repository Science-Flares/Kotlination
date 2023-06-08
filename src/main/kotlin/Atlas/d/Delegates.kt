package Atlas.d

import kotlin.properties.Delegates

private object Delegates {

     var delegates: Delegates = Delegates

    init {
        /** Delegation.Observable()
        Observable() takes two arguments to initialize the object and returns the same
        to the called function.
        In the following example, we will see how to use Observable() method in order to
        implement delegation.
         */
        class User {
            var user : Int by delegates.observable ( 0)
            { property, oldValue, newValue ->
                println("a:$property b:$oldValue c:$newValue")
            }
        }

        delegates.vetoable(this)
        { property, oldValue, newValue -> true } // todo
    }
}