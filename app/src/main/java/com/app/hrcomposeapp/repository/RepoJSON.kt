package com.app.hrcomposeapp.repository

import ApiService
import ApiService.Companion.apiService
import android.util.Log
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

    suspend fun addTravel(travel: Travel) {
        try {
            val retrofit = ApiServiceAdd.getInstance()
            val m = mapOf<String,String>(
                "destination" to travel.destination,
                "name" to travel.name,
                "country" to travel.country,
                "category" to travel.category,
                "duration" to travel.duration,
                "price" to travel.price
            )
            val response = retrofit.addTravel(m)
            Log.d("addTravel", response.toString())
        } catch (e: Exception) {
            errorMessage.value = e.message.toString()
        }
    }

    suspend fun updateTravel(travel: Travel) {

    }

    suspend fun deleteTravel(travel: Travel) {
        try {
            val retrofit = ApiServiceDelete.getInstance()
            val m = mapOf<String,String>(
                "id" to travel.id,
            )
            val response = retrofit.deleteTravel(m)
        } catch (e: Exception) {
            errorMessage.value = e.message.toString()
        }
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