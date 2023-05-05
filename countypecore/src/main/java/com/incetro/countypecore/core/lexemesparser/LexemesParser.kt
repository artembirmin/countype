/*
 * Created by Artem Petrosyan on 23/9/2021.
 *
 */

package com.incetro.countypecore.core.lexemesparser

import com.incetro.countypecore.model.function.functiondescription.Template

/**
 * Парсер лексем.
 */
interface LexemesParser {
    /**
     * Достает лексемы из [phrase] по [template]. Возвращает список лексем в виде [String].
     */
    fun getLexemes(phrase: String, template: Template): List<String>
}