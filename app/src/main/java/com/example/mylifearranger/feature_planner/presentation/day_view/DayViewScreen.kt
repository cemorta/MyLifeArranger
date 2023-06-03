package com.example.mylifearranger.feature_planner.presentation.day_view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
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
import com.example.mylifearranger.feature_planner.presentation.day_view.components.TimelineView
import com.example.mylifearranger.feature_planner.presentation.day_view.components.WeekDaysRow
import com.example.mylifearranger.feature_planner.presentation.day_view.components.dayViewActionButtons
import com.example.mylifearranger.feature_planner.presentation.util.AppBar
import com.example.mylifearranger.feature_planner.presentation.util.BottomBar
import com.example.mylifearranger.feature_planner.presentation.util.BottomBarItem
import com.example.mylifearranger.feature_planner.presentation.util.Screen
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DayViewScreen(
    navController: NavController,
    date: String,
    viewModel: DayViewViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = date) {
        println("Launched effect")
        viewModel.onScreenDisplayed(date)
    }

    val state = viewModel.state.value
    val scope = rememberCoroutineScope()

    // Parse string date in format of YYYY-MM-DD to LocalDate
    val localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE)

    var selectedDate by remember { mutableStateOf<LocalDate?>(localDate) }

    val appTitle: String = localDate.toString()

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
            Column {
                WeekDaysRow(selectedDate) {
                    selectedDate = it
                    navController.currentBackStackEntry?.let { currentBackStackEntry ->
                        navController.navigate(route = Screen.DayViewScreen.route + "?date=${selectedDate}") {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(currentBackStackEntry.id) {
                                saveState = true
                            }
                        }
                    }
                }
                Divider()
                TimelineView(events, navController)
            }
        }
    }
}
