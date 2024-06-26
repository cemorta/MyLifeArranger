package com.example.mylifearranger.feature_planner.presentation

import PlanViewScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mylifearranger.feature_planner.presentation.add_edit_event.AddEditEventScreen
import com.example.mylifearranger.feature_planner.presentation.add_edit_plan.AddEditPlanScreen
import com.example.mylifearranger.feature_planner.presentation.add_edit_plan.SharedViewModel
import com.example.mylifearranger.feature_planner.presentation.add_edit_task.AddEditTaskScreen
import com.example.mylifearranger.feature_planner.presentation.day_view.DayViewScreen
import com.example.mylifearranger.feature_planner.presentation.event_details.EventDetailsScreen
import com.example.mylifearranger.feature_planner.presentation.plan_details.PlanDetailsScreen
import com.example.mylifearranger.feature_planner.presentation.plan_overview.PlanOverviewScreen
import com.example.mylifearranger.feature_planner.presentation.task_overview.TaskOverviewScreen
import com.example.mylifearranger.feature_planner.presentation.task_view.TaskViewScreen
import com.example.mylifearranger.feature_planner.presentation.util.Screen
import com.example.mylifearranger.ui.theme.MyLifeArrangerTheme
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyLifeArrangerTheme {
                val currentDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.DayViewScreen.route + "?date={date}"
                    ) {
                        composable(
                            route = Screen.DayViewScreen.route + "?date={date}",
                            arguments = listOf(
                                navArgument(
                                    name = "date"
                                ) {
                                    type = NavType.StringType
                                    defaultValue = currentDate
                                }
                            )
                        ) {
                            val date = it.arguments?.getString("date") ?: currentDate
                            DayViewScreen(
                                navController = navController,
                                date = date,
                            )
                        }
                        composable(
                            route = Screen.AddEditEventScreen.route +
                                    "?eventId={eventId}&eventColor={eventColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "eventId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "eventColor"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            val color = it.arguments?.getInt("eventColor") ?: -1
                            AddEditEventScreen(
                                navController = navController,
                                eventColor = color
                            )
                        }
                        composable(
                            route = Screen.EventDetailsScreen.route +
                                    "?eventId={eventId}",
                            arguments = listOf(
                                navArgument(
                                    name = "eventId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "eventColor"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            EventDetailsScreen(
                                navController = navController,
                            )
                        }
                        composable(
                            route = Screen.TaskViewScreen.route + "?taskType={taskType}&date={date}",
                            arguments = listOf(
                                navArgument(
                                    name = "taskType"
                                ) {
                                    type = NavType.StringType
                                    defaultValue = "DAILY"
                                },
                                navArgument(
                                    name = "date"
                                ) {
                                    type = NavType.StringType
                                    defaultValue = currentDate
                                }
                            )
                        ) {
                            TaskViewScreen(
                                navController = navController,
                            )
                        }
                        composable(
                            route = Screen.TaskOverviewScreen.route +
                                    "?taskId={taskId}",
                            arguments = listOf(
                                navArgument(
                                    name = "taskId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            TaskOverviewScreen(
                                navController = navController,
                            )
                        }
                        composable(
                            route = Screen.AddEditTaskScreen.route +
                                    "?taskId={taskId}",
                            arguments = listOf(
                                navArgument(
                                    name = "taskId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            AddEditTaskScreen(
                                navController = navController,
                            )
                        }
                        composable(
                            route = Screen.PlanViewScreen.route,
                        ) {
                            PlanViewScreen(
                                navController = navController,
                            )
                        }
                        composable(
                            route = Screen.AddEditPlanScreen.route +
                                    "?planId={planId}",
                            arguments = listOf(
                                navArgument(
                                    name = "planId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            val sharedViewModel: SharedViewModel = viewModel(it)
                            AddEditPlanScreen(
                                navController = navController,
                                sharedViewModel = sharedViewModel,
                            )
                        }
                        composable(
                            route = Screen.PlanOverviewScreen.route +
                                    "?planId={planId}",
                            arguments = listOf(
                                navArgument(
                                    name = "planId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            val sharedViewModel: SharedViewModel = viewModel(navController.previousBackStackEntry!!)
                            PlanOverviewScreen(
                                navController = navController,
                                sharedViewModel = sharedViewModel,
                            )
                        }
                        composable(
                            route = Screen.PlanDetailsScreen.route +
                                    "?planId={planId}",
                            arguments = listOf(
                                navArgument(
                                    name = "planId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ) {
                            PlanDetailsScreen(
                                navController = navController,
                            )
                        }
                    }

                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DayScreenPreview() {
//    MyLifeArrangerTheme {
//        DayScreen(LocalDate.now())
//    }
//}