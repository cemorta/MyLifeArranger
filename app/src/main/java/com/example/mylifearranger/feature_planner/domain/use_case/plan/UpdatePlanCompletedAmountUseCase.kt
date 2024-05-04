package com.example.mylifearranger.feature_planner.domain.use_case.plan

import com.example.mylifearranger.feature_planner.data.data_source.models.PlanWithTasks
import com.example.mylifearranger.feature_planner.domain.model.Plan
import com.example.mylifearranger.feature_planner.domain.model.PlanTask
import com.example.mylifearranger.feature_planner.domain.repository.PlanRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

class UpdatePlanCompletedAmountUseCase(
    private val planRepository: PlanRepository
) {

    suspend operator fun invoke(plan: Plan, completedAmount: Int) {
        planRepository.updatePlanCompletedAmount(plan, completedAmount)
    }
}
