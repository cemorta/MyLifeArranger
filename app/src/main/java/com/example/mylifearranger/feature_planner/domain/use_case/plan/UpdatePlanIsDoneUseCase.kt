package com.example.mylifearranger.feature_planner.domain.use_case.plan

import com.example.mylifearranger.feature_planner.domain.model.Plan
import com.example.mylifearranger.feature_planner.domain.repository.PlanRepository

class UpdatePlanIsDoneUseCase(
    private val planRepository: PlanRepository
) {

    suspend operator fun invoke(plan: Plan, boolean: Boolean) {
        planRepository.updatePlanIsDone(plan, boolean)
    }
}
