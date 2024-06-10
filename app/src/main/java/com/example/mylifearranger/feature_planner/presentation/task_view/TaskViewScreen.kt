package com.example.mylifearranger.feature_planner.presentation.task_view

import androidx.compose.foundation.layout.Column
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
import com.example.mylifearranger.feature_planner.presentation.task_view.components.TaskViewContent
import com.example.mylifearranger.core.presentation.components.BottomBar
import com.example.mylifearranger.core.presentation.components.BottomBarItem
import com.example.mylifearranger.feature_planner.presentation.task_view.components.IconButtonBar
import com.example.mylifearranger.feature_planner.presentation.util.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskViewScreen(
    navController: NavController,
    viewModel: TaskViewViewModel = hiltViewModel()
) {
//    LaunchedEffect(key1 = date) {
//        println("Launched effect")
//        viewModel.onScreenDisplayed(date)
//    }
//
    val state = viewModel.state.value
//    val scope = rememberCoroutineScope()
//
//    // Parse string date in format of YYYY-MM-DD to LocalDate
//    val localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE)
//
//    var selectedDate by remember { mutableStateOf<LocalDate?>(localDate) }
//
//    val appTitle = when (state.taskType) {
//        TaskType.YEARLY -> "${state.date!!.substring(0, 4)} ${state.taskType}"
//        TaskType.MONTHLY -> "${state.date!!.substring(0, 7)} ${state.taskType}"
//        TaskType.DAILY -> "${state.date} ${state.taskType}"
//        TaskType.NONE -> "Inbox"
//    }

//    // print task type
//    println("Task type: ${state.taskType}")
//    // print tasks
//    try {
//        println("Tasks: ${state.tasks}")
//    } catch (e: Exception) {
//        println("No tasks")
//    }
//
//    // print current date
//    println("Current date: $localDate")
//    // print events
//    try {
//        println("Events: ${state.events}")
//    } catch (e: Exception) {
//        println("No events")
//    }
//    // Get events for the current date
//    val events = state.events
//
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
            Column(
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween,
            ) {
                TaskViewContent(
                    state = state,
                    onTaskClick = { taskId ->
                        navController.navigate(Screen.TaskOverviewScreen.route + "?taskId=${taskId}")
                    },
                    onTaskCheckedChange = { taskId, isChecked ->
                        viewModel.onAction(TaskViewAction.UpdateTaskCompletion(taskId, isChecked))
                    },
                )
                IconButtonBar(
                    onAddClick = {
                        navController.navigate(Screen.AddEditTaskScreen.route)
                    },
                    onClickCompleted = {
                        if (viewModel.filter.intValue == 1) viewModel.onAction(TaskViewAction.FilterTask(0))
                        else viewModel.onAction(TaskViewAction.FilterTask(1))
                    },
                    viewModel.filter.intValue
                )
            }
        }
    }
//            Column {
//                WeekDaysRow(selectedDate, navController) {
//                    selectedDate = it
//                    navController.currentBackStackEntry?.let { currentBackStackEntry ->
//                        navController.navigate(route = Screen.DayViewScreen.route + "?date=${selectedDate}") {
//                            launchSingleTop = true
//                            restoreState = true
//                            popUpTo(currentBackStackEntry.id) {
//                                saveState = true
//                            }
//                        }
//                    }
//                }
//                Divider()
//                TimelineView(events, navController)
//            }
//        }
//    }
}
