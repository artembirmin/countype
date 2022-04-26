package com.incetro.countype.presentation.note.interactor

import com.incetro.countypecore.calculator.Calculator

class NoteInteractorImpl(private val calculator: Calculator) : NoteInteractor {

    override fun calculatePhrase(phrase: String): String {
        return calculator.calculateOrNull(phrase) ?: ""
    }
}