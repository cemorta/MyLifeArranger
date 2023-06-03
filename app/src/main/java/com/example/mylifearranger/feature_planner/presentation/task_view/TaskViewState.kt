package com.example.mylifearranger.feature_planner.presentation.task_view

import com.example.mylifearranger.feature_planner.domain.model.Task
import com.example.mylifearranger.feature_planner.domain.util.TaskType

data class TaskViewState(
    val tasks: List<Task> = emptyList(),
    var taskType: TaskType = TaskType.DAILY,
    var date: String? = null,
)
