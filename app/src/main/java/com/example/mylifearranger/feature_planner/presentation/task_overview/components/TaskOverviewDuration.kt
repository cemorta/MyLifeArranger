package com.example.mylifearranger.feature_planner.presentation.task_overview.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TaskOverviewDuration(
    durationHour: String?,
    durationMinute: String?,
) {

    // Show task duration
    durationHour?.let {
        Text(text = it)
    }
    durationMinute?.let {
        Text(text = it)
    }
}
