/*
 * Created by Artem Petrosyan on 11/9/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.data.repository.measure

import com.incetro.countypecore.model.function.argumentobject.Measure

/**
 * Репозиторий для работы с [Measure].
 */
interface MeasureRepository {
    /**
     * Ищет единицу измерения [Measure].
     * @return  соответствующий [Measure], если [alias] содержится в [Measure.aliases].
     * Иначе null.
     */
    fun findMeasureByAlias(alias: String): Measure?

    /**
     * Добавляет экземпляр [Measure].
     */
    fun add(measure: Measure)
}
