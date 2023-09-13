package com.example.mylifearranger.feature_planner.presentation.task_overview.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.mylifearranger.feature_planner.domain.util.TaskType

@Composable
fun TaskOverviewType(
    taskType: TaskType?,
) {

    // Show task type
    taskType?.let {
        Text(text = it.name)
    }
}
