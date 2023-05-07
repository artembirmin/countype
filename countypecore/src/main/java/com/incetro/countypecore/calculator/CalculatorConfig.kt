/*
 * My Application
 *
 * Created by artembirmin on 7/5/2023.
 */

package com.incetro.countypecore.calculator

import com.incetro.countypecore.model.function.argumentobject.City
import com.incetro.countypecore.model.function.argumentobject.Datestamp
import com.incetro.countypecore.model.function.argumentobject.Measure
import com.incetro.countypecore.model.function.argumentobject.Timestamp
import com.incetro.countypecore.model.function.functiondescription.FunctionDescription

data class CalculatorConfig(
    val functionDescriptions: List<FunctionDescription>,
    val measures: List<Measure>,
    val cities: List<City>,
    val datestamps: List<Datestamp>,
    val timestamps: List<Timestamp>,
)
