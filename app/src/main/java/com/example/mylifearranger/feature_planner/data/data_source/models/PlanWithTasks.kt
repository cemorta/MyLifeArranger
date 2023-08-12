package com.example.mylifearranger.feature_planner.data.data_source.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mylifearranger.feature_planner.domain.model.Plan
import com.example.mylifearranger.feature_planner.domain.model.PlanTask

data class PlanWithTasks(
    @Embedded val plan: Plan,
    @Relation(
        parentColumn = "id",
        entityColumn = "assignedPlanId"
    )
    val tasks: List<PlanTask>
)
