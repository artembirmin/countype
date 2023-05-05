/*
 * Created by Artem Petrosyan on 22/10/2021.
 *
 */

package com.incetro.countypecore.model.returnablevalue

/**
 * Форматированное возвращаемое значение для [com.incetro.countypecore.model.function.Function]
 */
sealed interface FormattedValue {
    override fun toString(): String
}