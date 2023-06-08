package com.example.mylifearranger.feature_planner.domain.model

import androidx.room.Embedded
import androidx.room.Relation

data class PlanWithTasks(
    @Embedded val plan: Plan,
    @Relation(
        parentColumn = "id",
        entityColumn = "planId"
    )
    val tasks: List<PlanTask>
)
