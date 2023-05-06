/*
 * My Application
 *
 * Created by artembirmin on 6/5/2023.
 */

package com.incetro.countype.data.database.city

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.incetro.countypecore.model.function.argumentobject.City

@Entity(
    tableName = CityDto.TABLE_NAME
)
data class CityDto(
    @PrimaryKey
    val id: String,
    val aliases: List<String>,
    val name: String,
    val nameEng: String,
    val timezoneId: String,
) {
    companion object {
        const val TABLE_NAME = "city"
    }

    fun toCity(): City =
            City(aliases, name, nameEng, timezoneId)
}