/*
 * Created by Artem Petrosyan on 28/10/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.model.returnablevalue

class FormattedDouble(private val double: Double) : FormattedValue {
    override fun toString(): String =
        if (double % 1 == 0.0) double.toInt().toString()
        else double.toString()
}