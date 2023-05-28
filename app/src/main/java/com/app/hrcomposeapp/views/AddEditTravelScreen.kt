package com.app.hrcomposeapp.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.hrcomposeapp.R
import com.app.hrcomposeapp.database.Travel
import com.app.hrcomposeapp.ui.theme.Shapes
import com.app.hrcomposeapp.ui.theme.customWidget.CustomTextField
import com.app.hrcomposeapp.utils.CustomToolbarWithBackArrow
import com.app.hrcomposeapp.viewmodels.TravelViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var id: String = ""
var destination: String = ""
var name: String = ""
var country: String = ""
var category: String = ""
var duration: String = ""
var price: String = ""

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddEditTravelScreen(
    navController: NavHostController,
    TravelViewModel: TravelViewModel,
    TravelToEdit: String?,
    isEdit: Boolean
) {
    lateinit var selectedTravel: Travel
    val mContext = LocalContext.current
    // The coroutine scope for event handlers calling suspend functions.
    val coroutineScope = rememberCoroutineScope()
    // True if the message about the edit feature is shown.
    var validationMessageShown by remember { mutableStateOf(false) }
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }
    Travel_clearAll()
    if (isEdit) {
        TravelViewModel.findTravelById(TravelToEdit!!)
        selectedTravel = TravelViewModel.foundTravel.observeAsState().value!!
        id = selectedTravel.id
        destination= selectedTravel.destination
        name = selectedTravel.name
        country = selectedTravel.country
        category = selectedTravel.category
        duration = selectedTravel.duration
        price = selectedTravel.price
    }

    // Shows the validation message.
    suspend fun showEditMessage() {
        if (!validationMessageShown) {
            validationMessageShown = true
            delay(3000L)
            validationMessageShown = false
        }
    }

    val scrollState = rememberScrollState()

    var isEdited by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            CustomToolbarWithBackArrow(
                title = if (isEdit) "Редактировать" else "Добавить",
                navController = navController
            )
        },
        content = {
            Surface(
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                        .verticalScroll(state = scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    imageUri?.let {
                        if (Build.VERSION.SDK_INT < 28) {
                            bitmap.value = MediaStore.Images
                                .Media.getBitmap(mContext.contentResolver, it)

                        } else {
                            val source = ImageDecoder
                                .createSource(mContext.contentResolver, it)
                            bitmap.value = ImageDecoder.decodeBitmap(source)
                        }

                        bitmap.value?.let { btm ->
                            Image(
                                bitmap = btm.asImageBitmap(),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(120.dp)
                                    .clip(Shapes.large),
                            )
                        }
                    }

                    ValidationMessage(validationMessageShown)

                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        labelResId = R.string.travel_id,
                        readOnly = isEdit,
                        inputWrapper = id,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        maxLength = 50,
                        maxLines = 1
                    ) {
                        isEdited = true
                        id = it
                    }
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        labelResId = R.string.travel_destination,
                        inputWrapper = destination,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        maxLength = 50,
                        maxLines = 1
                    ) {
                        isEdited = true
                        destination = it
                    }
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        labelResId = R.string.travel_name,
                        inputWrapper = name,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        maxLength = 50,
                        maxLines = 1
                    ) {
                        isEdited = true
                        name = it
                    }
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        labelResId = R.string.travel_country,
                        inputWrapper = country,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        maxLength = 100,
                        maxLines = 1
                    ) {
                        isEdited = true
                        country = it
                    }
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        labelResId = R.string.travel_category,
                        inputWrapper = category,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        maxLength = 100,
                        maxLines = 1
                    ) {
                        isEdited = true
                        category = it
                    }
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        labelResId = R.string.travel_duration,
                        inputWrapper = duration,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        maxLength = 100,
                        maxLines = 1
                    ) {
                        isEdited = true
                        duration = it
                    }
                    CustomTextField(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(),
                        labelResId = R.string.travel_price,
                        inputWrapper = price,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.None,
                            autoCorrect = false,
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        maxLength = 100,
                        maxLines = 1
                    ) {
                        isEdited = true
                        price = it
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(onClick = {
                        if (isEdited) {
                            val Travel = Travel(
                                id,
                                destination,
                                name,
                                country,
                                category,
                                duration,
                                price,
                            )
                            if (isEdit) {
                                updateTravelInDB(mContext, navController, Travel, TravelViewModel)
                            } else {
                                addTravelInDB(mContext, navController, Travel, TravelViewModel)
                            }
                            Travel_clearAll()
                        } else {
//                            toast(mContext, "Please add or update something...")
                            coroutineScope.launch {
                                showEditMessage()
                            }
                        }
                    }) {
                        Text(
                            text = if (isEdit) "Сохранить" else "Добавить",
                            fontSize = 18.sp,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    )
}

/**
 * Shows a validation message.
 */
@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun ValidationMessage(shown: Boolean) {
    AnimatedVisibility(
        visible = shown,
        enter = slideInVertically(
            initialOffsetY = { fullHeight -> -fullHeight },
            animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
        ),
        exit = slideOutVertically(
            // Exits by sliding out from offset 0 to -fullHeight.
            targetOffsetY = { fullHeight -> -fullHeight },
            animationSpec = tween(durationMillis = 250, easing = FastOutLinearInEasing)
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colors.secondary,
            elevation = 4.dp
        ) {
            Text(
                text = "Please add or update detail",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

fun Travel_clearAll() {
    id = ""
    destination = ""
    name = ""
    country = ""
    category = ""
    duration = ""
    price = ""
}

fun addTravelInDB(
    context: Context,
    navController: NavHostController,
    Travel: Travel,
    TravelViewModel: TravelViewModel
) {
    TravelViewModel.addTravel(Travel)
    navController.popBackStack()
}

fun updateTravelInDB(
    context: Context,
    navController: NavHostController,
    Travel: Travel,
    TravelViewModel: TravelViewModel
) {
    TravelViewModel.updateTravelDetails(Travel)
    navController.popBackStack()
    Log.d("updateTravelInDB", Travel.toString())
}

