/*
 * Created by Artem Petrosyan on 9/9/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.model.measure

/**
 * Measure (Unit).
 */
data class Measure(
    /**
     * Варианты употребления во фразе.
     */
    val aliases: List<String>,

    /**
     * Идентификатор.
     */
    val id: String,

    /**
     * Символ.
     */
    val symbol: String,

    /**
     * Идентификатор единицы измерения,
     * производной от которой является current measure.
     */
    val baseMeasureId: String,

    /**
     * На сколько нужно домножить базовую единицу с идентификатором [baseMeasureId],
     * чтобы получить текущую единицу измерения.
     */
    val multiplier: Double
)