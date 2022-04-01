package com.example.softwareqaandtesting.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RegisterEntity::class, CalculationEntity::class],  version = 3, exportSchema = false)
abstract class RegisterDatabase : RoomDatabase() {

    abstract fun getRegisterDao(): RegisterDatabaseDao
    abstract fun getCalculationDao(): CalculationDatabaseDao

    //Singleton

    companion object {
        @Volatile
        private var instance: RegisterDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, RegisterDatabase::class.java, "register_database"
        ).allowMainThreadQueries().fallbackToDestructiveMigration()
            .build()
    }
}
