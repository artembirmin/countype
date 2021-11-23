/*
 * Created by Artem Petrosyan on 28/10/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.model.returnablevalue

import com.incetro.countypecore.model.measure.Measure

class FormattedMeasure(private val measure: Measure) : FormattedValue {
    override fun toString(): String {
        return measure.symbol
    }
}