package com.example.softwareqaandtesting.login

import android.app.Application
import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.softwareqaandtesting.database.RegisterDatabase
import com.example.softwareqaandtesting.database.RegisterEntity
import com.example.softwareqaandtesting.register.BaseViewModel
import com.example.softwareqaandtesting.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import java.util.regex.Pattern


class LoginViewModel(application: Application): BaseViewModel(application) {
    val userLiveData = SingleLiveEvent<RegisterEntity>()
    val PASSWORD_PATTERN: Pattern = Pattern.compile(
        "^(?=.*[0-9])(?=\\S+\$)(?=.*[a-zA-ZığüşöçİĞÜŞÖÇ]).{8,50}\$")

    val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
    )

    fun isValidEmail(mail: CharSequence?): Boolean {
        return !mail.isNullOrEmpty() && EMAIL_ADDRESS_PATTERN.matcher(mail).matches()
    }

    fun isValidPassword(password: CharSequence?): Boolean{
        return !password.isNullOrEmpty() && PASSWORD_PATTERN.matcher(password).matches()
    }

    fun loginButtonClicked(email: String, password: String) {
        launch {
            val dao = RegisterDatabase(getApplication()).getRegisterDao()
            userLiveData.value = dao.getUserWithEmailAndPassword(email, password)
        }
    }
}