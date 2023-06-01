package com.example.mylifearranger.feature_planner.domain.repository

import com.example.mylifearranger.feature_planner.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTasks(): Flow<List<Task>>

    suspend fun getTaskById(id: Int): Task?

    fun getTasksForDate(date: String): Flow<List<Task>>

    suspend fun insertTask(task: Task)

    suspend fun deleteTask(task: Task)
}