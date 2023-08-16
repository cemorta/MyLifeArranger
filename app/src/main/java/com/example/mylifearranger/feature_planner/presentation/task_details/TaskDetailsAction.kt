package com.example.mylifearranger.feature_planner.presentation.task_details

import com.example.mylifearranger.feature_planner.domain.model.Task

sealed class TaskDetailsAction {
    data class DeleteTask(val task: Task) : TaskDetailsAction()
}
