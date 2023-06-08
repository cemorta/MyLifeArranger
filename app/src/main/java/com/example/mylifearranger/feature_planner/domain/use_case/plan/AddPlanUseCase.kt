package com.example.mylifearranger.feature_planner.domain.use_case.plan

import com.example.mylifearranger.feature_planner.domain.model.Plan
import com.example.mylifearranger.feature_planner.domain.repository.PlanRepository

class AddPlanUseCase(
    private val planRepository: PlanRepository
) {

    suspend operator fun invoke(plan: Plan) {
        planRepository.insertPlan(plan)
    }
}