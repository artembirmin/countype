/*
 * Created by Artem Petrosyan on 8/9/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.model.function.functiondescription

/**
 * Описание функции.
 */
data class FunctionDescription(
    /**
     * Идентификатор описываемой функции.
     */
    val functionId: String,
    /**
     * Список [Template], описывающий фразы, которые вычислимы функцией.
     */
    val templates: List<Template>
)