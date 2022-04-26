package com.incetro.countype.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.incetro.countype.entity.Record
import java.util.*

object Converters {

    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun fromStringToRecordList(value: String): List<Record> {
        val listType = object : TypeToken<ArrayList<Record>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun fromRecordsListToString(list: List<Record>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun toDateTime(value: Long) = Date(value)

    @TypeConverter
    @JvmStatic
    fun fromDateTime(value: Date) = value.time


}