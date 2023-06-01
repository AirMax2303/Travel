package com.app.hrcomposeapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.hrcomposeapp.database.Travel
import com.app.hrcomposeapp.repository.RepoJSON
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed interface JSONUiState {
    data class Success(val list: List<Travel>) : JSONUiState
    object Error : JSONUiState
    object Loading : JSONUiState
}

class JSONViewModel(private val repoJSON: RepoJSON) : ViewModel() {

    var jsonUiState: JSONUiState by mutableStateOf(JSONUiState.Loading)
        private set

    init {
        getAllTarvel2()
    }

    var travelList: LiveData<List<Travel>> = repoJSON.travelList
    val foundTravel: LiveData<Travel> = repoJSON.foundTravel
    var errorMessage: LiveData<String> = repoJSON.errorMessage

    suspend fun addTravel(travel: Travel) {
        repoJSON.addTravel(travel)
    }

    suspend fun updateTravel(travel: Travel) {
        repoJSON.updateTravel(travel)
    }

    suspend fun deleteTravel(travel: Travel) {
        repoJSON.deleteTravel(travel)
    }

    fun getAllTravels() {
        viewModelScope.launch {
            repoJSON.getAllTravels()
        }
    }

    fun getAllTarvel2() {
        viewModelScope.launch {
            jsonUiState = JSONUiState.Loading
            jsonUiState = try {
                JSONUiState.Success(repoJSON.getAllTravels2())
            } catch (e: IOException) {
                JSONUiState.Error
            } catch (e: HttpException) {
                JSONUiState.Error
            }
        }
    }

    /*

        fun getAllTravels() {
            viewModelScope.launch {
                repoJSON.getAllTravels()
            }

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

