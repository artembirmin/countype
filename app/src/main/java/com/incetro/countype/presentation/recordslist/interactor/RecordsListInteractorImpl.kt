package com.incetro.countype.presentation.recordslist.interactor

import com.incetro.countypecore.calculator.Calculator

class RecordsListInteractorImpl(val calculator: Calculator) : RecordsListInteractor {

    override fun calculatePhrase(phrase: String): String {
        return calculator.calculateOrNull(phrase) ?: ""
    }
}