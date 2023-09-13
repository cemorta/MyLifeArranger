package com.example.mylifearranger.feature_planner.presentation.task_overview.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.mylifearranger.feature_planner.domain.util.TaskType
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
) {

    Column {
        TaskOverviewTitle(taskTitle = taskTitle)
        TaskOverviewDuration(durationHour = durationHour, durationMinute = durationMinute)
        TaskOverviewType(taskType = taskType)
        TaskOverviewPlannedTime(
            taskPlannedLocalDateTime = taskPlannedLocalDateTime,
            isPlannedTimeSet = isPlannedTimeSet
        )
        TaskOverviewDueTime(
            taskDueLocalDateTime = taskDueLocalDateTime,
            isDueTimeSet = isDueTimeSet
        )
        TaskOverviewIsDone(isDone = isDone)
    }


}

