/*
 * Created by Artem Petrosyan on 8/9/2021.
 *
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