package AtlasJ

import java.util.*

class JLocale {
    lateinit var locale: Locale
    init {
        Locale.CANADA // example
        Locale.filter(listOf<Locale.LanguageRange>(), listOf<Locale>())
        Locale.filterTags(listOf<Locale.LanguageRange>(), listOf<String>())
        Locale.forLanguageTag("")
        Locale.getAvailableLocales()
        Locale.getDefault()
        Locale.getISOCountries()
        Locale.getISOLanguages()
        Locale.lookup( listOf<Locale.LanguageRange>(), listOf<Locale>())
        Locale.lookupTag(listOf<Locale.LanguageRange>(), listOf<String>())
        Locale.setDefault(Locale.getDefault())

        //
        Locale.Category.DISPLAY
        Locale.Category.FORMAT

        //
        Locale.FilteringMode.AUTOSELECT_FILTERING
        Locale.FilteringMode.EXTENDED_FILTERING
        Locale.FilteringMode.IGNORE_EXTENDED_RANGES
        Locale.FilteringMode.MAP_EXTENDED_RANGES
        Locale.FilteringMode.REJECT_EXTENDED_RANGES

        //
        Locale.IsoCountryCode.PART1_ALPHA2
        Locale.IsoCountryCode.PART1_ALPHA3
        Locale.IsoCountryCode.PART3

        //
        Locale.LanguageRange.MAX_WEIGHT
        Locale.LanguageRange.MIN_WEIGHT
        Locale.LanguageRange.mapEquivalents(mutableListOf<Locale.LanguageRange>(), mutableMapOf<String,MutableList<String>>())
        Locale.LanguageRange.parse("")

        //
        Locale.Builder().run {
            this.build()
            this.clear()
            this.clearExtensions()
            this.addUnicodeLocaleAttribute("")
            this.removeUnicodeLocaleAttribute("")
            this.setExtension('0',"")
            this.setLanguage("")
            this.setLanguageTag("")
            this.setLocale(Locale.CANADA)
            this.setRegion("")
            this.setScript("")
            this.setUnicodeLocaleKeyword("","")
            this.setVariant("")
        }
        with(locale){
            this.country
            this.displayCountry
            this.displayLanguage
            this.displayName
            this.displayScript
            this.displayVariant
            this.extensionKeys
            this.isO3Country
            this.isO3Language
            this.language
            this.script
            this.unicodeLocaleAttributes
            this.unicodeLocaleKeys
            this.variant
            this.getExtension('0')
            this.hasExtensions()
            this.stripExtensions()
            this.toLanguageTag()
        }
    }
}