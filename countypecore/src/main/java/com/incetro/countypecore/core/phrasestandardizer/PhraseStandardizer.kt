/*
 * Created by Artem Petrosyan on 6/10/2021.
 *
 */

/*
 * Created by Artem Petrosyan on 29/9/2021.
 *
 */

package com.incetro.countypecore.core.phrasestandardizer

/**
 * Приводит фразы к общему/стандартному/нормализованному/каноническому виду.
 */
interface PhraseStandardizer {
    /**
     * Приводит [phrase] к стандартному виду, заменяя некоторые подстроки таким образом,
     * что было удобнее разбивать [phrase] на лексемы.
     */
    fun createStandardizedPhrase(phrase: String): String
}