/*
 * Created by Artem Petrosyan on 28/10/2021.
 *
 */

package com.incetro.countypecore.model.returnablevalue

import com.incetro.countypecore.model.function.argumentobject.Measure

class FormattedMeasure(private val measure: Measure) : FormattedValue {
    override fun toString(): String {
        return measure.symbol
    }
}