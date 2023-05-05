/*
 * Created by Artem Petrosyan on 6/10/2021.
 *
 */

/*
 * Created by Artem Petrosyan on 7/9/2021.
 *
 */

package com.incetro.countypecore.calculator.interactor.calculationinteractor

import com.incetro.countypecore.common.KeywordConstants
import com.incetro.countypecore.core.lexemesparser.LexemesParser
import com.incetro.countypecore.core.phrasestandardizer.PhraseStandardizer
import com.incetro.countypecore.core.phraseunnecessarycleaner.PhraseUnnecessaryCleaner
import com.incetro.countypecore.data.repository.function.FunctionRepository
import com.incetro.countypecore.data.repository.functiondescription.FunctionDescriptionRepository
import com.incetro.countypecore.data.repository.measure.MeasureRepository
import com.incetro.countypecore.model.function.ArgumentType
import com.incetro.countypecore.model.function.ArgumentType.*
import com.incetro.countypecore.model.function.Function
import com.incetro.countypecore.model.function.argumentobject.City
import com.incetro.countypecore.model.function.argumentobject.Datestamp
import com.incetro.countypecore.model.function.argumentobject.Measure
import com.incetro.countypecore.model.function.argumentobject.Timestamp
import com.incetro.countypecore.model.function.functiondescription.FunctionDescription
import com.incetro.countypecore.model.function.functiondescription.Template
import com.incetro.countypecore.model.returnablevalue.FormattedValue

internal class CalculationInteractorImpl(
    private val lexemesParser: LexemesParser,
    private val phraseStandardizer: PhraseStandardizer,
    private val phraseUnnecessaryCleaner: PhraseUnnecessaryCleaner,
    private val functionRepository: FunctionRepository,
    private val functionDescriptionRepository: FunctionDescriptionRepository,
    private val measureRepository: MeasureRepository,
) : CalculationInteractor {

    /**
     * Вычисляет команду, описанную в [phrase].
     */
    override fun calculate(phrase: String): FormattedValue {
        val standardizedPhrase = phrase.standardized()
        val (template: Template, functionId: String) = functionDescriptionRepository
            .findTemplateAndFunctionIdByPhrase(standardizedPhrase)
        val function = functionRepository.findFunctionById(functionId)
        val cleanedPhrase = standardizedPhrase.cleaned(template)
        val lexemes = lexemesParser.getLexemes(cleanedPhrase, template)
        val args = getDefinedArgsTypes(lexemes, function.argumentTypes)
        return function(args)
    }

    override fun setupData(
        functionDescriptions: List<FunctionDescription>,
        measures: List<Measure>,
        cities: List<City>,
        datestamps: List<Datestamp>,
        timestamps: List<Timestamp>,
    ) {

    }

    private fun String.standardized(): String =
            phraseStandardizer.createStandardizedPhrase(this)

    private fun String.cleaned(template: Template): String =
            phraseUnnecessaryCleaner.createCleanedPhrase(this, template)


    /**
     * Определяет типы объектов, которые соответствуют лексемам в списке [lexemes].
     * Возможные типы указываются в [argumentTypes], который содержит каждая [Function].
     * Вернет список готовых к передаче в функцию аргументов.
     */
    private fun getDefinedArgsTypes(
        lexemes: List<String>,
        argumentTypes: List<ArgumentType>,
    ): List<Any> {
        val argsWithTypes = lexemes.map { lexeme ->
            for (type in argumentTypes) {
                when (type) {
                    NUMBER -> {
                        return@map lexeme.toDoubleOrNull() ?: continue
                    }
                    UNIT -> {
                        return@map measureRepository.findMeasureByAlias(lexeme) ?: continue
                    }
                    PERCENTAGE -> {
                        return@map lexemeToPercentage(lexeme) ?: continue
                    }
                }
            }
        }
        return argsWithTypes
    }

    /**
     * Делает из [lexeme] процент, приводя его к [Double] и умножая на 0.01.
     * @return null, если лексема не является процентом
     */
    private fun lexemeToPercentage(lexeme: String): Double? {
        val percentageRegex = Regex(KeywordConstants.PERCENTAGE_REGEX_STRING)
        val digitRegex = Regex(KeywordConstants.NUMBER_REGEX_STRING)
        return if (lexeme.matches(percentageRegex)) {
            digitRegex.find(lexeme)?.value?.toDoubleOrNull()
        } else null
    }
}