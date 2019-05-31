package com.example.android.roomwordssample.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car_table")
data class Car(@ColumnInfo(name = "brand")
               val brand : String,
               @ColumnInfo(name = "model")
               val model : String,
               @ColumnInfo(name = "year")
               val year : Int,
               @ColumnInfo(name = "info")
               val Info: String,
               @ColumnInfo(name = "price")
               val price: Int,
               @ColumnInfo(name = "specs")
               val specs: String)
{@PrimaryKey(autoGenerate = true) var id: Long = 0}