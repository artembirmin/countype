/*
 * Created by Artem Petrosyan on 9/9/2021.
 *
 */

package com.incetro.countypecore.data.repository.functiondescription

import com.incetro.countypecore.R
import com.incetro.countypecore.data.file.JsonFileReader
import com.incetro.countypecore.data.repository.functiondescription.factory.TemplateRegexToTemplateWithFunctionIdMapFactory
import com.incetro.countypecore.model.function.TemplateWithFunctionIdResult
import com.incetro.countypecore.model.function.functiondescription.FunctionDescription
import com.incetro.countypecore.model.function.functiondescription.Template
import com.incetro.countypecore.model.json.FunctionDescriptionsLists

internal class FunctionDescriptionRepositoryImpl(
    private val templateRegexToTemplateWithFunctionIdMapFactory:
    TemplateRegexToTemplateWithFunctionIdMapFactory,
    private val jsonFileReader: JsonFileReader
) : FunctionDescriptionRepository {

    /**
     * Name of JSON file with function descriptions in `resources`.
     */
    private val functionDescriptionsJsonFileName = R.raw.function_descriptions

    /**
     * Хранит объекты [FunctionDescription] в рантайме.
     */
    private val functionDescriptions: MutableList<FunctionDescription> =
        jsonFileReader.decerializeObjectFromJsonFileFromResources<FunctionDescriptionsLists>(
            functionDescriptionsJsonFileName
        ).flatten().toMutableList()

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
