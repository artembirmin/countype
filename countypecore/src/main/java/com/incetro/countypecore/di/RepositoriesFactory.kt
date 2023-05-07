/*
 * Created by Artem Petrosyan on 24/10/2021.
 *
 */

package com.incetro.countypecore.di

import android.content.res.Resources
import com.incetro.countypecore.data.file.JsonFileReader
import com.incetro.countypecore.data.repository.city.CityRepository
import com.incetro.countypecore.data.repository.city.CityRepositoryImpl
import com.incetro.countypecore.data.repository.datestamp.DatestampRepository
import com.incetro.countypecore.data.repository.datestamp.DatestampRepositoryImpl
import com.incetro.countypecore.data.repository.function.FunctionRepository
import com.incetro.countypecore.data.repository.function.FunctionRepositoryImpl
import com.incetro.countypecore.data.repository.functiondescription.FunctionDescriptionRepository
import com.incetro.countypecore.data.repository.functiondescription.FunctionDescriptionRepositoryImpl
import com.incetro.countypecore.data.repository.functiondescription.factory.TemplateExpressionToRegexMapper
import com.incetro.countypecore.data.repository.functiondescription.factory.TemplateRegexToTemplateWithFunctionIdMapFactory
import com.incetro.countypecore.data.repository.functiondescription.factory.TemplateRegexToTemplateWithFunctionIdMapFactoryImpl
import com.incetro.countypecore.data.repository.measure.MeasureRepository
import com.incetro.countypecore.data.repository.measure.MeasureRepositoryImpl
import com.incetro.countypecore.data.repository.timestamp.TimestampRepository
import com.incetro.countypecore.data.repository.timestamp.TimestampRepositoryImpl

/**
 * [FunctionRepository], [FunctionDescriptionRepository], [MeasureRepository] factory.
 */
internal object RepositoriesFactory : DiFactory() {

    /**
     * @return [FunctionRepository] instance.
     */
    fun getFunctionRepository(): FunctionRepository {
        return getInstance(FunctionRepositoryImpl::class) as FunctionRepository
    }

    /**
     * @return [FunctionDescriptionRepository] instance.
     */
    fun getFunctionDescriptionRepository(resources: Resources): FunctionDescriptionRepository {
        return getInstance(
            clazz = FunctionDescriptionRepositoryImpl::class,
            getTemplateRegexToTemplateWithFunctionIdMapFactory()
        ) as FunctionDescriptionRepository
    }

    /**
     * @return [MeasureRepository] instance.
     */
    fun getMeasureRepository(resources: Resources): MeasureRepository {
        return getInstance(
            MeasureRepositoryImpl::class,
        ) as MeasureRepository
    }

    fun getCityRepository(): CityRepository {
        return getInstance(
            CityRepositoryImpl::class
        ) as CityRepository
    }

    fun getTimestampRepository(): TimestampRepository {
        return getInstance(
            TimestampRepositoryImpl::class
        ) as TimestampRepository
    }

    fun getDatestampRepository(): DatestampRepository {
        return getInstance(
            DatestampRepositoryImpl::class
        ) as DatestampRepository
    }

    /**
     * @return [JsonFileReader] instance.
     */
    private fun getJsonFileReader(resources: Resources): JsonFileReader {
        return getInstance(
            clazz = JsonFileReader::class,
            resources
        ) as JsonFileReader
    }

    /**
     * @return [getTemplateRegexToTemplateWithFunctionIdMapFactory] instance.
     */
    private fun getTemplateRegexToTemplateWithFunctionIdMapFactory():
            TemplateRegexToTemplateWithFunctionIdMapFactory {
        return getInstance(
            clazz = TemplateRegexToTemplateWithFunctionIdMapFactoryImpl::class,
            TemplateExpressionToRegexMapper()
        ) as TemplateRegexToTemplateWithFunctionIdMapFactory
    }
}