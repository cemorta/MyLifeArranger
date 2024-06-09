package com.example.mylifearranger.feature_planner.presentation.task_overview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mylifearranger.core.presentation.components.AppBar
import com.example.mylifearranger.core.presentation.components.BottomBar
import com.example.mylifearranger.core.presentation.components.BottomBarItem
import com.example.mylifearranger.feature_planner.presentation.task_overview.components.TaskOverviewContent
import com.example.mylifearranger.feature_planner.presentation.task_overview.components.taskOverviewActionButtons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskOverviewScreen(
    navController: NavController,
    viewModel: TaskOverviewViewModel = hiltViewModel()
) {

    val appTitle = viewModel.taskTitle.value.text


    Scaffold(
//        floatingActionButton = {
//        FloatingActionButton(
//            onClick = {
//                navController.navigate(Screen.AddEditTaskScreen.route)
//            },
//            Modifier.background(color = MaterialTheme.colorScheme.background)
//        ) {
//            Icon(painterResource(id = R.drawable.baseline_add_24), contentDescription = "Add task")
//        }
//    },
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
        }, bottomBar = { BottomBar(navController, BottomBarItem.items[2]) }) { paddingValues ->
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
