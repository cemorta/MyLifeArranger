package com.example.mylifearranger.feature_planner.domain.use_case.plan

import com.example.mylifearranger.feature_planner.data.data_source.models.PlanWithTasks
import com.example.mylifearranger.feature_planner.domain.repository.PlanRepository
import kotlinx.coroutines.flow.Flow

class GetPlanWithTasksUseCase(
    private val planRepository: PlanRepository
) {

    operator fun invoke(planId: Int): Flow<PlanWithTasks> {
        return planRepository.getPlanWithTasks(planId)
    }
}