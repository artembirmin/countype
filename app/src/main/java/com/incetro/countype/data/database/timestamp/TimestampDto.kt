/*
 * My Application
 *
 * Created by artembirmin on 6/5/2023.
 */

package com.incetro.countype.data.database.timestamp

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.incetro.countypecore.model.function.argumentobject.Timestamp

@Entity(
    tableName = TimestampDto.TABLE_NAME
)
data class TimestampDto(
    @PrimaryKey
    val id: String,
    val timeFormat: String,
) {
    companion object {
        const val TABLE_NAME = "timestamp"
    }

    fun toTimestamp(): Timestamp =
            Timestamp(timeFormat)
}