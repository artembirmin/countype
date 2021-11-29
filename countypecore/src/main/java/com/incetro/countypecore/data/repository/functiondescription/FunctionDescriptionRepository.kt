/*
 * Created by Artem Petrosyan on 9/9/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.data.repository.functiondescription

import com.incetro.countypecore.model.function.Function
import com.incetro.countypecore.model.function.TemplateWithFunctionIdResult
import com.incetro.countypecore.model.function.functiondescription.FunctionDescription

/**
 * Репозиторий для работы с [FunctionDescription].
 */
interface FunctionDescriptionRepository {

    /**
     * Добавляет экземпляр [FunctionDescription].
     */
    fun add(functionDescription: FunctionDescription)

    /**
     * Ищет [com.incetro.countypecore.model.function.functiondescription.Template] и [Function.id]
     * осуществляя сопоставление [phrase] шаблону функции из [FunctionDescription.templates].
     *
     * @return [TemplateWithFunctionIdResult] если [phrase]
     * соответствует одному из [FunctionDescription.templates].
     *
     * @throws IllegalArgumentException if template for [phrase] not found.
     */
    fun findTemplateAndFunctionIdByPhrase(phrase: String): TemplateWithFunctionIdResult

    /**
     * Добавляет все экземпляры [FunctionDescription] из коллекции.
     */
    fun addAll(functionDescriptions: Collection<FunctionDescription>)
}