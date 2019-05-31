package com.example.android.roomwordssample.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android.roomwordssample.Entities.Car
import com.example.android.roomwordssample.Entities.Word

@Dao
interface CarDao {

    // LiveData is a data holder class that can be observed within a given lifecycle.
    // Always holds/caches latest version of data. Notifies its active observers when the
    // data has changed. Since we are getting all the contents of the database,
    // we are notified whenever any of the database contents have changed.
    @Query("SELECT * from car_table ORDER BY brand ASC")
    fun AllCars(): LiveData<List<Car>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(car: Car)

    @Query("DELETE FROM car_table")
    suspend fun deleteAll()
}