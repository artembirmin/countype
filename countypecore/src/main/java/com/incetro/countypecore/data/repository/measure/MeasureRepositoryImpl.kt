/*
 * Created by Artem Petrosyan on 11/9/2021.
 *
 */

package com.incetro.countypecore.data.repository.measure

import com.incetro.countypecore.model.function.argumentobject.Measure

internal class MeasureRepositoryImpl :
    MeasureRepository {

    private val measures: MutableList<Measure> = mutableListOf()

    override fun findMeasureByAlias(alias: String): Measure? =
            measures.find { it.aliases.contains(alias) }

    override fun add(measure: Measure) {
        measures.add(measure)
    }

    override fun addAll(measures: List<Measure>) {
        this.measures.addAll(measures)
    }
}