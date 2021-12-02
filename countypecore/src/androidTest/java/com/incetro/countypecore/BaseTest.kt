/*
 * Created by Artem Petrosyan on 26/9/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore

import androidx.test.platform.app.InstrumentationRegistry
import com.incetro.countypecore.core.LexemesParserImpl
import com.incetro.countypecore.data.repository.function.FunctionRepositoryImpl
import com.incetro.countypecore.di.LexemesParserFactory
import com.incetro.countypecore.di.PhraseStandardizerFactory
import com.incetro.countypecore.di.CalculationInteractorFactory
import com.incetro.countypecore.di.RepositoriesFactory
import com.incetro.countypecore.calculator.interactor.calculationinteractor.CalculationInteractorImpl

internal open class BaseTest {

    private val resources = InstrumentationRegistry.getInstrumentation().targetContext.resources

    val phraseStandardizer = PhraseStandardizerFactory.getPhraseStandardizer()

    val lexemesParserImpl = LexemesParserFactory.getLexemesParser() as LexemesParserImpl

    val dataForTest = DataForTest()

    val functionRepository = RepositoriesFactory.getFunctionRepository() as FunctionRepositoryImpl
    val functionDescriptionRepository =
        RepositoriesFactory.getFunctionDescriptionRepository(resources)
    val unitRepository = RepositoriesFactory.getMeasureRepository(resources)

    val mainInteractor =
        CalculationInteractorFactory.getCalculationInteractor(resources) as CalculationInteractorImpl
}