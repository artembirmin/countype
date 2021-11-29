/*
 * Created by Artem Petrosyan on 9/9/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.data.repository.function

import com.incetro.countypecore.model.function.Function
import com.incetro.countypecore.model.function.concrete.PercentOf
import com.incetro.countypecore.model.function.concrete.UnitToUnit

internal class FunctionRepositoryImpl : FunctionRepository {

    /**
     * Хранит все функции.
     */
    private val functionsList = arrayListOf(UnitToUnit(), PercentOf())

    override fun findFunctionById(id: String): Function =
        functionsList.find { it.id == id }
            ?: throw IllegalStateException("Function with id = $id not found.")

    override fun add(function: Function) {
        functionsList.add(function)
    }
}