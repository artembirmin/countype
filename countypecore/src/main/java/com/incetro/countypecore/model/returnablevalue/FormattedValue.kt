/*
 * Created by Artem Petrosyan on 22/10/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.model.returnablevalue

/**
 * Форматированное возвращаемое значение для [com.incetro.countypecore.model.function.Function]
 */
sealed interface FormattedValue {
    override fun toString(): String
}