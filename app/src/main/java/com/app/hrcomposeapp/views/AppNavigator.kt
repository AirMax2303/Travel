package com.app.hrcomposeapp.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.app.hrcomposeapp.utils.AppScreens
import com.app.hrcomposeapp.viewmodels.JSONViewModel
import com.app.hrcomposeapp.viewmodels.TravelViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppRouter(
    navController: NavHostController,
    travelViewModel: TravelViewModel,
    jsonViewModel: JSONViewModel,
) {
    AnimatedNavHost(navController, startDestination = AppScreens.TravelScreen.route) {

        composable(route = AppScreens.TravelScreen.route) {
            TravelScreen(navController, travelViewModel)
        }
        composable(route = AppScreens.JSONScreen.route) {
            JSONlScreen(navController, travelViewModel, jsonViewModel)
        }
        composable(route = AppScreens.AddEditTravelScreen.route + "/{travelId}/{isEdit}",
            arguments = listOf(
                navArgument("travelId") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("isEdit") {
                    type = NavType.BoolType
                    defaultValue = false
                }
            ), enterTransition = {
                // Let's make for a really long fade in
                slideInVertically(
                    initialOffsetY = { 1800 }
                )
            }, popExitTransition = {
                slideOutVertically(
                    targetOffsetY = { 1800 }
                )
            }
        ) {
            val isEdit = it.arguments?.getBoolean("isEdit")
            val travelId = it.arguments?.getString("travelId")
            AddEditTravelScreen(navController, travelViewModel, jsonViewModel, travelId, isEdit!!)
        }
        composable(route = AppScreens.TravelDetailScreen.route + "/{travelId}",
            arguments = listOf(
                navArgument("travelId") {
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )) {
            val travelId = it.arguments?.getString("travelId")
            TravelDetailScreen(navController, travelViewModel, travelId)
        }
    }

}

