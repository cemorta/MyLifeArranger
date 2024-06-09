package com.example.mylifearranger.feature_planner.presentation.task_overview

import com.example.mylifearranger.feature_planner.domain.util.TaskType

sealed class TaskOverviewAction {
    data class AddNewSubtask(val title: String, val subtaskId: Int) : TaskOverviewAction()
    data class UpdateSubtaskTitle(val subtaskId: Int, val title: String) : TaskOverviewAction()
    data class UpdateSubtaskStatus(val subtaskId: Int, val isDone: Boolean) : TaskOverviewAction()
    data class RemoveSubtask(val subtaskId: Int) : TaskOverviewAction()
}
