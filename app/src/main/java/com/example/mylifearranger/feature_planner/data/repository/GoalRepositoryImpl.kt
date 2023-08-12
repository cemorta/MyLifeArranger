package com.example.mylifearranger.feature_planner.data.repository

import com.example.mylifearranger.feature_planner.data.data_source.dao.GoalDao
import com.example.mylifearranger.feature_planner.domain.model.Goal
import com.example.mylifearranger.feature_planner.domain.repository.GoalRepository
import kotlinx.coroutines.flow.Flow

class GoalRepositoryImpl(
    private val goalDao: GoalDao
) : GoalRepository {
    override fun getGoals(): Flow<List<Goal>> {
        return goalDao.getGoals()
    }

    override suspend fun getGoalById(id: Int): Goal? {
        return goalDao.getGoalById(id)
    }

    override suspend fun insertGoal(goal: Goal) {
        goalDao.insertGoal(goal)
    }

    override suspend fun deleteGoal(goal: Goal) {
        goalDao.deleteGoal(goal)
    }

}