package com.example.mylifearranger.feature_planner.presentation.task_overview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mylifearranger.R
import com.example.mylifearranger.core.presentation.components.AppBar
import com.example.mylifearranger.core.presentation.components.BottomBar
import com.example.mylifearranger.core.presentation.components.BottomBarItem
import com.example.mylifearranger.feature_planner.presentation.add_edit_task.AddEditTaskViewModel
import com.example.mylifearranger.feature_planner.presentation.task_overview.components.TaskOverviewContent
import com.example.mylifearranger.feature_planner.presentation.task_overview.components.taskOverviewActionButtons
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskOverviewScreen(
    navController: NavController,
    viewModel: TaskOverviewViewModel = hiltViewModel()
) {

    val appTitle = viewModel.taskTitle.value.text
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { action ->
            when (action) {
                is TaskOverviewViewModel.UiAction.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = action.message
                    )
                }

                is TaskOverviewViewModel.UiAction.SaveTask -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onAction(TaskOverviewAction.SaveTask)
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
                title = appTitle,
                isThereBackButton = true,
                actionIconButtons = taskOverviewActionButtons(
                    onEdit = {
//                        navController.navigate(Screen.AddEditTaskScreen.route)
                    },
                    onDelete = {
//                        viewModel.onEvent(TaskViewAction.DeleteTask)
                    }
                ), navController = navController
            )
        },
        snackbarHost = {
            snackbarHostState.currentSnackbarData?.let { snackbarData ->
                Snackbar(
                    snackbarData = snackbarData,
                )
            }
        },
        bottomBar = { BottomBar(navController, BottomBarItem.items[2]) }) { paddingValues ->
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

            TaskOverviewContent(
                viewModel.taskTitle.value.text,
                viewModel.taskDurationHour.value.text,
                viewModel.taskDurationMinute.value.text,
                viewModel.taskType.value,
                viewModel.taskPlannedLocalDateTime.value,
                viewModel.dueLocalDateTime.value,
                viewModel.isDone.value,
                viewModel.isDueTimeSet.value,
                viewModel.isPlannedTimeSet.value,
                viewModel.subtasks.value,
                viewModel
            )
        }
    }

}
