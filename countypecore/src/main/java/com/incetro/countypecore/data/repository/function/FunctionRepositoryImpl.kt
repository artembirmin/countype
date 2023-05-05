/*
 * Created by Artem Petrosyan on 9/9/2021.
 *
 */

package com.incetro.countypecore.data.repository.function

import com.incetro.countypecore.model.function.Function
import com.incetro.countypecore.model.function.concrete.notcalculating.FormatNumber
import com.incetro.countypecore.model.function.concrete.notcalculating.FormatNumberAndMeasure
import com.incetro.countypecore.model.function.concrete.percentage.PercentOf
import com.incetro.countypecore.model.function.concrete.unitrelated.UnitToUnit

internal class FunctionRepositoryImpl : FunctionRepository {

    /**
     * Хранит все функции.
     */
    private val functionsList = arrayListOf(
        FormatNumber(),
        FormatNumberAndMeasure(),
        UnitToUnit(),
        PercentOf()
    )

    override fun findFunctionById(id: String): Function =
        functionsList.find { it.id == id }
            ?: throw IllegalStateException("Function with id = $id not found.")

    override fun add(function: Function) {
        functionsList.add(function)
    }
}
