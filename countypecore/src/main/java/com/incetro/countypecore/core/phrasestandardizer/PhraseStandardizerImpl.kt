/*
 * Created by Artem Petrosyan on 6/10/2021.
 *
 */

/*
 * Created by Artem Petrosyan on 26/9/2021.
 *
 */

package com.incetro.countypecore.core.phrasestandardizer

internal class PhraseStandardizerImpl : PhraseStandardizer {

    /**
     * Percent [Regex].
     */
    private val percentRegex = """\s%|\sпроцент[a-zA-Zа-яА-Я]*""".toRegex()

    /**
     * Percent [Regex] with surrounding context.
     */
    private val percentRegexWithContext = """\d+($percentRegex)\s""".toRegex()

    /**
     * Percent replacement string.
     */
    private val percentReplacement = "%"

    /**
     * Ставит в соответствие регулярное выражение вместе окружающими его элементами (контекстом)
     * регулярному выражению без контекста. Второе используется для замены и не затрагивает окружение.
     */
    private val replaceableRegexWithContextToRegex = mapOf(
        percentRegexWithContext
                to percentRegex
    )

    /**
     * Сопоставляет регулярное выражение и строку,
     * на которую будет заменена соответствующая ему подстрока фразы.
     */
    private val regexToReplacement = mapOf(
        percentRegex
                to percentReplacement
    )

    override fun createStandardizedPhrase(phrase: String): String {
        var standardPhrase = phrase.lowercase()
        replaceableRegexWithContextToRegex.forEach { (regexWithContext, regex) ->
            if (standardPhrase.contains(regexWithContext)) {
                val replacement = regexToReplacement[regex]
                replacement?.let {
                    standardPhrase = standardPhrase.replace(regex, replacement)
                }
            }
        }
        return standardPhrase
    }
}