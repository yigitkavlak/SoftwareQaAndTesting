package com.example.softwareqaandtesting

import android.app.Application
import com.example.softwareqaandtesting.login.LoginViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LoginUnitTest {

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp(){
        viewModel = LoginViewModel(Application())
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

}