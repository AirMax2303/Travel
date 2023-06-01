package com.app.hrcomposeapp.repository

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import com.app.hrcomposeapp.database.Travel
import com.app.hrcomposeapp.database.TravelDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TravelRepository(private val travelDao: TravelDao) {

    val allTravels = MutableLiveData<List<Travel>>()
    val foundTravel = MutableLiveData<Travel>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addTravel(newTravel: Travel) {
        coroutineScope.launch(Dispatchers.IO) {
            travelDao.addTravel(newTravel)
        }
    }

    fun addListTravel(list: List<Travel>) {
        coroutineScope.launch(Dispatchers.IO) {
            travelDao.addListTravel(list)
        }
    }

    fun updateTravelDetails(newTravel: Travel) {
        coroutineScope.launch(Dispatchers.IO) {
            travelDao.updateTravelDetails(newTravel)
        }
    }

    fun getAllTravels() {
        coroutineScope.launch(Dispatchers.IO) {
            allTravels.postValue(travelDao.getAllTravels())
        }
    }

    fun getTravelsByCategory(category: String) {
        coroutineScope.launch(Dispatchers.IO) {
            allTravels.postValue(travelDao.getTravelsByCategory(category))
        }
    }

    fun getSortTravelByIndex(category: String, columnIndex: Int, sort: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            allTravels.postValue(travelDao.getSortTravelByIndex(category, columnIndex, sort))
        }
    }

    fun deleteTravel(Travel: Travel) {
        coroutineScope.launch(Dispatchers.IO) {
            travelDao.deleteTravel(Travel)
        }
    }

    fun deleteAllTravel(list: List<Travel>) {
        coroutineScope.launch(Dispatchers.IO) {
            travelDao.deleteAllTravel(list)
        }
    }

    fun findTravelById(id: String) {
        coroutineScope.launch(Dispatchers.IO) {
            foundTravel.postValue(travelDao.findTravelById(id))
        }
    }
}

