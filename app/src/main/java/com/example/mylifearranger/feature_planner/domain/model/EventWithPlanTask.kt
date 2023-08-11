package com.example.mylifearranger.feature_planner.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class EventWithPlanTask(
    @Embedded val event: Event,
    @Relation(
        parentColumn = "assignedPlanTaskId",
        entityColumn = "id"
    )
    val planTask: PlanTask?
)
