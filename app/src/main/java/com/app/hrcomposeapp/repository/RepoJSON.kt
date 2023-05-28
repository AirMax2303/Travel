package com.app.hrcomposeapp.repository

import ApiService
import ApiService.Companion.apiService
import ApiServiceJSON
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.hrcomposeapp.database.Travel
import com.app.hrcomposeapp.database.TravelJSON
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepoJSON(
//    private val apiService: ApiServiceJSON
){
    val travelList = MutableLiveData<List<Travel>>(listOf())
    val foundTravel = MutableLiveData<Travel>()
    val errorMessage = MutableLiveData<String>()

    fun addTravel(travel: Travel) {
    }

    fun updateTravel(travel: Travel) {
    }

    fun deleteTravel(travel: Travel) {
    }

    suspend fun getAllTravels() {

            try {
                val apiservice = ApiService.getInstance()
                val responseList = apiservice.getTravels()
//                travelList.value = responseList
                travelList.postValue(responseList)
            } catch (e: Exception) {
                errorMessage.value = e.message.toString()
            }

    }
};