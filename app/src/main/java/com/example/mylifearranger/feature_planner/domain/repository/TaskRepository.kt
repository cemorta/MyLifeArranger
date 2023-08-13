package com.example.mylifearranger.feature_planner.domain.repository

import com.example.mylifearranger.feature_planner.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTasks(): Flow<List<Task>>

    fun getNoneTasks(): Flow<List<Task>>

    suspend fun getTaskById(id: Int): Task?

    fun getYearlyTasksForYear(yearStart: Long, yearEnd: Long): Flow<List<Task>>

    fun getMonthlyTasksForMonth(monthStart: Long, monthEnd: Long): Flow<List<Task>>

    fun getTasksForDate(date: String): Flow<List<Task>>

    suspend fun insertTask(task: Task)

    suspend fun deleteTask(task: Task)
}