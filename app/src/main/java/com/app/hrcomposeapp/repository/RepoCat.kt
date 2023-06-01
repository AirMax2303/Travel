package com.app.hrcomposeapp.repository

import androidx.lifecycle.MutableLiveData
import com.app.hrcomposeapp.database.Category
import com.app.hrcomposeapp.database.TravelDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryRepository(private val TravelDao: TravelDao) {

    val categoryList = MutableLiveData<List<Category>>()

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun getCategoryes() {
        coroutineScope.launch(Dispatchers.IO) {
            val list = TravelDao.getCategoryes()
            categoryList.postValue(list)
        }
    }
}