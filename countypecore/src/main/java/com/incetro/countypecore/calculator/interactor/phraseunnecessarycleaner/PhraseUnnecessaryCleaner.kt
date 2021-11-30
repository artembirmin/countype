package com.incetro.countypecore.calculator.interactor.phraseunnecessarycleaner

import com.incetro.countypecore.model.function.functiondescription.Template

interface PhraseUnnecessaryCleaner {
    fun createCleanedPhrase(phrase: String, template: Template): String
}