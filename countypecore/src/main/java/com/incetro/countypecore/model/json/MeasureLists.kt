/*
 * Created by Artem Petrosyan on 30/10/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.model.json

import com.incetro.countypecore.model.function.argumentobject.Measure

/**
 * Model for decerialization from JSON file containing [Measure].
 */
data class MeasureLists(
    val length: List<Measure>,
    val area: List<Measure>
) {
    /**
     * Flatten all lists in model.
     */
    fun flatten(): List<Measure> {
        return length + area
    }
}