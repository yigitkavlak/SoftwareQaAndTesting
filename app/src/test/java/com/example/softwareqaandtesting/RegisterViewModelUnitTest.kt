package com.example.softwareqaandtesting

import android.app.Application
import com.example.softwareqaandtesting.register.RegisterViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RegisterViewModelUnitTest {
    private lateinit var viewModel: RegisterViewModel


    @Before
    fun setup(){
        viewModel = RegisterViewModel(Application())
    }

    @Test
    fun `validating valid email address`(){
        //given
        val email = "yigit@kavlak.com"

        //when
        val actualResult = viewModel.isValidEmail(email)

        //then
        assertEquals("valid mail", true, actualResult)
    }

    @Test
    fun `validating invalid mail adress`(){
        //given
        val email = "yigit#kavlak.com"

        //when
        val actualResult = viewModel.isValidEmail(email)

        //then
        assertEquals("invalid mail", false, actualResult)
    }

    @Test
    fun `validating valid name`(){
        //given
        val name = "yigit"

        //when
        val actualResult = viewModel.isValidName(name)

        //then
        assertEquals("valid name", true, actualResult)
    }

    @Test
    fun `validating invalid name with one letter`(){
        //given
        val name = "y"

        //when
        val actualResult = viewModel.isValidName(name)

        //then
        assertEquals("invalid name", false, actualResult)
    }

    @Test
    fun `validating invalid first name with number`(){
        //given
        val name = "yigit23"

        //when
        val actualResult = viewModel.isValidName(name)

        //then
        assertEquals("invalid name", false, actualResult)
    }

    @Test
    fun `validating valid password`(){
        //given
        val password = "kavlak123"

        //when
        val actualResult = viewModel.isValidPassword(password)

        //then
        assertEquals("valid password", true, actualResult)
    }

    @Test
    fun `validating invalid password without number`(){
        //given
        val password ="yigitkavlak"

        //when
        val actualResult = viewModel.isValidPassword(password)

        //then
        assertEquals("invalid password", false, actualResult)
    }

    @Test
    fun `validating invalid password with lest letter`(){
        //given
        val password ="yigit12"

        //when
        val actualResult = viewModel.isValidPassword(password)

        //then
        assertEquals("invalid password", false, actualResult)
    }

    @Test
    fun `validating matches password`(){
        //given
        val password1 = "emre123"
        val password2 = "emre123"

        //when
        val actualResult = viewModel.isPasswordsMatching(password1,password2)

        //then
        assertEquals("passwords are matching with each other", true, actualResult)
    }

    @Test
    fun `validating not matches password`(){
        //given
        val password1 = "emre123"
        val password2 = "tutku123"

        //when
        val actualResult = viewModel.isPasswordsMatching(password1,password2)

        //then
        assertEquals("passwords are not matching with each other", false, actualResult)
    }
}