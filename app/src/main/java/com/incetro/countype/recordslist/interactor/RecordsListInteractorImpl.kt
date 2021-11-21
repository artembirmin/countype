package com.incetro.countype.recordslist.interactor

class RecordsListInteractorImpl : RecordsListInteractor {
    override fun calculatePhrase(phrase: String): String {
        return "ответ на $phrase"
    }
}