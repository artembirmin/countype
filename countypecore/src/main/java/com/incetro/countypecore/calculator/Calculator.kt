package com.incetro.countypecore.calculator

/**
 * Вычисляет фразы, переданные в методы.
 */
interface Calculator {
    /**
     * Вычисляет [phrase] или бросает [Exception], где объяснена причина неудавшегося вычисления.
     */
    fun calculateOrException(phrase: String): String

    /**
     * Вычисляет [phrase] и возвращает результат в виде [String]
     * или возвращает `null`, если вычисления не удались.
     */
    fun calculateOrNull(phrase: String): String?

    fun setupData(calculatorConfig: CalculatorConfig)
}
