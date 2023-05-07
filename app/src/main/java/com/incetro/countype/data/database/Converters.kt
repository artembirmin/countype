package com.incetro.countype.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.incetro.countype.data.database.functiondescription.TemplateDto
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

    @TypeConverter
    @JvmStatic
    fun fromStringToTemplateList(value: String): List<TemplateDto> {
        val listType = object : TypeToken<ArrayList<TemplateDto>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun fromTemplateListToString(list: List<TemplateDto>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun fromStringToIntList(value: String): List<Int> {
        val listType = object : TypeToken<ArrayList<Int>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun fromIntListToString(list: List<Int>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    @JvmStatic
    fun fromStringToStringList(value: String): List<String> {
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun fromStringListToString(list: List<String>): String {
        return gson.toJson(list)
    }


}