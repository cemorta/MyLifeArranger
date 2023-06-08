package com.example.mylifearranger.feature_planner.domain.use_case.plan

import com.example.mylifearranger.feature_planner.domain.model.PlanTask
import com.example.mylifearranger.feature_planner.domain.repository.PlanRepository

class AddPlanTaskUseCase(
    private val planRepository: PlanRepository
) {

    suspend operator fun invoke(planTask: PlanTask) {
        planRepository.insertPlanTask(planTask)
    }
}