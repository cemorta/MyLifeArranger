package com.example.mylifearranger.feature_planner.domain.use_case.goal

import com.example.mylifearranger.feature_planner.domain.model.Goal
import com.example.mylifearranger.feature_planner.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow

class GetGoalsUseCase(
    private val goalRepository: GoalRepository
) {

    operator fun invoke(): Flow<List<Goal>> {
        return goalRepository.getGoals()
    }
}