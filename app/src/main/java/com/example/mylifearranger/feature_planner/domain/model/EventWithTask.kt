package com.example.mylifearranger.feature_planner.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class EventWithTask(
    @Embedded val event: Event,
    @Relation(
        parentColumn = "assignedTaskId",
        entityColumn = "id"
    )
    val task: Task?
)
