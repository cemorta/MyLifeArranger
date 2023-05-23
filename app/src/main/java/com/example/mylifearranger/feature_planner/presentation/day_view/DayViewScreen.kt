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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mylifearranger.R
import com.example.mylifearranger.feature_planner.presentation.day_view.components.TimelineView
import com.example.mylifearranger.feature_planner.presentation.day_view.components.WeekDaysRow
import com.example.mylifearranger.feature_planner.presentation.util.AppBar
import com.example.mylifearranger.feature_planner.presentation.util.BottomBar
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
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()

    // Parse date from string to LocalDate
    val currentDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE)

    val date = currentDate?.format(DateTimeFormatter.ISO_DATE)
    val appTitle: String = date.toString()

    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = {
                navController.navigate(Screen.AddEditEventScreen.route)
            },
            Modifier.background(color = MaterialTheme.colorScheme.primary)
        ) {
            Icon(painterResource(id = R.drawable.baseline_add_24), contentDescription = "Add event")
        }
    }, topBar = { AppBar(appTitle) }, bottomBar = { BottomBar() }) {
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
                WeekDaysRow(currentDate)
                Divider()
                TimelineView()
            }
        }
    }
}
fun returnClockPosition(time: String): Float {
    val initialPadding = 11
    val paddingBetweenHours: Float = 81.5F
    val paddingBetweenMinutes: Float = 1.35F

    val hour = time.substring(0, 2).toInt()
    val minutes = time.substring(3, 5).toInt()
    val result: Float =
        hour * paddingBetweenHours + initialPadding + minutes * paddingBetweenMinutes
    return result
}
