/*
 * My Application
 *
 * Created by artembirmin on 6/5/2023.
 */

package com.incetro.countype.data.database.datestamp

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.incetro.countypecore.model.function.argumentobject.Datestamp

@Entity(
    tableName = DatestampDto.TABLE_NAME
)
data class DatestampDto(
    @PrimaryKey
    val id: String,
    val dateFormat: String,
) {
    companion object {
        const val TABLE_NAME = "datestamp"
    }

    fun toDatestamp(): Datestamp =
            Datestamp(dateFormat)
}