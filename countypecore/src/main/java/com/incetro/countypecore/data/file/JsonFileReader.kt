/*
 * Created by Artem Petrosyan on 28/10/2021.
 *
 */

package com.incetro.countypecore.data.file

import android.content.res.Resources
import androidx.annotation.RawRes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

/**
 * Осуществляет чтение JSON файла и десериализацию содержимого в объект.
 */
internal class JsonFileReader(private val resources: Resources) {

    /**
     * [Gson] instance.
     */
    val gson: Gson = GsonBuilder().create()

    /**
     * Десериализует объект типа [T] из JSON файла, находящегося в `resources` folder.
     * [fileResId] is resource id. It must be json file.
     * Example: "object.json", "json/object.json".
     * @return Десериализованный объект типа [T].
     */
    inline fun <reified T> decerializeObjectFromJsonFileFromResources(@RawRes fileResId: Int): T {
        val tTypeToken = object : TypeToken<T>() {}.type
        return gson.fromJson(readJsonFileFromResources(fileResId), tTypeToken)
    }

    /**
     * @return Содержимое JSON file with [fileResId].
     */
    fun readJsonFileFromResources(@RawRes fileResId: Int): String {
        return resources.openRawResource(fileResId).bufferedReader()
            .use { it.readText() }
    }
}