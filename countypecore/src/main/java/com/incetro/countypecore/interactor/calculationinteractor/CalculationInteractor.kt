/*
 * Created by Artem Petrosyan on 7/9/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.interactor.calculationinteractor

import com.incetro.countypecore.model.returnablevalue.FormattedValue

/**
 * Calculation interactor.
 */
interface CalculationInteractor {

    /**
     * Вычисляет команду, описанную в [phrase].
     */
    fun calculate(phrase: String): FormattedValue
}