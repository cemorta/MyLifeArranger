package com.example.mylifearranger.feature_planner.presentation.task_overview.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.mylifearranger.feature_planner.domain.model.Subtask
import com.example.mylifearranger.feature_planner.domain.util.TaskType
import com.example.mylifearranger.feature_planner.presentation.task_overview.TaskOverviewViewModel
import java.time.LocalDateTime

@Composable
fun TaskOverviewContent(
    taskTitle: String?,
    durationHour: String?,
    durationMinute: String?,
    taskType: TaskType?,
    taskPlannedLocalDateTime: LocalDateTime?,
    taskDueLocalDateTime: LocalDateTime?,
    isDone: Boolean?,
    isDueTimeSet: Boolean?,
    isPlannedTimeSet: Boolean?,
    subtasks: List<Subtask>,
    viewModel: TaskOverviewViewModel
) {

    Column {
        TaskDetailsCard(taskDurationHour = durationHour, taskDurationMinute = durationMinute, dueDateTime = taskDueLocalDateTime, plannedDateTime = taskPlannedLocalDateTime)
        TaskSubtasksCard(subtasks, viewModel)
    }


}

