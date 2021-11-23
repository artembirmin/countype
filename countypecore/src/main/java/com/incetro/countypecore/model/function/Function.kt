/*
 * Created by Artem Petrosyan on 7/9/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.model.function

import com.incetro.countypecore.model.returnablevalue.FormattedValue

/**
 * Функция, производящая вычисления по набору соответствующих ей аргументов.
 */
interface Function {

    /**
     * Идентификатор функции.
     */
    val id: String

    /**
     * Метод производящий вычисления используя переданные ему [args].
     * В реализации необходимо указать конкретные типы аргументов\
     * в нужной последовательности.
     */
    operator fun invoke(args: List<Any>): FormattedValue

    /**
     * Типы аргументов, с которыми работает функция.
     */
    val argumentTypes: List<ArgumentType>
}