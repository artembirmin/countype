/*
 * Created by Artem Petrosyan on 11/9/2021.
 * Copyright © 2021 Incetro Inc. All rights reserved.
 */

package com.incetro.countypecore.calculator.interactor

import com.incetro.countypecore.BaseTest
import com.incetro.countypecore.model.function.concrete.percentage.PercentOf
import com.incetro.countypecore.model.function.concrete.unitrelated.UnitToUnit
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

internal class CalculationInteractorImplTest : BaseTest() {

    @Before
    fun setUp() {
        functionRepository.add(UnitToUnit())
        functionRepository.add(PercentOf())
    }

    @Test
    fun calculateUnitToUnit() {
        assertEquals(
            "500см",
            mainInteractor.calculate("5 метров в сантиметрах").toString()
        )
        assertEquals(
            "0.05м",
            mainInteractor.calculate("5 сантиметров в метры").toString()
        )
        assertEquals(
            "5м",
            mainInteractor.calculate("5 метров в метры").toString()
        )
        assertEquals(
            "5см",
            mainInteractor.calculate("5 сантиметров в сантиметры").toString()
        )

        assertEquals(
            "50мм",
            mainInteractor.calculate("5 сантиметров в миллиметры").toString()
        )
        assertEquals(
            "10000000м²", mainInteractor.calculate(
                "10 квадратных километров в " +
                        "квадратные метры"
            ).toString()
        )
        try {
            mainInteractor.calculate(
                "10 километров в " +
                        "квадратные метры"
            )
        } catch (e: IllegalArgumentException) {
            assertTrue(true)
        } catch (e: Exception) {
            assertTrue(false)
        }

        try {
            mainInteractor.calculate(
                "10 квадратных километров в метры"
            )
        } catch (e: IllegalArgumentException) {
            assertTrue(true)
        } catch (e: Exception) {
            assertTrue(false)
        }


        assertEquals(
            "500см",
            mainInteractor.calculate("сколько сантиметров в 5 метрах").toString()
        )
        assertEquals(
            "0.05м",
            mainInteractor.calculate("сколько метров в 5 сантиметрах").toString()
        )
        assertEquals(
            "5м",
            mainInteractor.calculate("сколько метров в 5 метрах").toString()
        )
        assertEquals(
            "5см",
            mainInteractor.calculate("сколько сантиметров в 5 сантиметрах").toString()
        )

            // С мусором
        assertEquals(
            "5м",
            mainInteractor.calculate("я прошел 5 метров в метрах").toString()
        )
    }

    @Test
    fun calculatePercentOf() {
        assertEquals("1", mainInteractor.calculate("10 процентов от 10").toString())
        assertEquals("1", mainInteractor.calculate("10% от 10").toString())
        assertEquals("1", mainInteractor.calculate("10 % от 10").toString())
    }

    @Test
    fun calculateFormatNumber(){
        assertEquals("1", mainInteractor.calculate("1").toString())
        assertEquals("1313", mainInteractor.calculate("1313").toString())
    }

    @Test
    fun calculateFormatNumberAndMeasure(){
        assertEquals("1м", mainInteractor.calculate("1 метр").toString())
        assertEquals("1313м", mainInteractor.calculate("1313 метров").toString())
    }
}