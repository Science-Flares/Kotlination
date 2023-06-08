package Atlas.m

import kotlin.text.MatchResult

interface MatchResult {
     var matchResult : MatchResult
    fun some (){
        matchResult.value
        matchResult.range
        matchResult.groups
        matchResult.groupValues
        matchResult.destructured
        matchResult.next()
    }
    private class Destructured {
        lateinit var destructured: MatchResult.Destructured
        init {
            destructured.run {
                this.match.run {
                }
                this.component1()
                this.component2()
                this.component3()
                this.component4()
                this.component5()
                this.component6()
                this.component7()
                this.component8()
                this.component9()
                this.component10()
                this.toList()
            }
        }
    }
}