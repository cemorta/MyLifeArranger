package com.example.mylifearranger.feature_planner.domain.use_case.plan

import com.example.mylifearranger.feature_planner.data.data_source.models.PlanWithTasks
import com.example.mylifearranger.feature_planner.domain.model.PlanTask
import com.example.mylifearranger.feature_planner.domain.repository.PlanRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow

class GetPlanTasksBetweenTwoDatesUseCase(
    private val planRepository: PlanRepository
) {

    operator fun invoke(dateStart: Long, dateEnd: Long): Flow<List<PlanTask>> {
        return planRepository.getPlanTasksBetweenTwoDates(dateStart, dateEnd)
    }
}