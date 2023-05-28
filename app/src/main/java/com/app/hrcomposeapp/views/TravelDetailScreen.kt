package com.app.hrcomposeapp.views


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.hrcomposeapp.utils.AppScreens
import com.app.hrcomposeapp.utils.CustomToolbarWithBackArrow
import com.app.hrcomposeapp.viewmodels.JSONViewModel
import com.app.hrcomposeapp.viewmodels.TravelViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TravelDetailScreen(
    navController: NavHostController,
    travelViewModel: TravelViewModel,
    TravelId: String?
) {

    travelViewModel.findTravelById(TravelId!!)
    val selectedTravel = travelViewModel.foundTravel.observeAsState().value

    Scaffold(topBar = {
        CustomToolbarWithBackArrow(title = "Направление", navController = navController)
    }) {
        if (selectedTravel != null) {
            Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = Color.White,
                    elevation = 2.dp
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = selectedTravel.id,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = selectedTravel.name,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = selectedTravel.destination,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = selectedTravel.country,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = selectedTravel.category,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = selectedTravel.duration,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = selectedTravel.price,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Row {
                            Button(
                                onClick = {
                                    travelViewModel.deleteTravel(selectedTravel)
                                    navController.popBackStack()
                                }, modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = "Удалить",
                                    fontSize = 16.sp,
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Button(
                                onClick = {
                                    navController.navigate(AppScreens.AddEditTravelScreen.route + "/" + selectedTravel.id + "/" + true)
                                }, modifier = Modifier.weight(1f)
                            ) {
                                Text(text = "Редактировать", fontSize = 16.sp)
                            }
                        }
                    }
                }
            }
        } else {
            Text(
                text = "null",
                fontSize = 50.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

