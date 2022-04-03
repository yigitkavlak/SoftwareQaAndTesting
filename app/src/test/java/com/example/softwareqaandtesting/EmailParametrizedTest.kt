package com.example.softwareqaandtesting

import android.app.Application
import com.example.softwareqaandtesting.register.RegisterViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
class EmailParametrizedTest(
    private val email: String,
    private val expected: Boolean)
{

    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setUp(){
        viewModel = RegisterViewModel(Application())
    }

    @Test
    fun testIsValidEmailId() {
        val actual = viewModel.isValidEmail(email)
        assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: isValid({0})={1}")
        fun emailData(): Iterable<Array<Any>> {
            return listOf(
                arrayOf("mary@testdomain.com", true),
                arrayOf("mary.smith@testdomain.com", true),
                arrayOf("mary_smith123@testdomain.com", true),
                arrayOf("mary@testdomaindotcom", false),
                arrayOf("mary-smith@testdomain", false),
                arrayOf("testing.com", false)
            )
        }
    }
}