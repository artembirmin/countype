/*
 * Created by Artem Petrosyan on 11/9/2021.
 *
 */

package com.incetro.countypecore.data.repository.measure

import com.incetro.countypecore.R
import com.incetro.countypecore.data.file.JsonFileReader
import com.incetro.countypecore.model.function.argumentobject.Measure
import com.incetro.countypecore.model.function.functiondescription.FunctionDescription
import com.incetro.countypecore.model.json.MeasureLists

internal class MeasureRepositoryImpl(private val jsonFileReader: JsonFileReader) :
    MeasureRepository {

    /**
     * Name of JSON file with function descriptions in `resources`.
     */
    private val measureJsonFileName = R.raw.measure_descriptions

    /**
     * Хранит объекты [FunctionDescription] в рантайме.
     * Экземпляры извлекаются из файла с именем [measureJsonFileName].
     */
    private val measures: MutableList<Measure> =
        jsonFileReader.decerializeObjectFromJsonFileFromResources<MeasureLists>(
            measureJsonFileName
        ).flatten().toMutableList()

    override fun findMeasureByAlias(alias: String): Measure? =
        measures.find { it.aliases.contains(alias) }

    override fun add(measure: Measure) {
        measures.add(measure)
    }
}