package com.example.mylifearranger.feature_planner.domain.use_case.goal

import com.example.mylifearranger.feature_planner.domain.model.Goal
import com.example.mylifearranger.feature_planner.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow

class GetGoalUseCase(
    private val goalRepository: GoalRepository
) {

    suspend operator fun invoke(id: Int): Goal? {
        return goalRepository.getGoalById(id)
    }
}