package com.example.mylifearranger.feature_planner.presentation.task_overview.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TaskOverviewTitle(taskTitle: String?) {

    // Show task title
    taskTitle?.let {
        Text(text = it)
    }
}
