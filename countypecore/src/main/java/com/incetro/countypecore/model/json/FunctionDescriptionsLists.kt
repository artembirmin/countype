/*
 * Created by Artem Petrosyan on 25/10/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.model.json

import com.incetro.countypecore.model.function.functiondescription.FunctionDescription

/**
 * Model for decerialization from JSON file containing [FunctionDescription].
 */
data class FunctionDescriptionsLists(
    val unitRelated: List<FunctionDescription>,
    val percentage: List<FunctionDescription>
) {
    /**
     * Flatten all lists in model.
     */
    fun flatten(): List<FunctionDescription> {
        return unitRelated + percentage
    }
}