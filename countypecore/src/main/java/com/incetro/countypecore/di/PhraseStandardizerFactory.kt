/*
 * Created by Artem Petrosyan on 24/10/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.di

import com.incetro.countypecore.calculator.interactor.phrasestandardizer.PhraseStandardizer
import com.incetro.countypecore.calculator.interactor.phrasestandardizer.PhraseStandardizerImpl

/**
 * [PhraseStandardizer] factory.
 */
internal object PhraseStandardizerFactory : DiFactory() {
    /**
     * @return [PhraseStandardizer] instance.
     */
    fun getPhraseStandardizer(): PhraseStandardizer {
        return getInstance(PhraseStandardizerImpl::class) as PhraseStandardizer
    }
}