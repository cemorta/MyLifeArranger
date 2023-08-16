package com.example.mylifearranger.feature_planner.presentation.add_edit_task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskScreen(
    navController: NavController,
    viewModel: AddEditTaskViewModel = hiltViewModel()
) {
    val titleState = viewModel.taskTitle.value

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is AddEditTaskViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }

                is AddEditTaskViewModel.UiEvent.SaveEvent -> {
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
                TransparentHintTextField(
                    text = titleState.text,
                    hint = titleState.hint,
                    onValueChange = {
                        viewModel.onEvent(AddEditTaskAction.EnteredTitle(it))
                    },
                    onFocusChange = {
                        viewModel.onEvent(AddEditTaskAction.ChangeTitleFocus(it))
                    },
                    isHintVisible = titleState.isHintVisible,
                    singleLine = true,
                    textStyle = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier = Modifier.height(16.dp))
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
