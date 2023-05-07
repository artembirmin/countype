package com.incetro.countypecore.calculator

import android.content.res.Resources
import com.incetro.countypecore.calculator.interactor.calculationinteractor.CalculationInteractor
import com.incetro.countypecore.di.InteractorFactory
import timber.log.Timber

class CalculatorImpl(
    private val resources: Resources,
    private val recognitionInteractor: CalculationInteractor =
            InteractorFactory.getCalculationInteractor(resources),
) : Calculator {


    override fun setupData(
        calculatorConfig: CalculatorConfig,
    ) {
        Timber.plant(Timber.DebugTree())
        recognitionInteractor.setupData(
            calculatorConfig.functionDescriptions,
            calculatorConfig.measures,
            calculatorConfig.cities,
            calculatorConfig.datestamps,
            calculatorConfig.timestamps
        )
    }

    override fun calculateOrException(phrase: String): String {
        return recognitionInteractor.calculate(phrase).toString()
    }

    override fun calculateOrNull(phrase: String): String? {
        return try {
            calculateOrException(phrase)
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
    }
}