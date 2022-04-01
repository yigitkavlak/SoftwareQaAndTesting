package com.example.softwareqaandtesting.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CalculationDatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(calculation: CalculationEntity)

    @Query("SELECT * FROM Users_calculations_table WHERE user_id = :userId")
    suspend fun getAllCalculationsOfUser(userId: Int): List<CalculationEntity>

    @Query("SELECT * FROM Users_calculations_table ORDER BY calculationId DESC")
    suspend fun getAllCalculations(): List<CalculationEntity>


}