package com.app.hrcomposeapp.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.app.hrcomposeapp.repository.RepoJSON
import com.app.hrcomposeapp.ui.theme.HRComposeAppTheme
import com.app.hrcomposeapp.viewmodels.JSONViewModel
import com.app.hrcomposeapp.viewmodels.TravelViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val travelViewModel: TravelViewModel by viewModels()
    private val jsonViewModel: JSONViewModel = JSONViewModel(RepoJSON())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HRComposeAppTheme {
                AppMainScreen(travelViewModel, jsonViewModel)
            }
        }
    }
}
