package com.example.softwareqaandtesting.register

import android.app.Application
import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.softwareqaandtesting.database.RegisterDatabase
import com.example.softwareqaandtesting.database.RegisterDatabaseDao
import com.example.softwareqaandtesting.database.RegisterEntity
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class RegisterViewModel(application: Application): BaseViewModel(application) {

    val liveData = MutableLiveData<State>()

    val PASSWORD_PATTERN: Pattern = Pattern.compile(
        "^(?=.*[0-9])(?=\\S+\$)(?=.*[a-zA-ZığüşöçİĞÜŞÖÇ]).{8,50}\$")

    val NAME_SURNAME_PATTERN: Pattern = Pattern.compile("[A-Za-zığüşöçİĞÜŞÖÇ]{2,64}")

    val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
    )

    fun isValidEmail(mail: CharSequence?): Boolean {
        return !mail.isNullOrEmpty() && EMAIL_ADDRESS_PATTERN.matcher(mail).matches()
    }

    fun isValidPassword(password: CharSequence?): Boolean{
        return !password.isNullOrEmpty() && PASSWORD_PATTERN.matcher(password).matches()
    }

    fun isValidName(name: CharSequence?): Boolean{
        val truncatedName = name?.filter {!it.isWhitespace()}

        return !truncatedName.isNullOrEmpty() && NAME_SURNAME_PATTERN.matcher(truncatedName).matches()
    }

    fun isPasswordsMatching(password1: String?, password2: String?): Boolean{
        return password1.equals(password2)
    }

    fun registerUser(user: RegisterEntity){
        launch {
            val dao = RegisterDatabase(getApplication()).getRegisterDao()
            dao.insert(user)
            liveData.value = State.OnInsertUser(user)
        }
    }

    sealed class State{
        data class OnInsertUser(val user: RegisterEntity) : State()
    }


}