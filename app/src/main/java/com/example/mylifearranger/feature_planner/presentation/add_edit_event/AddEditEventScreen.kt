package com.example.mylifearranger.feature_planner.presentation.add_edit_event

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mylifearranger.R
import com.example.mylifearranger.feature_planner.domain.model.Event
import com.example.mylifearranger.feature_planner.presentation.add_edit_event.components.TransparentHintTextField
import com.example.mylifearranger.feature_planner.presentation.util.AppBar
import com.example.mylifearranger.feature_planner.presentation.util.DatePicker
import com.example.mylifearranger.feature_planner.presentation.util.TimePicker
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDate
import java.time.LocalTime
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
    val startDateState = viewModel.eventStartDateTime.value
    val endDateState = viewModel.eventEndDateTime.value
    val colorState = viewModel.eventColor.value

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
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_save_24),
                    contentDescription = "Save event"
                )
            }
        },
        topBar = {
            AppBar(
                title = "Add event",
                isThereBackButton = true,
                navController = navController
            )
        },
        snackbarHost = {
            snackbarHostState.currentSnackbarData?.let { snackbarData ->
                Snackbar(
                    snackbarData = snackbarData,
                )
            }
        }
    ) { paddingValues ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    paddingValues.calculateLeftPadding(layoutDirection = LayoutDirection.Ltr),
                    paddingValues.calculateTopPadding(),
                    paddingValues.calculateRightPadding(layoutDirection = LayoutDirection.Ltr),
                    paddingValues.calculateBottomPadding()
                ),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
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
                                .border(
                                    width = 3.dp,
                                    color = if (colorInt == viewModel.eventColor.value) {
                                        Color.Black
                                    } else {
                                        Color.Transparent
                                    },
                                    shape = CircleShape
                                )
                                .clickable {
                                    viewModel.onEvent(
                                        AddEditEventEvent.ChangeColor(
                                            colorInt
                                        )
                                    )
                                }
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
                    textStyle = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Start date",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )
                DateTimePicker(
                    initialDateValue = startDateState.toLocalDate(),
                    initialTimeValue = startDateState.toLocalTime(),
                    onDateSelected = {
                        viewModel.onEvent(AddEditEventEvent.EnteredStartDate(it))
                    },
                    onTimeSelected = {
                        viewModel.onEvent(AddEditEventEvent.EnteredStartTime(it))
                    },
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "End date",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )
                DateTimePicker(
                    initialDateValue = endDateState.toLocalDate(),
                    initialTimeValue = endDateState.toLocalTime(),
                    onDateSelected = {
                        viewModel.onEvent(AddEditEventEvent.EnteredEndDate(it))
                    },
                    onTimeSelected = {
                        viewModel.onEvent(AddEditEventEvent.EnteredEndTime(it))
                    },
                )
            }
        }
    }
}

@Composable
fun DateTimePicker(
    initialDateValue: LocalDate? = null,
    initialTimeValue: LocalTime? = null,
    onDateSelected: (LocalDate) -> Unit,
    onTimeSelected: (LocalTime) -> Unit
) {
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    Column {
        DatePicker(
            label = "Date",
            value = initialDateValue?.format(dateFormatter) ?: "",
            onValueChange = {
                onDateSelected(LocalDate.parse(it, dateFormatter))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TimePicker(
            label = "Time",
            value = initialTimeValue?.format(timeFormatter) ?: "",
            onValueChange = {
                onTimeSelected(LocalTime.parse(it, timeFormatter))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AddEditEventScreenPreview() {
//    AddEditEventScreen(null,0, hiltViewModel())
//}