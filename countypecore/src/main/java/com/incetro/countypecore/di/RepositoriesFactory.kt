/*
 * Created by Artem Petrosyan on 24/10/2021.
 *
 */

package com.incetro.countypecore.di

import android.content.res.Resources
import com.incetro.countypecore.data.file.JsonFileReader
import com.incetro.countypecore.data.repository.function.FunctionRepository
import com.incetro.countypecore.data.repository.function.FunctionRepositoryImpl
import com.incetro.countypecore.data.repository.functiondescription.FunctionDescriptionRepository
import com.incetro.countypecore.data.repository.functiondescription.FunctionDescriptionRepositoryImpl
import com.incetro.countypecore.data.repository.functiondescription.factory.TemplateExpressionToRegexMapper
import com.incetro.countypecore.data.repository.functiondescription.factory.TemplateRegexToTemplateWithFunctionIdMapFactory
import com.incetro.countypecore.data.repository.functiondescription.factory.TemplateRegexToTemplateWithFunctionIdMapFactoryImpl
import com.incetro.countypecore.data.repository.measure.MeasureRepository
import com.incetro.countypecore.data.repository.measure.MeasureRepositoryImpl

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
            getTemplateRegexToTemplateWithFunctionIdMapFactory(),
            getJsonFileReader(resources)
        ) as FunctionDescriptionRepository
    }

    /**
     * @return [MeasureRepository] instance.
     */
    fun getMeasureRepository(resources: Resources): MeasureRepository {
        return getInstance(
            MeasureRepositoryImpl::class,
            getJsonFileReader(resources)
        ) as MeasureRepository
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