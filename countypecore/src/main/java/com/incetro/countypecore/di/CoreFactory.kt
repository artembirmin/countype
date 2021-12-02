/*
 * Created by Artem Petrosyan on 24/10/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.di

import com.incetro.countypecore.core.LexemesParser
import com.incetro.countypecore.core.LexemesParserImpl

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
}