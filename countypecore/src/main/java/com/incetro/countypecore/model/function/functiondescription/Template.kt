/*
 * Created by Artem Petrosyan on 25/9/2021.
 *
 */

package com.incetro.countypecore.model.function.functiondescription

/**
 * Шаблон фразы.
 */
data class Template(
    /**
     * Шаблон. В нем описывается общий вид фразы с указанием типов по следующим правилам:
     * [ArgumentType.UNIT] : __unit
     * [ArgumentType.NUMBER] : __number
     * [ArgumentType.PERCENTAGE] : __percentage
     *
     * Пример. __number __unit в _unit.
     */
    val expression: String,
    /**
     * Порядок аргументов.
     *
     * Цифра указывает позицию аргумента
     * в [com.incetro.countypecore.model.function.Function.invoke].
     * Индекс цифры - позиция аргумента в [expression].
     *
     * Пример. Функция принимает аргументы (__number, __unit, __unit).
     * Шаблон имеет вид сколько __unit в __number __unit.
     * Тогда порядок аргументов (3,1,2)
     */
    val argumentsOrder: List<Int>
)