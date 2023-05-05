/*
 * My Application
 *
 * Created by artembirmin on 5/5/2023.
 */

package com.incetro.countypecore.data.repository.timestamp

import com.incetro.countypecore.model.function.argumentobject.Timestamp
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

internal class TimestampRepositoryImpl : TimestampRepository {

    private val timestamps: MutableList<Timestamp> = mutableListOf()

    override fun findTimestampByTime(time: String): Timestamp? {
        timestamps.forEach {
            val dateTime = parseTime(time, it.timeFormat)
            if (dateTime != null) return it.copy(time = dateTime)
        }
        return null
    }

    override fun addAll(timestamps: List<Timestamp>) {
        this.timestamps.addAll(timestamps)
    }

    private fun parseTime(time: String, format: String): DateTime? {
        val formatter = DateTimeFormat
            .forPattern(format)

        return try {
            DateTime.parse(time, formatter)
        } catch (e: Exception) {
            null
        }
    }
}