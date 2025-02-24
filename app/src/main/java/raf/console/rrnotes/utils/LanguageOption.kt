package raf.console.rrnotes.utils

enum class LanguageOption(val languageName: String) {
    Russian("Русский"),
    Belarusian("Беларуская"),
    English("English"),
    German("Deutsch"),
    French("Français"),
    Polish("Polski"),
    Spanish("Español"),
    Estonian("Eesti"),
    Italian("Italiano"),
    Greek("Ελληνικά"),
    Hindi("हिन्दी"),
    Persian("فارسی"),
    Arabic("العربية"),
    PalestinianArabic("العربية الفلسطينية"),
    Indonesian("Bahasa Indonesia"),
    Hebrew("עברית"),
    Yiddish("ייִדיש"),
    Tatar("Татарча"),
    Bashkir("Башҡорт"),
    Kazakh("Қазақ"),
    Kyrgyz("Кыргызча"),
    Tajik("Тоҷикӣ"),
    Uzbek("Oʻzbek"),
    Armenian("Հայերեն"),
    Azerbaijani("Azərbaycan");

    fun toLanguageString(): String = name.lowercase()

    companion object {
        fun fromLanguageString(languageString: String): LanguageOption {
            return values().find { it.name.lowercase() == languageString } ?: Russian
        }
    }
}