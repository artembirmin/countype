/*
 * Created by Artem Petrosyan on 24/10/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.di

import android.content.res.Resources
import com.incetro.countypecore.interactor.calculationinteractor.CalculationInteractor
import com.incetro.countypecore.interactor.calculationinteractor.CalculationInteractorImpl

/**
 * [CalculationInteractor] factory.
 */
internal object RecognitionInteractorFactory : DiFactory() {
    /**
     * @return [CalculationInteractor] instance.
     */
    fun getRecognitionInteractor(resources: Resources): CalculationInteractor {
        return getInstance(
            CalculationInteractorImpl::class,
            LexemesParserFactory.getLexemesParser(),
            RepositoriesFactory.getFunctionRepository(),
            RepositoriesFactory.getFunctionDescriptionRepository(resources),
            RepositoriesFactory.getMeasureRepository(resources),
            PhraseStandardizerFactory.getPhraseStandardizer()
        ) as CalculationInteractor

    }
}