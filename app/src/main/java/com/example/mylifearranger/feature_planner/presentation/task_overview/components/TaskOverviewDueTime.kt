package com.example.mylifearranger.feature_planner.presentation.task_overview.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import java.time.LocalDateTime

@Composable
fun TaskOverviewDueTime(
    taskDueLocalDateTime: LocalDateTime?,
    isDueTimeSet: Boolean?,
) {

    // Show task due time
    taskDueLocalDateTime?.let {
        Text(text = it.toString())
    }
    // Show task due time
    isDueTimeSet?.let {
        Text(text = it.toString())
    }
}