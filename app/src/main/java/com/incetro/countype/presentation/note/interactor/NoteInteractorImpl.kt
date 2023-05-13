package com.incetro.countype.presentation.note.interactor

import com.incetro.countype.presentation.toReadableDateTime
import com.incetro.countypecore.calculator.Calculator
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

class NoteInteractorImpl(private val calculator: Calculator) : NoteInteractor {

    override fun calculatePhrase(phrase: String): String {
        if (phrase == "Время в Сингапуре") return DateTime.now(DateTimeZone.forID("Asia/Singapore"))
            .toReadableDateTime()
        return calculator.calculateOrNull(phrase) ?: ""
    }
}