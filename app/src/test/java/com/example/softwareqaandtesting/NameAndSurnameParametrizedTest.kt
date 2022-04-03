package com.example.softwareqaandtesting

import android.app.Application
import com.example.softwareqaandtesting.register.RegisterViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class NameAndSurnameParametrizedTest(
    private val name: String,
    private val expected: Boolean
) {
    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setUp(){
        viewModel = RegisterViewModel(Application())
    }

    @Test
    fun isValidNameOrSurname(){
        val actual = viewModel.isValidName(name)
        assertEquals(expected, actual)
    }

    companion object{
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: isValid({0})={1}")
        fun passwordData(): Iterable<Array<Any>> {
            return listOf(
                arrayOf("kavlak123", false),
                arrayOf("emrecicek.", false),
                arrayOf("tutkukirac!", false),
                arrayOf("YiğitKavlak", true),
                arrayOf("emrecicek", true),
                arrayOf("TutkuKıraç", true),
            )
        }
    }
}