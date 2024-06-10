package com.example.mylifearranger.feature_planner.presentation.plan_details

import com.example.mylifearranger.feature_planner.domain.model.Plan
import com.example.mylifearranger.feature_planner.domain.model.PlanTask
import java.time.LocalDateTime
import java.time.LocalTime

sealed class PlanDetailsAction {
    data class UpdatePlanCompletedAmount(val plan : Plan, val completedAmount: Int) : PlanDetailsAction()
    data class SchedulePlanTask(val planTask: PlanTask, val startTime: LocalTime, val endTime: LocalTime) : PlanDetailsAction()
}
