package com.example.softwareqaandtesting.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Users_calculations_table")
@Parcelize
data class CalculationEntity(

    @PrimaryKey(autoGenerate = true)
    var calculationId: Int = 0,

    @ColumnInfo(name = "user_id")
    var userId: Int,

    @ColumnInfo(name = "calculating_time")
    var calculationType: String,

    @ColumnInfo(name = "first_number")
    var firstNumber: Double,

    @ColumnInfo(name = "second_number")
    var secondNumber: Double

) : Parcelable
