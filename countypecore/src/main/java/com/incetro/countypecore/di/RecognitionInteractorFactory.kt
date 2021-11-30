/*
 * Created by Artem Petrosyan on 24/10/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.di

import android.content.res.Resources
import com.incetro.countypecore.data.repository.functiondescription.factory.TemplateExpressionToRegexMapper
import com.incetro.countypecore.calculator.interactor.phraseunnecessarycleaner.PhraseUnnecessaryCleaner
import com.incetro.countypecore.calculator.interactor.calculationinteractor.CalculationInteractor
import com.incetro.countypecore.calculator.interactor.calculationinteractor.CalculationInteractorImpl
import com.incetro.countypecore.calculator.interactor.phrasestandardizer.PhraseStandardizer
import com.incetro.countypecore.calculator.interactor.phrasestandardizer.PhraseStandardizerImpl
import com.incetro.countypecore.calculator.interactor.phraseunnecessarycleaner.PhraseUnnecessaryCleanerImpl

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
            getPhraseStandardizer(),
            getPhraseUnnecessaryCleaner()
        ) as CalculationInteractor

    }

    /**
     * @return [PhraseStandardizer] instance.
     */
    private fun getPhraseStandardizer(): PhraseStandardizer {
        return getInstance(PhraseStandardizerImpl::class) as PhraseStandardizer
    }

    /**
     * @return [PhraseUnnecessaryCleaner] instance.
     */
    private fun getPhraseUnnecessaryCleaner(): PhraseUnnecessaryCleaner {
        return getInstance(
            PhraseUnnecessaryCleanerImpl::class,
            TemplateExpressionToRegexMapper()
        ) as PhraseUnnecessaryCleaner
    }
}