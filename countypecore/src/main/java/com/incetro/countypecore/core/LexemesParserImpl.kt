/*
 * Created by Artem Petrosyan on 20/9/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.core

import com.incetro.countypecore.model.function.functiondescription.Template

internal class LexemesParserImpl : LexemesParser {

    /**
     * Регулярное выражение описывающее паттерн ключевого слова,
     * используемого в [Template.expression]
     */
    private val keywordTemplateRegexString = "__[a-z]+"

    override fun getLexems(phrase: String, template: Template): List<String> {
        val separators = getSeparators(template.expression)
        val lexemes = getLexemsBySeparators(phrase, separators)
        return getSortedTokens(lexemes, template.argumentsOrder)
    }

    /**
     * Достает сепараторы между ключевыми словами из [template].
     * Ключевые слова имеют вид "__\[a-z]+".
     */
    fun getSeparators(template: String): List<String> {
        val keywordTemplateRegex = keywordTemplateRegexString.toRegex()
        return template.split(keywordTemplateRegex)
                // Отфильтровали пустые строки, которые есть в начале в конце фраз
                .filter { it.isNotEmpty() }
    }

    /**
     * Достает лексемы из [phrase], используя [separatorsFromTemplate],
     * полученные из шаблона, которому соответствует фраза.
     */
    fun getLexemsBySeparators(
        phrase: String,
        separatorsFromTemplate: List<String>
    ): MutableList<String> {
        val lexemes = mutableListOf(phrase)
        separatorsFromTemplate.forEach { separatorFromTemplate ->
            lexemes.addAll(lexemes.removeLast().split(separatorFromTemplate, limit = 2))
            // Можно намутить рекурсию, которая будет принимать сначала всю строку, а потом будет
            // сплитить по разделителю и передавать самой себе последний элемент массива
        }
        return lexemes
                .filter { it.isNotEmpty() }
                .toMutableList()
    }

    /**
     * Возвращает отсортированные лексемы, чтобы они соответствовали порядку аргументов,
     * в [com.incetro.countypecore.model.function.Function].
     * В [Template.argumentsOrder] указывается порядок аргументов,
     * который образуется конкретным шаблоном.
     */
    private fun getSortedTokens(
        lexemes: MutableList<String>,
        argumentsOrder: List<Int>
    ): List<String> {
        return lexemes.sortedWithLeadingList(argumentsOrder.toMutableList())
    }

    /**
     * Сортирует список на основе [leadingList].
     * Все перестановки при сортировке [leadingList] повторяются для вызывающего метод списка
     * с теми же позициями элементов.
     */
    private fun MutableList<String>.sortedWithLeadingList(
        leadingList: MutableList<Int>
    ): MutableList<String> {
        return quickDoubleSort(leadingList, this)
    }

    /**
     * Сортирует два массива одновременно. Ведущим массивом является [leadingList],
     * ведомым является [slaveList].
     * @return [slaveList].
     */
    private fun quickDoubleSort(
        leadingList: MutableList<Int>,
        slaveList: MutableList<String>,
        lowerLimitOfList: Int = 0,
        upperLimitOfList: Int = leadingList.size - 1
    ): MutableList<String> {

        if (leadingList.isSorted() && leadingList.isNotEmpty() ||
            lowerLimitOfList >= upperLimitOfList
        ) return slaveList

        val middleItemIndex = lowerLimitOfList + (upperLimitOfList - lowerLimitOfList) / 2
        val middleItem = leadingList[middleItemIndex]

        var lowerLimit = lowerLimitOfList
        var upperLimit = upperLimitOfList
        while (lowerLimit <= upperLimit) {
            while (leadingList[lowerLimit] < middleItem) {
                lowerLimit++
            }
            while (leadingList[upperLimit] > middleItem) {
                upperLimit--
            }
            if (lowerLimit <= upperLimit) {
                leadingList[lowerLimit] = leadingList[upperLimit]
                        .also { leadingList[upperLimit] = leadingList[lowerLimit] }
                slaveList[lowerLimit] = slaveList[upperLimit]
                        .also { slaveList[upperLimit] = slaveList[lowerLimit] }
                lowerLimit++
                upperLimit--
            }
        }
        if (lowerLimitOfList < upperLimit)
            quickDoubleSort(
                leadingList,
                slaveList,
                lowerLimitOfList,
                upperLimit
            )
        if (upperLimitOfList > lowerLimit)
            quickDoubleSort(
                leadingList,
                slaveList,
                lowerLimit,
                upperLimitOfList
            )
        return slaveList
    }

    /**
     * @return true, если список отсортирован по возрастанию.
     */
    private fun List<Int>.isSorted(): Boolean {
        var isSorted = true
        for (i in 0 until this.size - 1) {
            if (this[i] > this[i + 1]) {
                isSorted = false
            }
        }
        return isSorted
    }
}


