package com.example.mylifearranger.feature_planner.presentation.day_view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
fun DaySquare(date: LocalDate, planTaskInThatDay: Int, isSelected: Boolean, onClick: () -> Unit) {
    val dayOfWeek = date.dayOfWeek.getDisplayName(
        java.time.format.TextStyle.SHORT,
        java.util.Locale.getDefault()
    )
    Box(
        modifier = Modifier
            .clickable(onClick = onClick) // Add a click listener
            .size(50.dp) // Set the size of the square
            .background(
                color = if (isSelected) { Color.Blue } else if(planTaskInThatDay > 0) {Color.Green} else {Color.LightGray},
                shape = RoundedCornerShape(4.dp)
            ), // Set the background color and shape
        contentAlignment = Alignment.Center // Align the content to the center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(dayOfWeek)
            Text(date.dayOfMonth.toString())
        }
    }
}
