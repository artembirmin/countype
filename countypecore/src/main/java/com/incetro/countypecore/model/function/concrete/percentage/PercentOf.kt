/*
 * Created by Artem Petrosyan on 26/9/2021.
 *
 */

package com.incetro.countypecore.model.function.concrete.percentage

import com.incetro.countypecore.model.function.ArgumentType
import com.incetro.countypecore.model.function.ArgumentType.NUMBER
import com.incetro.countypecore.model.function.ArgumentType.PERCENTAGE
import com.incetro.countypecore.model.function.Function
import com.incetro.countypecore.model.returnablevalue.FormattedDouble
import com.incetro.countypecore.model.returnablevalue.FormattedValue

/**
 * Percentage function.
 */
class PercentOf : Function {

    override val id = percentOfFunctionId

    /**
     * Порядок и типы аргументов: ([ArgumentType.PERCENTAGE],[ArgumentType.NUMBER]).
     * @return [Double].
     */
    override fun invoke(args: List<Any>): FormattedValue {
        val percentage = args[0] as Double
        val number = args[1] as Double
        val answer = number * percentage / 100
        return FormattedDouble(answer)
    }

    override val argumentTypes = listOf(
        PERCENTAGE,
        NUMBER
    )

    companion object {
        const val percentOfFunctionId = "percentOf"
    }
}