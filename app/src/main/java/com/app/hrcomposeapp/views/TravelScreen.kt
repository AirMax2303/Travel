package com.app.hrcomposeapp.views

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.app.hrcomposeapp.R
//import com.app.travel.R
import com.app.hrcomposeapp.database.Travel
import com.app.hrcomposeapp.database.TravelJSON
import com.app.hrcomposeapp.utils.AppScreens
import com.app.hrcomposeapp.utils.CustomToolbar
import com.app.hrcomposeapp.viewmodels.JSONViewModel
import com.app.hrcomposeapp.viewmodels.TravelViewModel
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.N)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TravelScreen(
    navController: NavHostController,
    travelViewModel: TravelViewModel,
) {

    travelViewModel.getAllTravels()

    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    Scaffold(topBar = {
        CustomToolbar(title = stringResource(id = R.string.app_name))
    },
        content = {
            val travelList: List<Travel> by travelViewModel.travelList.observeAsState(initial = listOf())
            if (travelList.isNotEmpty()) {
                Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                    LazyColumn(
                        modifier = Modifier.padding(vertical = 4.dp), state = lazyListState
                    ) {
                        items(items = travelList) { travel ->
                            travelCard(
                                travelViewModel = travelViewModel,
                                travel = travel,
                                navController = navController
                            )
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        " Записей нет",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = {navController.navigate(AppScreens.JSONScreen.route)},
                    ) {
                        Text(
                            text = "Загрузить",
                            fontSize = 16.sp,
                        )
                    }

                }
            }
        },

        floatingActionButton = {
            Row() {
                FloatingActionButton(onClick = {
                    navController.navigate(AppScreens.AddEditTravelScreen.route + "/" + "0" + "/" + false)
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_add_24),
                        contentDescription = stringResource(id = R.string.desc_add_fab),
                    )
                }
                FloatingActionButton(onClick = {
//                    travelViewModel.travelList.observeForever {
                    coroutineScope.launch {
                        travelViewModel.deleteAllTravel()
                    }
//                    }
                }) {
                    Text(text = "  Удалить ВСЕ  ")
                }
            }
        })
}

@Composable
private fun LazyListState.isScrollingUp(): Boolean {
    var previousIndex by remember(this) { mutableStateOf(firstVisibleItemIndex) }
    var previousScrollOffset by remember(this) { mutableStateOf(firstVisibleItemScrollOffset) }
    return remember(this) {
        derivedStateOf {
            if (previousIndex != firstVisibleItemIndex) {
                previousIndex > firstVisibleItemIndex
            } else {
                previousScrollOffset >= firstVisibleItemScrollOffset
            }.also {
                previousIndex = firstVisibleItemIndex
                previousScrollOffset = firstVisibleItemScrollOffset
            }
        }
    }.value
}

@Composable
fun travelCard(travelViewModel: TravelViewModel, travel: Travel, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        elevation = 2.dp
    ) {
        Column(modifier = Modifier
            .padding(20.dp)
            .clickable {
                navController.navigate(
                    AppScreens.TravelDetailScreen.routeWithArgs(
                        travel.id
                    )
                )
            }
        ) {
            Row {
                Spacer(modifier = Modifier.width(20.dp))
                Column {
                    Text(
                        text = travel.name,
                        fontSize = 18.sp,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = travel.destination,
                        fontSize = 14.sp,
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column() {
                    Text(
                        text = travel.price,
                        fontSize = 18.sp,
                    )
                }
/*
                Column() {
                    IconButton(
                        onClick = { travelViewModel.deleteTravel(travel) }
                    ) {
                        Icon(Icons.Filled.Info, contentDescription = "Информация о приложении")
                    }
                }

 */
            }
        }
    }
}