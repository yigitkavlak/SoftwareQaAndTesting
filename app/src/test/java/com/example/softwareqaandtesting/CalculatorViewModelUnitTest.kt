package com.example.softwareqaandtesting

import android.app.Application
import com.example.softwareqaandtesting.calculator.CalculatorViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CalculatorViewModelUnitTest {
    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setup(){
        viewModel = CalculatorViewModel(Application())
    }

    @Test
    fun `correct sum of two numbers`(){
        //given
        val number1 = 2.0
        val number2 = 5.0

        //when
        val actualResult = viewModel.sumOfNumbers(number1, number2)

        //then
        assertEquals("correct sum", 7.0, actualResult)
    }

    @Test
    fun `correct minus of two numbers`(){
        //given
        val number1 = 2.0
        val number2 = 5.0

        //when
        val actualResult = viewModel.minusOfNumbers(number1, number2)

        //then
        assertEquals("correct sum", -3.0, actualResult)
    }

    @Test
    fun `correct multiple of two numbers`(){
        //given
        val number1 = 2.0
        val number2 = 5.0

        //when
        val actualResult = viewModel.multipleOfNumbers(number1, number2)

        //then
        assertEquals("correct multiple", 10.0, actualResult)
    }

    @Test
    fun `correct divide of two numbers`(){
        //given
        val number1 = 2.0
        val number2 = 5.0

        //when
        val actualResult = viewModel.divideOfNumbers(number1, number2)

        //then
        assertEquals("correct multiple", 0.4, actualResult)
    }

    @Test
    fun `formatting result`(){
        //given
        val result: Double = 7.0/3.0 //2.333333.......5

        //when
        val actualResult = viewModel.formatResultNumber(result)

        //then
        assertEquals("formatted result", 2.33,actualResult)
    }




}