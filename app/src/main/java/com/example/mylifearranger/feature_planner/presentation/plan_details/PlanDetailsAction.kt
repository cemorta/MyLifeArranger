package com.example.mylifearranger.feature_planner.presentation.plan_details

import com.example.mylifearranger.feature_planner.domain.model.Plan

sealed class PlanDetailsAction {
    data class UpdatePlanCompletedAmount(val plan : Plan, val completedAmount: Int) : PlanDetailsAction()
}
