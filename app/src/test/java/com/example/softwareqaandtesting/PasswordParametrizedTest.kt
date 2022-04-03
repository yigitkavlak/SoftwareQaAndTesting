package com.example.softwareqaandtesting

import android.app.Application
import com.example.softwareqaandtesting.register.RegisterViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class PasswordParametrizedTest(
    private val password: String,
    private val expected: Boolean
) {
    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setUp(){
        viewModel = RegisterViewModel(Application())
    }

    @Test
    fun passwordIsValidTest(){
        val actualResult = viewModel.isValidPassword(password)
        assertEquals(expected, actualResult)
    }

    companion object{
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: isValid({0})={1}")
        fun passwordData(): Iterable<Array<Any>> {
            return listOf(
                arrayOf("kavlak123", true),
                arrayOf("emrecicek97", true),
                arrayOf("tutkukirac1997", true),
                arrayOf("yigitkavlak", false),
                arrayOf("emrecicek", false),
                arrayOf("tutkukirac", false)
            )
        }
    }
}