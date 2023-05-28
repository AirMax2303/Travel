package com.app.hrcomposeapp.viewmodels

import ApiService
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.hrcomposeapp.database.Travel
import com.app.hrcomposeapp.repository.RepoJSON
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class JSONViewModel(private val repoJSON: RepoJSON): ViewModel() {
    var travelList: LiveData<List<Travel>> = repoJSON.travelList
    val foundTravel: LiveData<Travel> = repoJSON.foundTravel
    var errorMessage: LiveData<String> = repoJSON.errorMessage

    suspend fun addTravel(travel: Travel) {
        repoJSON.addTravel(travel)
    }
    fun updateTravel(travel: Travel) {
        repoJSON.updateTravel(travel)
    }
    fun deleteTravel(travel: Travel) {
        repoJSON.deleteTravel(travel)
    }
    fun getAllTravels() {
        viewModelScope.launch {
            repoJSON.getAllTravels()
        }

/*
        viewModelScope.launch {
            try {
                val responseList = apiService.getTravels()
                repoJSON.travels.value = responseList
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }

 */
    }
}

