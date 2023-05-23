package com.example.mylifearranger.feature_planner.presentation.day_view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Composable
fun WeekDaysRow(currentDate: LocalDate?) {
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    val startDate = LocalDate.now().withDayOfMonth(1)
    val endDate = LocalDate.now().withDayOfMonth(startDate.lengthOfMonth())
    val daysOfMonth = ChronoUnit.DAYS.between(startDate, endDate).toInt() + 1
    val dates = (1..daysOfMonth).map {
        startDate.plusDays(it.toLong() - 1)
    }
    if (selectedDate == null) selectedDate = currentDate

    LazyRow(
        modifier = Modifier.padding(8.dp), // Add padding around the LazyRow
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Add space between items
    ) {
        items(dates) { date ->
            DaySquare(date = date, isSelected = date == selectedDate) {
                selectedDate = date
            }
        }
    }
}
