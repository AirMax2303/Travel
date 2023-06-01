package com.app.hrcomposeapp.repository

import ApiService
import ApiServiceUpdate
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.hrcomposeapp.database.Travel

class RepoJSON(
//    private val apiService: ApiServiceJSON
) {
    val travelList = MutableLiveData<List<Travel>>(listOf())
    val foundTravel = MutableLiveData<Travel>()
    val errorMessage = MutableLiveData<String>()

    suspend fun addTravel(travel: Travel) {
        try {
            val retrofit = ApiServiceAdd.getInstance()
            val m = mapOf<String, String>(
                "id" to travel.id,
                "destination" to travel.destination,
                "name" to travel.name,
                "country" to travel.country,
                "category" to travel.category,
                "duration" to travel.duration,
                "price" to travel.price.toString()
            )
            val response = retrofit.addTravel(m)
            Log.d("addTravel", response.toString())
        } catch (e: Exception) {
            errorMessage.value = e.message.toString()
        }
    }

    suspend fun updateTravel(travel: Travel) {
        try {
            val retrofit = ApiServiceUpdate.getInstance()
            val m = mapOf<String, String>(
                "id" to travel.id,
                "destination" to travel.destination,
                "name" to travel.name,
                "country" to travel.country,
                "category" to travel.category,
                "duration" to travel.duration,
                "price" to travel.price.toString()
            )
            val response = retrofit.addTravel(m)
            Log.d("updateTravel", response.toString())
        } catch (e: Exception) {
            errorMessage.value = e.message.toString()
        }
    }

    suspend fun deleteTravel(travel: Travel) {
        try {
            val retrofit = ApiServiceDelete.getInstance()
            val m = mapOf<String, String>(
                "id" to travel.id.toString(),
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

    suspend fun getAllTravels2(): List<Travel> {
        val apiservice = ApiService.getInstance()
        return ApiService.getInstance().getTravels()
    }

}

/*
is IOException -> "No internet connection"
is HttpException -> "Something went wrong!"
else -> t.localizedMessage
 */