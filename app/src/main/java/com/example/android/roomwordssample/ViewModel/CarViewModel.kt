package com.example.android.roomwordssample.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.android.roomwordssample.Entities.Car
import com.example.android.roomwordssample.Entities.Word
import com.example.android.roomwordssample.Repository.CarRepository
import com.example.android.roomwordssample.Repository.WordRepository
import com.example.android.roomwordssample.Room.WordRoomDatabase
import kotlinx.coroutines.launch

class CarViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CarRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allCars: LiveData<List<Car>>

    init {
        val carsDao = WordRoomDatabase.getDatabase(application, viewModelScope).carDao()
        repository = CarRepository(carsDao)
        allCars = repository.allCars
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(car: Car) = viewModelScope.launch {
        repository.insert(car)
    }
}