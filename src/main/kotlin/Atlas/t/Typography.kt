package t

import kotlin.text.Typography

object Typography {
    lateinit var typography: Typography
    init {
        with(typography) {
            this.quote // "
            this.dollar // $
            this.amp // &
            this.less // <
            this.greater // >
            this.nbsp
            this.times // ×
            this.cent // ¢
            this.pound // £
            this.section // §
            this.copyright // ©
            this.leftGuillemete // «
            this.rightGuillemete // »
            this.registered // ®
            this.degree // °
            this.plusMinus // ±
            this.paragraph // ¶
            this.middleDot // ·
            this.half // ½
            this.ndash // –
            this.mdash // —
            this.leftSingleQuote // ‘
            this.rightSingleQuote // ’
            this.lowSingleQuote // ‚
            this.leftDoubleQuote // “
            this.rightDoubleQuote // ”
            this.lowDoubleQuote // „
            this.dagger
            this.doubleDagger
            this.bullet // •
            this.ellipsis // …
            this.prime // ′
            this.doublePrime // ″
            this.euro // €
            this.tm // ™
            this.almostEqual // ≈
            this.notEqual // ≠
            this.lessOrEqual // ≤
            this.greaterOrEqual // ≥
        }
    }
}