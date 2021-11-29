/*
 * Created by Artem Petrosyan on 14/10/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.data.repository.functiondescription.factory

import com.incetro.countypecore.data.repository.functiondescription.FunctionDescriptionRepositoryImpl
import com.incetro.countypecore.model.function.TemplateWithFunctionIdResult
import com.incetro.countypecore.model.function.functiondescription.FunctionDescription

/**
 * Производит [Map] для
 * [FunctionDescriptionRepositoryImpl.templateExpressionRegexToTemplateWithFunctionIdMap]
 */
internal interface TemplateRegexToTemplateWithFunctionIdMapFactory {
    /**
     * Get [FunctionDescriptionRepositoryImpl.templateExpressionRegexToTemplateWithFunctionIdMap].
     */
    fun getMap(functionDescriptions: List<FunctionDescription>): Map<Regex, TemplateWithFunctionIdResult>
}
