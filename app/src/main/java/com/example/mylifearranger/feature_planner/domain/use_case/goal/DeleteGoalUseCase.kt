package com.example.mylifearranger.feature_planner.domain.use_case.goal

import com.example.mylifearranger.feature_planner.domain.model.Goal
import com.example.mylifearranger.feature_planner.domain.repository.GoalRepository

class DeleteGoalUseCase(
    private val goalRepository: GoalRepository
) {

    suspend operator fun invoke(goal: Goal) {
        goalRepository.deleteGoal(goal)
    }
}