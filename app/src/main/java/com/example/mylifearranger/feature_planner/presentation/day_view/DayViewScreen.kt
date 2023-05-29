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
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.mylifearranger.feature_planner.presentation.util.Screen
import toLocalDateTime
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

    val appTitle: String = localDate.toString()

    // print current date
    println("Current date: $localDate")
    // print events
    try {
        println("Events: ${state.events}")
    }
    catch (e: Exception) {
        println("No events")
    }
    // Get events for the current date
    val events = state.events
    LaunchedEffect(key1 = date) {
        println("Launched effect")
        viewModel.onScreenDisplayed(date)
    }
//    // Create a list of example events
//    var exampleEvents = mutableListOf<Event>()
//    exampleEvents.add(Event(id = 1, title = "Event 1", start = LocalDateTime.parse("2021-10-01T10:00:00") , end = LocalDateTime.parse("2021-10-01T11:00:00"), color = 0x2000FF00.toInt()))
//    exampleEvents.add(Event(id = 2, title = "Event 2", start = LocalDateTime.parse("2021-10-01T12:00:00") , end = LocalDateTime.parse("2021-10-01T13:00:00"), color = 0x20000000.toInt()))
//    exampleEvents.add(Event(id = 3, title = "Event 3", start = LocalDateTime.parse("2021-10-01T14:30:00") , end = LocalDateTime.parse("2021-10-01T16:00:00"), color = 0x2000FF00.toInt()))

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
                onTodayClick = { /*TODO*/ },
                onPickDateClick = { /*TODO*/ },
                onViewSettingsClick = { /*TODO*/ },
            )
        )
    }, bottomBar = { BottomBar() }) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    it.calculateLeftPadding(layoutDirection = LayoutDirection.Ltr),
                    it.calculateTopPadding(),
                    it.calculateRightPadding(layoutDirection = LayoutDirection.Ltr),
                    it.calculateBottomPadding()
                ),
            color = MaterialTheme.colorScheme.background
        ) {
            Column {
                WeekDaysRow(localDate, navController)
                Divider()
                TimelineView(events, navController)
            }
        }
    }
}
