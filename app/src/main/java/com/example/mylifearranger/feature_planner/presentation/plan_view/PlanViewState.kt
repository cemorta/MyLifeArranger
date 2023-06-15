package com.example.mylifearranger.feature_planner.presentation.plan_view

import com.example.mylifearranger.feature_planner.domain.model.Plan

data class PlanViewState(
    val plans: List<Plan> = emptyList(),
)
