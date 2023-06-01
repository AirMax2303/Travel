package com.app.hrcomposeapp.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.hrcomposeapp.database.Travel
import com.app.hrcomposeapp.database.TravelJSON
import com.app.hrcomposeapp.repository.TravelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TravelViewModel @Inject constructor(private val travelRepository: TravelRepository) :
    ViewModel() {

    fun getAllTravels() {
        travelRepository.getAllTravels()
    }

    fun getTravelsByCategory(cat: String) {
        Log.d("mylog", "columnindex.toString() ${category.value} ${columnIndex.value}")
        this._category.value = cat
//        category.value?.let { travelRepository.getTravelsByCategory(it) }
        category.value?.let { columnIndex.value?.let { it1 ->
            sort.value?.let { it2 ->
                travelRepository.getSortTravelByIndex(it,
                    it1, it2
                )
            }
        } }
    }

    fun getTravelsByCategory2() {
        this.category.value?.let { getTravelsByCategory(it) }
    }

    fun addListTravel(list: List<Travel>) {
        viewModelScope.launch {
            travelRepository.addListTravel(list)
        }
/*
                list.forEachIndexed { index, travel ->
                    run {
                        travelRepository.addTravel(
                            Travel(
                                id = travel.id,
                                destination = travel.destination,
                                name = travel.name,
                                country = travel.country,
                                category = travel.category,
                                duration = travel.duration,
                                price = travel.price
                            )
                        )
                    }
                }

 */
       getAllTravels()
    }

    fun addTravel(Travel: Travel) {
        travelRepository.addTravel(Travel)
        getAllTravels()
    }

    fun updateTravelDetails(Travel: Travel) {
        travelRepository.updateTravelDetails(Travel)
        getAllTravels()
    }

    fun findTravelById(empId: String) {
        travelRepository.findTravelById(empId)
    }

    fun deleteTravel(Travel: Travel) {
        travelRepository.deleteTravel(Travel)
        getAllTravels()
    }

    fun deleteAllTravel() {
        viewModelScope.launch {
            travelList.value?.let { travelRepository.deleteAllTravel(it) }
            getAllTravels()
        }
    }

    val travelList: LiveData<List<Travel>> = travelRepository.allTravels
    val foundTravel: LiveData<Travel> = travelRepository.foundTravel
//    var category by mutableStateOf("")

    var _category = MutableLiveData<String>()
    val category: LiveData<String> = _category

//    var columnIndex by mutableStateOf(0)
    var _columnIndex = MutableLiveData<Int>()
    val columnIndex: LiveData<Int> = _columnIndex

    fun setColumnSort(value: Int)
    {
        _columnIndex.value = value
    }

    var _sort = MutableLiveData<Int>()
    val sort: LiveData<Int> = _sort

    fun changeSort()
    {
        if (_sort.value == 0) {
            _sort.value = 1
        } else {
            _sort.value = 0
        }
    }

    val addStatus = MutableLiveData<Boolean>()



    val listResponse: LiveData<List<TravelJSON>>
        get() {
            return JSONLiveData
        }
    var errorMessage: String by mutableStateOf("")

    private var JSONLiveData = MutableLiveData<List<TravelJSON>>()
    fun observeMovieLiveData(): LiveData<List<TravelJSON>> {
        return JSONLiveData
    }

    val loading = MutableLiveData<Boolean>()

    fun getTravelJSONlList() {
/*
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            loading.value = false
            try {
                val travelList = apiService.getTravels()
                JSONLiveData.value = travelList
                addJSONTravel()
                getAllTravels()
                loading.value = true
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }

 */
    }
/*
    fun addJSONTravel() {
        JSONLiveData.value!!.forEachIndexed { index, travelJSON ->
            run {
                travelRepository.addTravel(
                    Travel(
                        id = travelJSON.id,
                        destination = travelJSON.destination,
                        name = travelJSON.name,
                        country = travelJSON.country,
                        category = travelJSON.category,
                        duration = travelJSON.duration,
                        price = travelJSON.price
                    )
                )
            }
        }
    }
 */

}
