/*
 * Created by Artem Petrosyan on 24/10/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.di

import com.incetro.countypecore.core.lexemesparser.LexemesParser
import com.incetro.countypecore.core.lexemesparser.LexemesParserImpl
import com.incetro.countypecore.core.phrasestandardizer.PhraseStandardizer
import com.incetro.countypecore.core.phrasestandardizer.PhraseStandardizerImpl
import com.incetro.countypecore.core.phraseunnecessarycleaner.PhraseUnnecessaryCleaner
import com.incetro.countypecore.core.phraseunnecessarycleaner.PhraseUnnecessaryCleanerImpl
import com.incetro.countypecore.data.repository.functiondescription.factory.TemplateExpressionToRegexMapper

/**
 * [LexemesParser] factory.
 */
internal object CoreFactory : DiFactory() {

    /**
     * @return [LexemesParser] instance.
     */
    fun getLexemesParser(): LexemesParser {
        return getInstance(clazz = LexemesParserImpl::class) as LexemesParser
    }

    /**
     * @return [PhraseStandardizer] instance.
     */
    fun getPhraseStandardizer(): PhraseStandardizer {
        return getInstance(PhraseStandardizerImpl::class) as PhraseStandardizer
    }

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