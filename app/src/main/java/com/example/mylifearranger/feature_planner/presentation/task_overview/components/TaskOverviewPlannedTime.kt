package com.example.mylifearranger.feature_planner.presentation.task_overview.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import java.time.LocalDateTime

@Composable
fun TaskOverviewPlannedTime(
    taskPlannedLocalDateTime: LocalDateTime?,
    isPlannedTimeSet: Boolean?
) {

    // Show planned time
    taskPlannedLocalDateTime?.let {
        Text(text = it.toString())
    }
    // Show if planned time is set
    isPlannedTimeSet?.let {
        Text(text = it.toString())
    }
}
