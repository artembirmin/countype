/*
 * Created by Artem Petrosyan on 20/9/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.core

import com.incetro.countypecore.BaseTest
import org.junit.Assert.assertEquals
import org.junit.Test

internal class LexemesParserImplTest : BaseTest() {

    @Test
    fun getTokens() {
        dataForTest.phraseToLexemes.forEach { (phrase, lexemes) ->
            assertEquals(
                lexemes,
                lexemesParserImpl.getLexemes(
                    phraseStandardizer.createStandardizedPhrase(phrase),
                    dataForTest.phrasesToTemplate[phrase]!!
                )
            )
        }
    }

    @Test
    fun getSeparators() {
        dataForTest.templateToSeparators.forEach { (template, expectedSeparators) ->
            assertEquals(expectedSeparators, lexemesParserImpl.getSeparators(template.expression))
        }
    }

    @Test
    fun makeTokensBySeparators() {
        val phraseToLexemes = dataForTest.phraseToLexemes
        dataForTest.templateToPhrases.forEach { (template, phrases) ->
            phrases.map { phraseStandardizer.createStandardizedPhrase(it) }.forEach { phrase ->
                assertEquals(
                    phraseToLexemes[phrase],
                    lexemesParserImpl.getLexemsBySeparators(
                        phrase, lexemesParserImpl.getSeparators(template.expression)
                    )
                )
            }
        }
    }
}