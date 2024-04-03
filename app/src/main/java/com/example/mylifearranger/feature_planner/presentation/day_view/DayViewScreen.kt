package com.example.mylifearranger.feature_planner.presentation.day_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mylifearranger.R
import com.example.mylifearranger.feature_planner.presentation.day_view.components.dayViewActionButtons
import com.example.mylifearranger.core.presentation.components.AppBar
import com.example.mylifearranger.core.presentation.components.BottomBar
import com.example.mylifearranger.core.presentation.components.BottomBarItem
import com.example.mylifearranger.feature_planner.presentation.day_view.components.DayViewContent
import com.example.mylifearranger.feature_planner.presentation.util.Screen
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayViewScreen(
    navController: NavController,
    date: String,
    viewModel: DayViewViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val scope = rememberCoroutineScope()

    // Parse string date in format of YYYY-MM-DD to LocalDate
    val localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE)

    var selectedDate by remember { mutableStateOf<LocalDate?>(localDate) }
    val weekDaysRowState = rememberLazyListState()
    LaunchedEffect(key1 = date) {
        println("Launched effect")

        coroutineScope {
            launch {
                if (localDate.dayOfMonth > 3 && localDate.dayOfMonth < localDate.lengthOfMonth() - 3) {
                    weekDaysRowState.animateScrollToItem(localDate.dayOfMonth - 3)
                } else if (localDate.dayOfMonth <= 3) {
                    weekDaysRowState.animateScrollToItem(0)
                } else {
                    weekDaysRowState.animateScrollToItem(localDate.lengthOfMonth() - 1)
                }
            }
        }
        viewModel.onScreenDisplayed(date)
    }
    val appTitle: String =
        "${localDate.toString()} | ${localDate.dayOfWeek.toString().substring(0, 3)}"

    // print current date
    println("Current date: $localDate")
    // print events
    try {
        println("Events: ${state.events}")
    } catch (e: Exception) {
        println("No events")
    }
    // Get events for the current date
    val events = state.events

    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = {
                navController.navigate(Screen.AddEditEventScreen.route)
            },
            Modifier.background(color = MaterialTheme.colorScheme.background)
        ) {
            Icon(painterResource(id = R.drawable.baseline_add_24), contentDescription = "Add event")
        }
    }, topBar = {
        AppBar(
            appTitle, dayViewActionButtons(
                onTodayClick = {
                    val today: LocalDate = LocalDate.now()
                    navController.currentBackStackEntry?.let { currentBackStackEntry ->
                        navController.navigate(
                            route = Screen.DayViewScreen.route + "?date=${
                                LocalDate.parse(
                                    today.toString(), DateTimeFormatter.ISO_DATE
                                )
                            }"
                        ) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(currentBackStackEntry.id) {
                                saveState = true
                            }
                        }
                    }
                    selectedDate = today
                },
                onPickDateClick = { /*TODO*/ },
                onViewSettingsClick = { /*TODO*/ },
            )
        )
    }, bottomBar = { BottomBar(navController, BottomBarItem.items[0]) }) { paddingValues ->
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
            DayViewContent(
                selectedDate = selectedDate!!,
                events = state.events,
                planTasks = state.planTasks,
                weekDaysRowState = weekDaysRowState,
                onDaySelected = { newDate ->
                    selectedDate = newDate
                    navController.currentBackStackEntry?.let { currentBackStackEntry ->
                        navController.navigate(route = Screen.DayViewScreen.route + "?date=${selectedDate}") {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(currentBackStackEntry.id) {
                                saveState = true
                            }
                        }
                    }
                },
                onEventClick = { event ->
                    navController.navigate(Screen.EventDetailsScreen.route + "?eventId=${event.id}")
                })
        }
    }
}
