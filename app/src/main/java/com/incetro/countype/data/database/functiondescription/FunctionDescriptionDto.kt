/*
 * My Application
 *
 * Created by artembirmin on 6/5/2023.
 */

package com.incetro.countype.data.database.functiondescription

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.incetro.countypecore.model.function.functiondescription.FunctionDescription

@Entity(
    tableName = FunctionDescriptionDto.TABLE_NAME
)
data class FunctionDescriptionDto(
    @PrimaryKey
    val id: String,
    val functionId: String,
    val templates: List<TemplateDto>,
) {
    companion object {
        const val TABLE_NAME = "function_description"
    }

    fun toFunctionDescription(): FunctionDescription =
            FunctionDescription(functionId, templates.map { it.toTemplate() })
}