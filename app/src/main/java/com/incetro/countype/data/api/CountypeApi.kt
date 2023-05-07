/*
 * My Application
 *
 * Created by artembirmin on 6/5/2023.
 */

package com.incetro.countype.data.api

import com.incetro.countype.data.database.city.CityDto
import com.incetro.countype.data.database.datestamp.DatestampDto
import com.incetro.countype.data.database.functiondescription.FunctionDescriptionDto
import com.incetro.countype.data.database.measure.MeasureDto
import com.incetro.countype.data.database.timestamp.TimestampDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CountypeApi {

    @GET("/measure")
    fun getMeasures(): Single<List<MeasureDto>>

    @GET("/function-description")
    fun getFunctionDescriptions(): Single<List<FunctionDescriptionDto>>

    @GET("/city")
    fun getCities(): Single<List<CityDto>>

    @GET("/datestamp")
    fun getDatestamps(): Single<List<DatestampDto>>

    @GET("/timestamp")
    fun getTimestamps(): Single<List<TimestampDto>>

}