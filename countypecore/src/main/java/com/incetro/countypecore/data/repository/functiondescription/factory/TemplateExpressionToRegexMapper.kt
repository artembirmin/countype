/*
 * Created by Artem Petrosyan on 14/10/2021.
 *
 */

package com.incetro.countypecore.data.repository.functiondescription.factory

import com.incetro.countypecore.common.KeywordConstants
import com.incetro.countypecore.model.function.functiondescription.Template

/**
 * Map [Template.expression] to [Regex] согласно [keywordToRegexStringMap].
 */
internal class TemplateExpressionToRegexMapper {
    /**
     * Отображает [KeywordConstants] в паттерны регулярных выражений.
     */
    private val keywordToRegexStringMap: Map<String, String> = mapOf(
        KeywordConstants.UNIT_TEMPLATE to KeywordConstants.UNIT_REGEX_STRING,
        KeywordConstants.NUMBER_TEMPLATE to KeywordConstants.NUMBER_REGEX_STRING,
        KeywordConstants.PERCENTAGE_TEMPLATE to KeywordConstants.PERCENTAGE_REGEX_STRING
    )

    /**
     * Map [templateExpression] to [Regex].
     */
    fun map(templateExpression: String): Regex = convertTemplateToRegex(templateExpression)

    /**
     * Convert [Template.expression] to [Regex] use [keywordToRegexStringMap].
     */
    private fun convertTemplateToRegex(templateExpression: String): Regex {
        var regex = templateExpression
        for ((keyword, regexString) in keywordToRegexStringMap)
            regex =
                regex.replace(
                    keyword,
                    regexString
                )
        return regex.toRegex()
    }
}