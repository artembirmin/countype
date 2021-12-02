package com.incetro.countypecore.model.function.concrete.notcalculating

import com.incetro.countypecore.model.function.ArgumentType
import com.incetro.countypecore.model.function.ArgumentType.*
import com.incetro.countypecore.model.function.Function
import com.incetro.countypecore.model.function.argumentobject.Measure
import com.incetro.countypecore.model.returnablevalue.FormattedDouble
import com.incetro.countypecore.model.returnablevalue.FormattedMeasure
import com.incetro.countypecore.model.returnablevalue.FormattedPair
import com.incetro.countypecore.model.returnablevalue.FormattedValue

class FormatNumberAndMeasure : Function {
    override val id = formatNumberAndUnitFunctionId

    override fun invoke(args: List<Any>): FormattedValue {
        val number = args[0] as Double
        val measure = args[1] as Measure
        return FormattedPair(FormattedDouble(number), FormattedMeasure(measure))
    }

    override val argumentTypes = listOf(
        NUMBER,
        UNIT,
    )

    companion object {
        const val formatNumberAndUnitFunctionId = "formatNumberAndUnit"
    }
}