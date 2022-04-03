package com.example.softwareqaandtesting

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.softwareqaandtesting.database.RegisterDatabase
import com.example.softwareqaandtesting.database.RegisterDatabaseDao
import com.example.softwareqaandtesting.database.RegisterEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class RegisterDatabaseTest {
    private lateinit var db: RegisterDatabase
    private lateinit var registerDatabaseDao: RegisterDatabaseDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, RegisterDatabase::class.java)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
        registerDatabaseDao = db.getRegisterDao()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        runBlocking {
            val user = RegisterEntity(
                firstName = "yigit",
                lastName = "kavlak",
                password = "asdasd11",
                email = "yigit@xyz.com"
            )
            registerDatabaseDao.insert(user)
            val byEmailAndPassword = registerDatabaseDao.getUserWithEmailAndPassword("yigit@xyz.com", "asdasd11")
            val users = registerDatabaseDao.getAllUsers()
            assert(users.contains(byEmailAndPassword))
        }
    }

    @After
    fun closeDb(){
        db.close()
    }
}