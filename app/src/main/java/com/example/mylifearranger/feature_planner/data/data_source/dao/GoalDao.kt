package com.example.mylifearranger.feature_planner.data.data_source.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mylifearranger.feature_planner.domain.model.Goal
import kotlinx.coroutines.flow.Flow

@Dao
interface GoalDao {

    @Query("SELECT * FROM goal")
    fun getGoals(): Flow<List<Goal>>

    @Query("SELECT * FROM goal WHERE id = :id")
    suspend fun getGoalById(id: Int): Goal?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: Goal)

    @Delete
    suspend fun deleteGoal(goal: Goal)
}