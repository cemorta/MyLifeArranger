package com.example.mylifearranger.feature_planner.domain.use_case.plan

import com.example.mylifearranger.feature_planner.domain.model.Plan
import com.example.mylifearranger.feature_planner.domain.repository.PlanRepository

class GetPlanUseCase(
    private val planRepository: PlanRepository
) {

    suspend operator fun invoke(id: Int): Plan? {
        return planRepository.getPlanById(id)
    }
}