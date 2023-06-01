package com.app.hrcomposeapp.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppScreens(val title: String, val route: String, var icon: ImageVector) {

    object HomeScreen : AppScreens("Путешествия", "home", Icons.Default.AccountCircle)
    object AddEditTravelScreen :
        AppScreens("Add/Edit Travel", "addEditTravelScreen", Icons.Default.Home)

    object TravelScreen : AppScreens("Путешествия", "travel", Icons.Default.AccountCircle)

    object CategoryScreen : AppScreens("Катерии", "category", Icons.Default.AccountCircle)

    object JSONScreen : AppScreens("JSON Путешествия", "json", Icons.Default.AccountCircle)

    object TravelDetailScreen :
        AppScreens("Travel Details", "travelrDetailScreen", Icons.Default.Home)

    fun routeWithArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
