package com.incetro.countypecore.model.function.concrete.notcalculating

import com.incetro.countypecore.model.function.ArgumentType
import com.incetro.countypecore.model.function.ArgumentType.*
import com.incetro.countypecore.model.function.Function
import com.incetro.countypecore.model.returnablevalue.FormattedDouble
import com.incetro.countypecore.model.returnablevalue.FormattedValue

class FormatNumber: Function {
    override val id = formatNumberFunctionId

    override fun invoke(args: List<Any>): FormattedValue {
        val number = args[0] as Double
        return FormattedDouble(number)
    }

    override val argumentTypes = listOf(
        NUMBER,
    )

    companion object {
        const val formatNumberFunctionId = "formatNumber"
    }
}