package com.incetro.countypecore.di

import com.incetro.countypecore.calculator.interactor.phraseunnecessarycleaner.PhraseUnnecessaryCleaner
import com.incetro.countypecore.calculator.interactor.phraseunnecessarycleaner.PhraseUnnecessaryCleanerImpl
import com.incetro.countypecore.data.repository.functiondescription.factory.TemplateExpressionToRegexMapper

internal object PhraseUnnecessaryCleanerFactory: DiFactory() {
    /**
     * @return [PhraseUnnecessaryCleaner] instance.
     */
    fun getPhraseUnnecessaryCleaner(): PhraseUnnecessaryCleaner {
        return getInstance(
            PhraseUnnecessaryCleanerImpl::class,
            TemplateExpressionToRegexMapper()
        ) as PhraseUnnecessaryCleaner
    }
}