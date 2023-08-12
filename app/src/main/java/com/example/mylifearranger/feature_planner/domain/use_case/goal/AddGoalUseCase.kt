package com.example.mylifearranger.feature_planner.domain.use_case.goal

import com.example.mylifearranger.feature_planner.domain.model.Goal
import com.example.mylifearranger.feature_planner.domain.repository.GoalRepository

class AddGoalUseCase(
    private val goalRepository: GoalRepository
) {

//    @Throws(InvalidGoalException::class)
    suspend operator fun invoke(goal: Goal) {
//        if (goal.title.isBlank()) {
//            throw InvalidGoalException("The title of the goal can't be empty.")
//        }
//        if (goal.startTimestamp > goal.endTimestamp) {
//            throw InvalidGoalException("The start time of the goal can't be after the end time.")
//        }
//        if (goal.color == 0) {
//            throw InvalidGoalException("The color of the goal can't be empty.")
//        }
//        if (goal.start.hour == goal.end) {
//            throw InvalidGoalException("The start time of the goal can't be the same as the end time.")
//        }

        goalRepository.insertGoal(goal)
    }
}