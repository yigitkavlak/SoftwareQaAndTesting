package com.example.softwareqaandtesting

import com.example.softwareqaandtesting.calculator.CalculationType
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CalculationTypeTest {

    @Test
    fun `testing enum type of sum`(){
        //given
        val sumText = "SUM"

        //when
        val actualResult = CalculationType.SUM.name

        //then
        assertEquals("sum enum", sumText, actualResult)
    }

    @Test
    fun `testing enum type of multiple`(){
        //given
        val multipleText = "MULTIPLE"

        //when
        val actualResult = CalculationType.MULTIPLE.name

        //then
        assertEquals("multiple enum", multipleText, actualResult)
    }

    @Test
    fun `testing enum type of minus`(){
        //given
        val minusText = "MINUS"

        //when
        val actualResult = CalculationType.MINUS.name

        //then
        assertEquals("sum enum", minusText, actualResult)
    }

    @Test
    fun `testing enum type of divide`(){
        //given
        val divideText = "DIVIDE"

        //when
        val actualResult = CalculationType.DIVIDE.name

        //then
        assertEquals("divideText enum", divideText, actualResult)
    }
}