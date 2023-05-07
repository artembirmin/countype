/*
 * Created by Artem Petrosyan on 9/9/2021.
 *
 */

package com.incetro.countypecore.data.repository.functiondescription

import com.incetro.countypecore.data.repository.functiondescription.factory.TemplateRegexToTemplateWithFunctionIdMapFactory
import com.incetro.countypecore.model.function.TemplateWithFunctionIdResult
import com.incetro.countypecore.model.function.functiondescription.FunctionDescription
import com.incetro.countypecore.model.function.functiondescription.Template

internal class FunctionDescriptionRepositoryImpl(
    private val templateRegexToTemplateWithFunctionIdMapFactory:
    TemplateRegexToTemplateWithFunctionIdMapFactory,
) : FunctionDescriptionRepository {

    /**
     * Хранит объекты [FunctionDescription] в рантайме.
     */
    private val functionDescriptions: MutableList<FunctionDescription> = mutableListOf()

    /**
     * Отображает [Template.expression] в виде [Regex] в [TemplateWithFunctionIdResult].
     */
    private val templateExpressionRegexToTemplateWithFunctionIdMap:
            Map<Regex, TemplateWithFunctionIdResult> by lazy {
        templateRegexToTemplateWithFunctionIdMapFactory.getMap(functionDescriptions)
    }

    override fun add(functionDescription: FunctionDescription) {
        functionDescriptions.add(functionDescription)
    }

    override fun addAll(functionDescriptions: Collection<FunctionDescription>) {
        this.functionDescriptions.addAll(functionDescriptions)
    }

    override fun findTemplateAndFunctionIdByPhrase(phrase: String): TemplateWithFunctionIdResult =
        templateExpressionRegexToTemplateWithFunctionIdMap.keys.firstOrNull { regex ->
            regex.containsMatchIn(
                phrase
            )
        }?.let { templateExpressionRegexToTemplateWithFunctionIdMap[it] }
            ?: throw IllegalArgumentException("Template for phrase = $phrase not found.")
}
