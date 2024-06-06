package com.example.mylifearranger.feature_planner.presentation.task_view

import com.example.mylifearranger.feature_planner.domain.util.TaskType

sealed class TaskViewAction {
    data class FilterTaskType(val taskType: TaskType, val date: String) : TaskViewAction()
    data class UpdateTaskCompletion(val id: Int, val isCompleted: Boolean) : TaskViewAction()
}
