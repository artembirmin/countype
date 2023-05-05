/*
 * My Application
 *
 * Created by artembirmin on 5/5/2023.
 */

package com.incetro.countypecore.data.repository.city

import com.incetro.countypecore.model.function.argumentobject.City

internal class CityRepositoryImpl : CityRepository {

    private val datestamps: MutableList<City> = mutableListOf()

    override fun findDatestampByDate(alias: String): City? {
        return datestamps.find {
            it.aliases.contains(alias)
        }
    }

    override fun addAll(cities: List<City>) {
        this.datestamps.addAll(cities)
    }
}