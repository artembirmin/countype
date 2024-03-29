/*
 * Created by Artem Petrosyan on 24/10/2021.
 *
 */

package com.incetro.countypecore.di

import android.content.res.Resources
import com.incetro.countypecore.calculator.interactor.calculationinteractor.CalculationInteractor
import com.incetro.countypecore.calculator.interactor.calculationinteractor.CalculationInteractorImpl

/**
 * [CalculationInteractor] factory.
 */
internal object InteractorFactory : DiFactory() {
    /**
     * @return [CalculationInteractor] instance.
     */
    fun getCalculationInteractor(resources: Resources): CalculationInteractor {
        return getInstance(
            CalculationInteractorImpl::class,
            CoreFactory.getLexemesParser(),
            CoreFactory.getPhraseStandardizer(),
            CoreFactory.getPhraseUnnecessaryCleaner(),
            RepositoriesFactory.getFunctionRepository(),
            RepositoriesFactory.getFunctionDescriptionRepository(resources),
            RepositoriesFactory.getMeasureRepository(resources),
            RepositoriesFactory.getCityRepository(),
            RepositoriesFactory.getTimestampRepository(),
            RepositoriesFactory.getDatestampRepository()
        ) as CalculationInteractor
    }
}