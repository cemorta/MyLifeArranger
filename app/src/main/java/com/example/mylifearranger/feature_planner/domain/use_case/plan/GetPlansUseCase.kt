package com.example.mylifearranger.feature_planner.domain.use_case.plan

import com.example.mylifearranger.feature_planner.domain.model.Plan
import com.example.mylifearranger.feature_planner.domain.repository.PlanRepository
import kotlinx.coroutines.flow.Flow

class GetPlansUseCase(
    private val planRepository: PlanRepository
) {

    operator fun invoke(): Flow<List<Plan>> {
        return planRepository.getPlans()
    }
}