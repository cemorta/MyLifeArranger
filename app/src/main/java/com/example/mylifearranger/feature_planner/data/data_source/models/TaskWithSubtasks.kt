package com.example.mylifearranger.feature_planner.data.data_source.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mylifearranger.feature_planner.domain.model.Subtask
import com.example.mylifearranger.feature_planner.domain.model.Task

data class TaskWithSubtasks(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "id",
        entityColumn = "assignedTaskId"
    )
    val subtasks: List<Subtask>
)
