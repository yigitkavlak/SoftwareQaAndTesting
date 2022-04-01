package com.example.softwareqaandtesting

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.softwareqaandtesting.calculator.CalculationType
import com.example.softwareqaandtesting.database.CalculationDatabaseDao
import com.example.softwareqaandtesting.database.CalculationEntity
import com.example.softwareqaandtesting.database.RegisterDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class CalculationDatabaseTest {

    private lateinit var db: RegisterDatabase
    private lateinit var calculationDatabaseDao: CalculationDatabaseDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, RegisterDatabase::class.java)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
        calculationDatabaseDao = db.getCalculationDao()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() = runBlocking {
        val calculation = CalculationEntity(
            userId = 1,
            calculationType = CalculationType.SUM.name,
            firstNumber = 2.0,
            secondNumber = 2.0
        )
        calculationDatabaseDao.insert(calculation)
        val calculations = calculationDatabaseDao.getAllCalculations()
        assert(calculations.contains(calculation))
    }


    @After
    fun closeDb(){
        db.close()
    }

}