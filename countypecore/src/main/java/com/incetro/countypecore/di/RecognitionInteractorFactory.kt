/*
 * Created by Artem Petrosyan on 24/10/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.di

import android.content.res.Resources
import com.incetro.countypecore.calculator.interactor.calculationinteractor.CalculationInteractor
import com.incetro.countypecore.calculator.interactor.calculationinteractor.CalculationInteractorImpl
import com.incetro.countypecore.calculator.interactor.phraseunnecessarycleaner.PhraseUnnecessaryCleaner
import com.incetro.countypecore.calculator.interactor.phraseunnecessarycleaner.PhraseUnnecessaryCleanerImpl
import com.incetro.countypecore.data.repository.functiondescription.factory.TemplateExpressionToRegexMapper

/**
 * [CalculationInteractor] factory.
 */
internal object RecognitionInteractorFactory : DiFactory() {
    /**
     * @return [CalculationInteractor] instance.
     */
    fun getCalculationInteractor(resources: Resources): CalculationInteractor {
        return getInstance(
            CalculationInteractorImpl::class,
            LexemesParserFactory.getLexemesParser(),
            RepositoriesFactory.getFunctionRepository(),
            RepositoriesFactory.getFunctionDescriptionRepository(resources),
            RepositoriesFactory.getMeasureRepository(resources),
            PhraseStandardizerFactory.getPhraseStandardizer(),
            PhraseUnnecessaryCleanerFactory.getPhraseUnnecessaryCleaner()
        ) as CalculationInteractor
    }

}