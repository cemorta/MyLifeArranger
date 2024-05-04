package com.example.mylifearranger.feature_planner.presentation.plan_details

import com.example.mylifearranger.feature_planner.data.data_source.models.PlanWithTasks
import com.example.mylifearranger.feature_planner.domain.model.Plan
import com.example.mylifearranger.feature_planner.domain.model.PlanTask

data class PlanDetailsState(
    var planWithTasks: PlanWithTasks? = null,
)
