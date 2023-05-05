/*
 * My Application
 *
 * Created by artembirmin on 5/5/2023.
 */

package com.incetro.countypecore.data.repository.datestamp

import com.incetro.countypecore.model.function.argumentobject.Datestamp
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

internal class DatestampRepositoryImpl : DatestampRepository {

    private val datestamps: MutableList<Datestamp> = mutableListOf()

    override fun findDatestampByDate(dateStr: String): Datestamp? {
        datestamps.forEach {
            val date = parseDate(dateStr, it.dateFormat)
            if (date != null) return it.copy(date = date)
        }
        return null
    }

    override fun addAll(datestamps: List<Datestamp>) {
        this.datestamps.addAll(datestamps)
    }

    private fun parseDate(date: String, format: String): DateTime? {
        val formatter = DateTimeFormat
            .forPattern(format)

        return try {
            DateTime.parse(date, formatter)
        } catch (e: Exception) {
            null
        }
    }
}