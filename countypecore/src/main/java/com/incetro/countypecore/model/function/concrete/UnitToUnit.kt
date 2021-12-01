/*
 * Created by Artem Petrosyan on 27/9/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

/*
 * Created by Artem Petrosyan on 26/9/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

/*
 * Created by Artem Petrosyan on 9/9/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.model.function.concrete

import com.incetro.countypecore.model.function.ArgumentType
import com.incetro.countypecore.model.function.ArgumentType.NUMBER
import com.incetro.countypecore.model.function.ArgumentType.UNIT
import com.incetro.countypecore.model.function.Function
import com.incetro.countypecore.model.function.argumentobject.Measure
import com.incetro.countypecore.model.returnablevalue.FormattedDouble
import com.incetro.countypecore.model.returnablevalue.FormattedMeasure
import com.incetro.countypecore.model.returnablevalue.FormattedPair
import com.incetro.countypecore.model.returnablevalue.FormattedValue

/**
 * [Measure] converting function.
 */
class UnitToUnit : Function {

    override val id = unitToUnitFunctionId

    /**
     * Порядок и типы аргументов: ([ArgumentType.NUMBER],[ArgumentType.UNIT]).
     * @return [Double].
     * @throws [IllegalArgumentException] если у единиц измерения не совпали base measure.
     */
    override fun invoke(args: List<Any>): FormattedValue {
        val number = args[0] as Double
        val fromMeasure = args[1] as Measure
        val toMeasure = args[2] as Measure
        if (fromMeasure.baseMeasureId == toMeasure.baseMeasureId) {
            val answer = (number * fromMeasure.multiplier) / toMeasure.multiplier
            return FormattedPair(FormattedDouble(answer), FormattedMeasure(toMeasure))
        } else throw IllegalArgumentException("Incompatible units")
    }

    override val argumentTypes = listOf(
        NUMBER,
        UNIT,
    )

    companion object {
        const val unitToUnitFunctionId = "unitToUnit"
    }
}
