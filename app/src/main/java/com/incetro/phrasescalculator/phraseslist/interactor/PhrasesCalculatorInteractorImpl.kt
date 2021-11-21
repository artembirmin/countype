package com.incetro.phrasescalculator.phraseslist.interactor

class PhrasesCalculatorInteractorImpl : PhrasesCalculatorInteractor {
    override fun calculatePhrase(phrase: String): String {
        return "ответ на $phrase"
    }
}