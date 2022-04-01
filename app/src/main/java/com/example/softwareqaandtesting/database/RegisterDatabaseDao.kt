package com.example.softwareqaandtesting.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RegisterDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: RegisterEntity)

    @Query("SELECT * FROM Register_users_table ORDER BY userId DESC")
    suspend fun getAllUsers(): List<RegisterEntity>

    @Query("SELECT * FROM register_users_table WHERE email_text = :email AND password_text = :password limit 1")
    suspend fun getUserWithEmailAndPassword(email: String, password: String): RegisterEntity
}
