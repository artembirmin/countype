/*
 * Created by Artem Petrosyan on 23/9/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.core

import com.incetro.countypecore.model.function.functiondescription.Template

/**
 * Парсер лексем.
 */
interface LexemesParser {
    /**
     * Достает лексемы из [phrase] по [template]. Возвращает список лексем в виде [String].
     */
    fun getLexems(phrase: String, template: Template): List<String>
}