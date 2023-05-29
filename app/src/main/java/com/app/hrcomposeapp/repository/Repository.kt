package com.app.hrcomposeapp.repository

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import com.app.hrcomposeapp.database.Travel
import com.app.hrcomposeapp.database.TravelDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TravelRepository(private val TravelDao: TravelDao) {

    val allTravels = MutableLiveData<List<Travel>>()
    val foundTravel = MutableLiveData<Travel>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addTravel(newTravel: Travel) {
        coroutineScope.launch(Dispatchers.IO) {
            TravelDao.addTravel(newTravel)
        }
    }

    fun addListTravel(list: List<Travel>) {
        coroutineScope.launch(Dispatchers.IO) {
            TravelDao.addListTravel(list)
        }
    }

    fun updateTravelDetails(newTravel: Travel) {
        coroutineScope.launch(Dispatchers.IO) {
            TravelDao.updateTravelDetails(newTravel)
        }
    }

    fun getAllTravels() {
        coroutineScope.launch(Dispatchers.IO) {
            allTravels.postValue(TravelDao.getAllTravels())
        }
    }

    fun getTravelsByCategory(category: String) {
        coroutineScope.launch(Dispatchers.IO) {
            allTravels.postValue(TravelDao.getTravelsByCategory(category))
        }
    }

    fun deleteTravel(Travel: Travel) {
        coroutineScope.launch(Dispatchers.IO) {
            TravelDao.deleteTravel(Travel)
        }
    }

    fun deleteAllTravel(list: List<Travel>) {
        coroutineScope.launch(Dispatchers.IO) {
            TravelDao.deleteAllTravel(list)
        }
    }

    fun findTravelById(id: String) {
        coroutineScope.launch(Dispatchers.IO) {
            foundTravel.postValue(TravelDao.findTravelById(id))
        }
    }
}

