package com.incetro.countypecore.calculator

import android.content.res.Resources
import com.incetro.countypecore.di.CalculatorFactory
import com.incetro.countypecore.calculator.interactor.calculationinteractor.CalculationInteractor

class CalculatorImpl(
    private val resources: Resources,
    private val recognitionInteractor: CalculationInteractor =
        CalculatorFactory.getCalculationInteractor(resources)
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