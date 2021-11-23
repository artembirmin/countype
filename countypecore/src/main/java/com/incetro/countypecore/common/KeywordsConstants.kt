/*
 * Created by Artem Petrosyan on 6/10/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.common

/**
 * Константы ключевых слов в [com.incetro.countypecore.model.function.Template.expression].
 */
object KeywordsConstants {
    const val UNIT_TEMPLATE = "__unit"

    const val NUMBER_TEMPLATE = "__number"

    const val PERCENTAGE_TEMPLATE = "__percentage"

    const val UNIT_REGEX_STRING = """[\w\s]+"""

    const val NUMBER_REGEX_STRING = """\d+"""

    const val PERCENTAGE_REGEX_STRING = """\d+%"""
}

