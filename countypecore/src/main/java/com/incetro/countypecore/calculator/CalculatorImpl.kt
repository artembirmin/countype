package com.incetro.countypecore.calculator

import android.content.res.Resources
import com.incetro.countypecore.di.RecognitionInteractorFactory
import com.incetro.countypecore.interactor.calculationinteractor.CalculationInteractor

class CalculatorImpl(
    private val resources: Resources,
    private val recognitionInteractor: CalculationInteractor =
        RecognitionInteractorFactory.getRecognitionInteractor(resources)
) : Calculator {

    override fun calculateOrException(phrase: String): String {
        return recognitionInteractor.calculate(phrase).toString()
    }

    override fun calculateOrNull(phrase: String): String? {
        return try {
            calculateOrException(phrase)
        } catch (e: Exception) {
            null
        }
    }
}