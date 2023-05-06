/*
 * My Application
 *
 * Created by artembirmin on 6/5/2023.
 */

package com.incetro.countype.data.database.measure

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.incetro.countypecore.model.function.argumentobject.Measure

@Entity(
    tableName = MeasureDto.TABLE_NAME
)
data class MeasureDto(
    @PrimaryKey
    val id: String,
    val aliases: List<String> = emptyList(),
    val name: String,
    val symbol: String,
    val baseMeasureName: String,
    val multiplier: Double,
) {
    companion object {
        const val TABLE_NAME = "measure"
    }

    fun toMeasure(): Measure =
            Measure(aliases, name, symbol, baseMeasureName, multiplier)
}