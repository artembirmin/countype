/*
 * Created by Artem Petrosyan on 14/10/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

/*
 * Created by Artem Petrosyan on 14/10/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.data.repository.functiondescription.factory

import com.incetro.countypecore.model.function.TemplateWithFunctionIdResult
import com.incetro.countypecore.model.function.functiondescription.FunctionDescription

internal class TemplateRegexToTemplateWithFunctionIdMapFactoryImpl(private val templateExpressionToRegexMapper: TemplateExpressionToRegexMapper) :
    TemplateRegexToTemplateWithFunctionIdMapFactory {

    override fun getMap(functionDescriptions: List<FunctionDescription>): Map<Regex, TemplateWithFunctionIdResult> {
        val regexToTemplateWithFunctionId =
            mutableMapOf<Regex, TemplateWithFunctionIdResult>()
        functionDescriptions.forEach { functionDescription ->
            functionDescription
                    .templates
                    .forEach { template ->
                        regexToTemplateWithFunctionId[templateExpressionToRegexMapper.map(template.expression)] =
                            TemplateWithFunctionIdResult(template, functionDescription.id)
                    }
        }
        return regexToTemplateWithFunctionId
    }
}