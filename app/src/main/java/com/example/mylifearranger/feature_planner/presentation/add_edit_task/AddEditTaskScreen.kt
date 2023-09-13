package com.example.mylifearranger.feature_planner.presentation.add_edit_task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mylifearranger.R
import com.example.mylifearranger.core.presentation.components.TransparentHintTextField
import com.example.mylifearranger.core.presentation.components.AppBar
import com.example.mylifearranger.core.presentation.components.TransparentHintNumberField
import com.example.mylifearranger.feature_planner.presentation.add_edit_task.components.AddEditTaskContent
import com.example.mylifearranger.feature_planner.presentation.add_edit_task.components.TaskTypeDropdown
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskScreen(
    navController: NavController,
    viewModel: AddEditTaskViewModel = hiltViewModel()
) {
    val titleState = viewModel.taskTitle.value
    val durationHourState = viewModel.taskDurationHour.value
    val durationMinuteState = viewModel.taskDurationMinute.value
    val taskTypeState = viewModel.taskType.value
    val plannedLocalDateTimeState = viewModel.taskPlannedLocalDateTime.value
    val isTimeSetState = viewModel.isTimeSet.value
    val dueLocalDateTimeState = viewModel.dueLocalDateTime.value

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditTaskViewModel.UiAction.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is AddEditTaskViewModel.UiAction.SaveTask -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditTaskAction.SaveTask)
                },
                Modifier.background(
                    MaterialTheme.colorScheme.background,
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_save_24),
                    contentDescription = "Save task"
                )
            }
        },
        topBar = {
            AppBar(
                title = "Add task",
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
                AddEditTaskContent(
                    titleState = titleState,
                    durationHourState = durationHourState,
                    durationMinuteState = durationMinuteState,
                    taskTypeState = taskTypeState,
                    plannedLocalDateTimeState = plannedLocalDateTimeState,
                    isTimeSetState = isTimeSetState,
                    dueLocalDateTimeState = dueLocalDateTimeState,
                    onEvent = { action ->
                        viewModel.onEvent(action)
                    }
                )

//                Text(
//                    text = "Start date",
//                    style = MaterialTheme.typography.bodyMedium,
//                    color = MaterialTheme.colorScheme.onSurface,
//                    modifier = Modifier.fillMaxWidth()
//                )
//                DateTimePicker(
//                    initialDateValue = startDateState.toLocalDate(),
//                    initialTimeValue = startDateState.toLocalTime(),
//                    onDateSelected = {
//                        viewModel.onEvent(AddEditEventEvent.EnteredStartDate(it))
//                    },
//                    onTimeSelected = {
//                        viewModel.onEvent(AddEditEventEvent.EnteredStartTime(it))
//                    },
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                Text(
//                    text = "End date",
//                    style = MaterialTheme.typography.bodyMedium,
//                    color = MaterialTheme.colorScheme.onSurface,
//                    modifier = Modifier.fillMaxWidth()
//                )
//                DateTimePicker(
//                    initialDateValue = endDateState.toLocalDate(),
//                    initialTimeValue = endDateState.toLocalTime(),
//                    onDateSelected = {
//                        viewModel.onEvent(AddEditEventEvent.EnteredEndDate(it))
//                    },
//                    onTimeSelected = {
//                        viewModel.onEvent(AddEditEventEvent.EnteredEndTime(it))
//                    },
//                )
            }
        }
    }
}
