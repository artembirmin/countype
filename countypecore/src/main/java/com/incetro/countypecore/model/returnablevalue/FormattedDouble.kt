/*
 * Created by Artem Petrosyan on 28/10/2021.
 *
 */

package com.incetro.countypecore.model.returnablevalue

class FormattedDouble(private val double: Double) : FormattedValue {
    override fun toString(): String =
        if (double % 1 == 0.0) double.toInt().toString()
        else double.toString()
}