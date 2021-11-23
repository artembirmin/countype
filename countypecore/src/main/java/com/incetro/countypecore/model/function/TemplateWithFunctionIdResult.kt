/*
 * Created by Artem Petrosyan on 14/9/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.model.function

import com.incetro.countypecore.data.repository.functiondescription.FunctionDescriptionRepository
import com.incetro.countypecore.model.function.functiondescription.Template

/**
 * Возвращаемое значение метода [FunctionDescriptionRepository.findTemplateAndFunctionIdByPhrase].
 */
class TemplateWithFunctionIdResult(
    /**
     * Один из шаблонов [FunctionDescription.templates].
     */
    val template: Template,
    /**
     * Соответствует [Function.id].
     */
    val functionId: String
) {
    operator fun component1() = template
    operator fun component2() = functionId
}