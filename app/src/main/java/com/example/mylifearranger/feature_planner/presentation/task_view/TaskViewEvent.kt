package com.example.mylifearranger.feature_planner.presentation.task_view

import com.example.mylifearranger.feature_planner.domain.model.Event
import com.example.mylifearranger.feature_planner.domain.util.TaskType

sealed class TaskViewEvent {
    data class FilterTaskType(val taskType: TaskType, val date: String) : TaskViewEvent()
}
