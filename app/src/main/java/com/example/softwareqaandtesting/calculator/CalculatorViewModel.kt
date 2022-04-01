package com.example.softwareqaandtesting.calculator

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.softwareqaandtesting.database.CalculationEntity
import com.example.softwareqaandtesting.database.RegisterDatabase
import com.example.softwareqaandtesting.register.BaseViewModel
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class CalculatorViewModel(application: Application): BaseViewModel(application) {

    private val _resultLiveData = MutableLiveData<Double>()
    val resultLiveData: LiveData<Double>
        get() = _resultLiveData

    val calculationListLiveData = MutableLiveData<ArrayList<CalculationEntity>>()



    fun sumOfNumbers(number1: Double?, number2: Double?): Double? {
        return if(number1 != null && number2 != null)
            number1 + number2
        else null
    }

    fun minusOfNumbers(number1: Double?, number2: Double?): Double?{
        return if (number1 != null && number2 != null) {
            number1 - number2
        } else {
            null
        }
    }

    fun multipleOfNumbers(number1: Double?, number2: Double?): Double?{
        return if(number1 != null && number2 != null)
            number1 * number2
        else null
    }

    fun divideOfNumbers(number1: Double?, number2: Double?): Double?{
        return if(number1 != null && number2 != null && number2 != 0.0)
            number1 / number2
        else null
    }

    fun updateResultLiveData(result: Double){
        _resultLiveData.value = result
    }

    fun addCalculation(calculation: CalculationEntity){
        launch {
            val dao = RegisterDatabase(getApplication()).getCalculationDao()
            dao.insert(calculation)
        }
    }

    fun formatResultNumber(number: Double) : Double{
        val decFormat = DecimalFormat("#.##")
        return decFormat.format(number).toDouble()
    }

    fun getAllCalculations(userId: Int){
        launch {
            val dao = RegisterDatabase(getApplication()).getCalculationDao()
            val list = dao.getAllCalculationsOfUser(userId)
            val arrayList = ArrayList<CalculationEntity>()
            arrayList.addAll(list)
            calculationListLiveData.value = arrayList
        }
    }
}