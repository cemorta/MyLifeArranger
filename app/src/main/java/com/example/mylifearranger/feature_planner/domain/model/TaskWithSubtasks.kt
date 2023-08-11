package com.example.mylifearranger.feature_planner.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class TaskWithSubtasks(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "id",
        entityColumn = "assignedTaskId"
    )
    val subtasks: List<Subtask>
)
