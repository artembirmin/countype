/*
 * My Application
 *
 * Created by artembirmin on 6/5/2023.
 */

package com.incetro.countype.data.database.functiondescription

import com.incetro.countypecore.model.function.functiondescription.Template


data class TemplateDto(
    val id: Int,
    val expression: String,
    val argumentsOrder: List<Int> = emptyList(),
) {

    fun toTemplate(): Template = Template(expression, argumentsOrder)
}