package com.example.android.roomwordssample.Repository

import androidx.lifecycle.LiveData
import com.example.android.roomwordssample.Dao.CarDao
import com.example.android.roomwordssample.Dao.WordDao
import com.example.android.roomwordssample.Entities.Car
import com.example.android.roomwordssample.Entities.Word

class CarRepository(private val carDao: CarDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allCars: LiveData<List<Car>> = carDao.AllCars()

    // The suspend modifier tells the compiler that this must be called from a
    // coroutine or another suspend function.
    // This ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    suspend fun insert(car: Car) {
        carDao.insert(car)
    }
}
