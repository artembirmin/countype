/*
 * Created by Artem Petrosyan on 7/9/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.calculator.interactor.calculationinteractor

import com.incetro.countypecore.model.function.argumentobject.City
import com.incetro.countypecore.model.function.argumentobject.Datestamp
import com.incetro.countypecore.model.function.argumentobject.Measure
import com.incetro.countypecore.model.function.argumentobject.Timestamp
import com.incetro.countypecore.model.function.functiondescription.FunctionDescription
import com.incetro.countypecore.model.returnablevalue.FormattedValue

/**
 * Calculation interactor.
 */
interface CalculationInteractor {

    /**
     * Вычисляет команду, описанную в [phrase].
     */
    fun calculate(phrase: String): FormattedValue
    fun setupData(
        functionDescriptions: List<FunctionDescription>,
        measures: List<Measure>,
        cities: List<City>,
        datestamps: List<Datestamp>,
        timestamps: List<Timestamp>,
    )
}