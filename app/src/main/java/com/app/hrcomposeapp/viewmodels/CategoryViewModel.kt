package com.app.hrcomposeapp.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.hrcomposeapp.database.Category
import com.app.hrcomposeapp.database.Travel
import com.app.hrcomposeapp.database.TravelJSON
import com.app.hrcomposeapp.repository.CategoryRepository
import com.app.hrcomposeapp.repository.TravelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface CategoryUiState {
    object Success : CategoryUiState
    object Error : CategoryUiState
    object Loading : CategoryUiState
}

@HiltViewModel
class CategoryViewModel @Inject constructor(private val categoryRepository: CategoryRepository) :
    ViewModel() {

    var categoryUiState: CategoryUiState by mutableStateOf(CategoryUiState.Loading)
        private set
    
    val categoryList: LiveData<List<Category>> = categoryRepository.categoryList

    fun getCategoryes() {
        viewModelScope.launch {
            categoryUiState = CategoryUiState.Loading
            categoryRepository.getCategoryes()
            categoryUiState = CategoryUiState.Success
        }
    }
}