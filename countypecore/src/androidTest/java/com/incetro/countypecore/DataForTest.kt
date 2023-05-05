/*
 * Created by Artem Petrosyan on 22/9/2021.
 *
 */

package com.incetro.countypecore

import com.incetro.countypecore.model.function.functiondescription.Template

/**
 * Набор данных для теста.
 */
internal class DataForTest {

    /**
     * Template for [com.incetro.countypecore.model.function.concrete.UnitToUnit].
     */
    private val template0 = Template(
        "__number __unit в __unit",
        listOf(1, 2, 3)
    )
    private val separators0 = listOf(" ", " в ")
    private val phrase00 = "25 километров в метры"
    private val lexemes00 = listOf("25", "километров", "метры")
    private val phrase01 = "25 квадратных километров в квадратные метры"
    private val lexemes01 = listOf("25", "квадратных километров", "квадратные метры")

//    private val template7 = Template(
//        "сколько __unit в __number __unit",
//        listOf(3, 1, 2)
//    )
//    private val separators7 = listOf("сколько ", " в ", " ")
//    private val phrase70 = "сколько метров в 100 сантиметрах"
//    private val lexemes70 = listOf("100", "сантиметрах", "метрах")
//    private val phrase71 = "сколько квадратных километров в 100 квадратных метрах"
//    private val lexemes71 = listOf("100", "квадратных метрах", "квадратных километров")

    /**
     * Template for [com.incetro.countypecore.model.function.concrete.PercentOf].
     */
    private val template1 = Template(
        "__percentage от __number",
        listOf(1, 2)
    )
    private val separators1 = listOf(" от ")
    private val phrase10 = "12 процентов от 120"
    private val lexemes10 = listOf("12%", "120")
    private val phrase11 = "12% от 120"
    private val lexemes11 = listOf("12%", "120")

    private val template2 = Template(
        "__number это __percentage от чего",
        listOf(1, 2)
    )
    private val separators2 = listOf(" это ", " от чего")
    private val phrase20 = "12 это 10 процентов от чего"
    private val lexemes20 = listOf("12", "10%")
    private val phrase21 = "12 это 10% от чего"
    private val lexemes21 = listOf("12", "10%")

    private val template3 = Template(
        "__number сколько % от __number",
        listOf(1, 2)
    )
    private val separators3 = listOf(" сколько % от ")
    private val phrase30 = "12 сколько % от 120"
    private val lexemes30 = listOf("12", "120")

    private val template4 = Template(
        "__number сколько процентов от __number",
        listOf(1, 2)
    )
    private val separators4 = listOf(" сколько процентов от ")
    private val phrase40 = "12 сколько процентов от 120"
    private val lexemes40 = listOf("12", "120")

    private val template5 = Template(
        "половина __number",
        listOf(1)
    )
    private val separators5 = listOf("половина ")
    private val phrase50 = "половина 12"
    private val lexemes50 = listOf("12")

    private val template6 = Template(
        "меньшее из __number и __number",
        listOf(1, 2)
    )
    private val separators6 = listOf("меньшее из ", " и ")
    private val phrase60 = "меньшее из 12 и 120"
    private val lexemes60 = listOf("12", "120")

    /**
     * Шаблоны и сепараторы, которые должны из них достаться
     */
    // А если случайно пользователь поставил пробел в конце фразы?
    // Предлагаю перед отправкой аргументов в функцию, после определения типов, очищать их от пробелов.
    // Хотя функции все равно, т.к. она берет аргументы по порядку и до последнего она не дойдет.
    val templateToSeparators = mapOf(
        template0
                to separators0,
        template1
                to separators1,
        template2
                to separators2,
        template3
                to separators3,
        template4
                to separators4,
        template5
                to separators5,
        template6
                to separators6,
    )

    /**
     * Шаблоны и соответствующие им фразы
     */
    val templateToPhrases = mapOf(
        template0
                to listOf(
            phrase00,
            phrase01
        ),
        template1
                to listOf(
            phrase10,
            phrase11
        ),
        template2
                to listOf(
            phrase20,
            phrase21
        ),
        template3
                to listOf(
            phrase30,
        ),
        template4
                to listOf(
            phrase40,
        ),
        template5
                to listOf(
            phrase50,
        ),
        template6
                to listOf(
            phrase60,
        ),
    )

    /**
     * Фразы и их лексемы
     */
    val phraseToLexemes = mapOf(
        phrase00 to lexemes00,
        phrase01 to lexemes01,
        phrase10 to lexemes10,
        phrase11 to lexemes11,
        phrase20 to lexemes20,
        phrase21 to lexemes21,
        phrase30 to lexemes30,
        phrase40 to lexemes40,
        phrase50 to lexemes50,
        phrase60 to lexemes60,
    )

    /**
     * Фразы и шаблоны им соответствующие
     */
    val phrasesToTemplate by lazy {
        val map = mutableMapOf<String, Template>()
        templateToPhrases.forEach { (template, phrases) ->
            phrases.forEach { map[it] = template }
        }
        map
    }
}