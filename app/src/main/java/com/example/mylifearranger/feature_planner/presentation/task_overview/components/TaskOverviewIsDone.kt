package com.example.mylifearranger.feature_planner.presentation.task_overview.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TaskOverviewIsDone(
    isDone: Boolean?,
) {

    // Show task is done
    isDone?.let {
        Text(text = it.toString())
    }
}
