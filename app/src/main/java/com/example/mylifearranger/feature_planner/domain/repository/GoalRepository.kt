package com.example.mylifearranger.feature_planner.domain.repository

import com.example.mylifearranger.feature_planner.domain.model.Goal
import kotlinx.coroutines.flow.Flow

interface GoalRepository {

    fun getGoals(): Flow<List<Goal>>

    suspend fun getGoalById(id: Int): Goal?

    suspend fun insertGoal(goal: Goal)

    suspend fun deleteGoal(goal: Goal)
}