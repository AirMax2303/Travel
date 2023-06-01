package com.app.hrcomposeapp.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.hrcomposeapp.R
import com.app.hrcomposeapp.database.Category
import com.app.hrcomposeapp.database.Travel
import com.app.hrcomposeapp.utils.AppScreens
import com.app.hrcomposeapp.utils.CustomToolbar
import com.app.hrcomposeapp.viewmodels.CategoryUiState
import com.app.hrcomposeapp.viewmodels.CategoryViewModel
import com.app.hrcomposeapp.viewmodels.JSONUiState
import com.app.hrcomposeapp.viewmodels.JSONViewModel
import com.app.hrcomposeapp.viewmodels.TravelViewModel
import kotlinx.coroutines.launch

class CategoryScreen {
}

@Composable
fun HomeScreen(
    navController: NavHostController,
    travelViewModel: TravelViewModel,
    jsonViewModel: JSONViewModel,
    categoryViewModel: CategoryViewModel,
) {
    travelViewModel.getAllTravels()
    when (jsonViewModel.jsonUiState) {
        is JSONUiState.Loading -> LoadingScreen()
        is JSONUiState.Success -> {
            if (travelViewModel.travelList.value?.isEmpty() == true) {
                travelViewModel.addListTravel((jsonViewModel.jsonUiState as JSONUiState.Success).list)
            }
            CategoryScreen(navController, categoryViewModel)
        }
        is JSONUiState.Error -> CategoryScreen(navController, categoryViewModel)
    }
}

@Composable
fun CategoryScreen(
    navController: NavHostController,
    categoryViewModel: CategoryViewModel,
) {
    categoryViewModel.getCategoryes()
    when(categoryViewModel.categoryUiState) {
        is CategoryUiState.Loading -> LoadingScreen()
        is CategoryUiState.Success -> _CategoryScreen(navController, categoryViewModel)
        is CategoryUiState.Error -> ErrorScreen()
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(stringResource(R.string.loading_failed))
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun _CategoryScreen(
    navController: NavHostController,
    categoryViewModel: CategoryViewModel,
) {

//    categoryViewModel.getCategoryes()
    val coroutineScope = rememberCoroutineScope()
    val lazyListState = rememberLazyListState()
    Scaffold(
        topBar = {
            CustomToolbar(title = stringResource(id = R.string.category))
        },
        content = {

            val categoryList: List<Category> by categoryViewModel.categoryList.observeAsState(initial = listOf())

            if (categoryList.isNotEmpty()) {
                Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                    LazyColumn(
                        modifier = Modifier.padding(vertical = 4.dp), state = lazyListState
                    ) {
                        items(items = categoryList) { category ->
                            categoryCard(
                                categoryViewModel = categoryViewModel,
                                category = category.category,
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
                        " Категорий нет",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
/*
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
            }
        }

 */
    )

}

@Composable
fun categoryCard(
    categoryViewModel: CategoryViewModel,
    category: String,
    navController: NavHostController
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                navController.navigate(
                    AppScreens.TravelScreen.routeWithArgs(category)
                )
            }
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        elevation = 2.dp
    ) {
        Column(modifier = Modifier
            .padding(20.dp)
        ) {
            Text(
                text = category,
                fontSize = 30.sp,
            )
        }
    }
}