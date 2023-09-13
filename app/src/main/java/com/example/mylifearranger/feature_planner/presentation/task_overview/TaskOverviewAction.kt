package com.example.mylifearranger.feature_planner.presentation.task_overview

import com.example.mylifearranger.feature_planner.domain.util.TaskType

sealed class TaskOverviewAction {
    object MarkAsCompleted : TaskOverviewAction()
    object MarkAsIncomplete : TaskOverviewAction()
    object DeleteTask: TaskOverviewAction()
}
