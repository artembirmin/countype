/*
 * Created by Artem Petrosyan on 13/9/2021.
 *
 */

package com.incetro.countypecore.model.function

/**
 * Типы аргументов, которые принимает [Function].
 */
enum class ArgumentType {
    /**
     * Соответствует модели [com.incetro.countypecore.model.measure.Measure].
     */
    UNIT,

    /**
     * Соответствует модели [Number]. В общем случае приводится к [Double].
     */
    NUMBER,

    /**
     * Процент. В общем случае приводится к [Double].
     */
    PERCENTAGE,

    CITY,

    DATESTAMP,

    TIMESTAMP
}