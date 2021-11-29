package com.incetro.countypecore.interactor

import com.incetro.countypecore.data.repository.functiondescription.factory.TemplateExpressionToRegexMapper
import com.incetro.countypecore.model.function.functiondescription.Template

internal class PhraseUnnecessaryCleaner(
    private val templateExpressionToRegexMapper: TemplateExpressionToRegexMapper =
        TemplateExpressionToRegexMapper()
) {
    fun createCleanedPhrase(phrase: String, template: Template): String {
        val regex = templateExpressionToRegexMapper.map(template.expression)
        return regex.find(phrase)?.value
            ?: throw IllegalArgumentException("Cannot find regex in phrase")
    }
}