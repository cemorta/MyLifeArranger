package com.example.mylifearranger.feature_planner.presentation.add_edit_event

import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mylifearranger.R
import com.example.mylifearranger.feature_planner.domain.model.Event
import com.example.mylifearranger.feature_planner.presentation.add_edit_event.components.TransparentHintTextField
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditEventScreen(
    navController: NavController,
    eventColor: Int,
    viewModel: AddEditEventViewModel = hiltViewModel()
) {
    val titleState = viewModel.eventTitle.value
    val startDateState = viewModel.eventStartDate.value
    val endDateState = viewModel.eventEndDate.value

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditEventViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is AddEditEventViewModel.UiEvent.SaveEvent -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditEventEvent.SaveEvent)
                },
                Modifier.background(
                    MaterialTheme.colorScheme.background,
                    MaterialTheme.shapes.small
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_save_24),
                    contentDescription = "Save event"
                )
            }
        },
        snackbarHost = {
            snackbarHostState.currentSnackbarData?.let { snackbarData ->
                Snackbar(
                    snackbarData = snackbarData,
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Event.eventColors.forEach { color ->
                    val colorInt = color.toArgb()
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .shadow(15.dp, CircleShape)
                            .background(color)
                            .clickable { viewModel.onEvent(AddEditEventEvent.ChangeColor(colorInt)) }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TransparentHintTextField(
                text = titleState.text,
                hint = titleState.hint,
                onValueChange = {
                    viewModel.onEvent(AddEditEventEvent.EnteredTitle(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddEditEventEvent.ChangeTitleFocus(it))
                },
                isHintVisible = titleState.isHintVisible,
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(16.dp))
            DateTimePicker(onDateTimeSelected = {
                viewModel.onEvent(AddEditEventEvent.EnteredStartDate(it))
            })
            Spacer(modifier = Modifier.height(16.dp))
            DateTimePicker(onDateTimeSelected = {
                viewModel.onEvent(AddEditEventEvent.EnteredEndDate(it))
            })



        }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePicker(onDateTimeSelected: (LocalDateTime) -> Unit) {
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    val date = remember { mutableStateOf(LocalDateTime.now().format(dateFormatter)) }
    val time = remember { mutableStateOf(LocalDateTime.now().format(timeFormatter)) }

    Column {
        TextField(
            value = date.value,
            onValueChange = { date.value = it },
            label = { Text("Date") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = time.value,
            onValueChange = { time.value = it },
            label = { Text("Time") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(onClick = {
            val dateTime = LocalDateTime.parse("${date.value}T${time.value}", DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            onDateTimeSelected(dateTime)
        }) {
            Text("Submit")
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun AddEditEventScreenPreview() {
//    AddEditEventScreen(null,0, hiltViewModel())
//}