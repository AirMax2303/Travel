package com.app.hrcomposeapp.views

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.app.hrcomposeapp.repository.CategoryRepository
import com.app.hrcomposeapp.repository.RepoJSON
import com.app.hrcomposeapp.ui.theme.HRComposeAppTheme
import com.app.hrcomposeapp.viewmodels.CategoryViewModel
import com.app.hrcomposeapp.viewmodels.JSONViewModel
import com.app.hrcomposeapp.viewmodels.TravelViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val categoryViewModel: CategoryViewModel by viewModels()
    private val travelViewModel: TravelViewModel by viewModels()
    private val jsonViewModel: JSONViewModel = JSONViewModel(RepoJSON())

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HRComposeAppTheme {
                AppMainScreen(categoryViewModel, travelViewModel, jsonViewModel)
            }
        }
    }
}
