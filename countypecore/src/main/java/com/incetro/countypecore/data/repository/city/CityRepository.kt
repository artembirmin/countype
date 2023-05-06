/*
 * My Application
 *
 * Created by artembirmin on 5/5/2023.
 */

package com.incetro.countypecore.data.repository.city

import com.incetro.countypecore.model.function.argumentobject.City


interface CityRepository {

    fun findCityByAlias(alias: String): City?

    fun addAll(cities: List<City>)
}
