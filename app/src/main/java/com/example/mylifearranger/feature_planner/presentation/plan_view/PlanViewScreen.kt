import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import com.example.mylifearranger.R
import com.example.mylifearranger.feature_planner.domain.model.Plan
import com.example.mylifearranger.feature_planner.domain.util.PlanType
import com.example.mylifearranger.feature_planner.presentation.plan_view.components.PlanList
import com.example.mylifearranger.core.presentation.components.ActionIconButton
import com.example.mylifearranger.core.presentation.components.AppBar
import java.time.LocalDateTime

//package com.example.mylifearranger.feature_planner.presentation.plan_view
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.FloatingActionButton
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Surface
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.LayoutDirection
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import com.example.mylifearranger.R
//import com.example.mylifearranger.feature_planner.domain.util.TaskType
//import com.example.mylifearranger.feature_planner.presentation.task_view.components.TaskViewContent
//import com.example.mylifearranger.feature_planner.presentation.task_view.components.taskViewActionButtons
//import com.example.mylifearranger.core.presentation.components.AppBar
//import com.example.mylifearranger.core.presentation.components.BottomBar
//import com.example.mylifearranger.core.presentation.components.BottomBarItem
//import com.example.mylifearranger.feature_planner.presentation.util.Screen
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TaskViewScreen(
//    navController: NavController,
//    viewModel: TaskViewViewModel = hiltViewModel()
//) {
////    LaunchedEffect(key1 = date) {
////        println("Launched effect")
////        viewModel.onScreenDisplayed(date)
////    }
////
//    val state = viewModel.state.value
////    val scope = rememberCoroutineScope()
////
////    // Parse string date in format of YYYY-MM-DD to LocalDate
////    val localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE)
////
////    var selectedDate by remember { mutableStateOf<LocalDate?>(localDate) }
////
//    val appTitle = when (state.taskType) {
//        TaskType.YEARLY -> "${state.date!!.substring(0, 4)} ${state.taskType}"
//        TaskType.MONTHLY -> "${state.date!!.substring(0, 7)} ${state.taskType}"
//        TaskType.DAILY -> "${state.date} ${state.taskType}"
//    }
//
//    // print task type
//    println("Task type: ${state.taskType}")
//    // print tasks
//    try {
//        println("Tasks: ${state.tasks}")
//    } catch (e: Exception) {
//        println("No tasks")
//    }
////
////    // print current date
////    println("Current date: $localDate")
////    // print events
////    try {
////        println("Events: ${state.events}")
////    } catch (e: Exception) {
////        println("No events")
////    }
////    // Get events for the current date
////    val events = state.events
////
//    Scaffold(floatingActionButton = {
//        FloatingActionButton(
//            onClick = {
//                navController.navigate(Screen.AddEditTaskScreen.route)
//            },
//            Modifier.background(color = MaterialTheme.colorScheme.background)
//        ) {
//            Icon(painterResource(id = R.drawable.baseline_add_24), contentDescription = "Add task")
//        }
//    }, topBar = {
//        AppBar(
//            appTitle, taskViewActionButtons(state.taskType,
//                {
//                    viewModel.onEvent(
//                        TaskViewEvent.FilterTaskType(TaskType.YEARLY, state.date!!)
//                    )
//                },
//                {
//                    viewModel.onEvent(
//                        TaskViewEvent.FilterTaskType(TaskType.MONTHLY, state.date!!)
//                    )
//                },
//                {
//                    viewModel.onEvent(
//                        TaskViewEvent.FilterTaskType(TaskType.DAILY, state.date!!)
//                    )
//                }
//            )
//        )
//    }, bottomBar = { BottomBar(navController, BottomBarItem.items[2]) }) { paddingValues ->
//        Surface(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(
//                    paddingValues.calculateLeftPadding(layoutDirection = LayoutDirection.Ltr),
//                    paddingValues.calculateTopPadding(),
//                    paddingValues.calculateRightPadding(layoutDirection = LayoutDirection.Ltr),
//                    paddingValues.calculateBottomPadding()
//                ),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            TaskViewContent(state)
//
//        }
//    }
////            Column {
////                WeekDaysRow(selectedDate, navController) {
////                    selectedDate = it
////                    navController.currentBackStackEntry?.let { currentBackStackEntry ->
////                        navController.navigate(route = Screen.DayViewScreen.route + "?date=${selectedDate}") {
////                            launchSingleTop = true
////                            restoreState = true
////                            popUpTo(currentBackStackEntry.id) {
////                                saveState = true
////                            }
////                        }
////                    }
////                }
////                Divider()
////                TimelineView(events, navController)
////            }
////        }
////    }
//}
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PlanViewScreenPreview() {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Go to add plan screen
                },
                Modifier.background(color = MaterialTheme.colorScheme.background)
            ) {
                Icon(
                    painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = "Add plan"
                )
            }
//    }, topBar = {
//        AppBar(
//            appTitle, taskViewActionButtons(state.taskType,
//                {
//                    viewModel.onEvent(
//                        TaskViewEvent.FilterTaskType(TaskType.YEARLY, state.date!!)
//                    )
//                },
//                {
//                    viewModel.onEvent(
//                        TaskViewEvent.FilterTaskType(TaskType.MONTHLY, state.date!!)
//                    )
//                },
//                {
//                    viewModel.onEvent(
//                        TaskViewEvent.FilterTaskType(TaskType.DAILY, state.date!!)
//                    )
//                }
//            )
//        )
        },
        topBar = {
            AppBar(
                "Plan",
                listOf(
                    ActionIconButton(
                        icon = R.drawable.baseline_filter_list_24,
                        "Filter",
                        onClick = {
                            // Go to filter screen
                        }
                    ),
                    ActionIconButton(
                        icon = R.drawable.baseline_search_24,
                        "Search",
                        onClick = {
                            // Go to search screen
                        }
                    )
                )
            )
        },
//        bottomBar = { BottomBar(navController, BottomBarItem.items[2]) }
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

            // Example plans for preview
            val plans = listOf(
                Plan(
                    "Plan 1",
                    PlanType.TOTAL,
                    20,
                    0,
                    "km",
                    null,
                    null,
                    6,
                    LocalDateTime.now().toTimestamp(),
                    LocalDateTime.now().plusHours(1).toTimestamp(),
                    false,
                    1,
                ),
                Plan(
                    "Plan 2",
                    PlanType.RANGE,
                    null,
                    0,
                    "pages",
                    40,
                    50,
                    7,
                    LocalDateTime.now().toTimestamp(),
                    LocalDateTime.now().plusHours(1).toTimestamp(),
                    false,
                    2,
                ),


            )

            // Plan view content
            PlanList(plans)


        }
    }
}