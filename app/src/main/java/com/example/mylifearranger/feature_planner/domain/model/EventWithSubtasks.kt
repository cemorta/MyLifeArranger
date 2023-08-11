package com.example.mylifearranger.feature_planner.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class EventWithSubtasks(
    @Embedded val event: Event,
    @Relation(
        parentColumn = "id",
        entityColumn = "assignedEventId"
    )
    val subtasks: List<Subtask>
)
