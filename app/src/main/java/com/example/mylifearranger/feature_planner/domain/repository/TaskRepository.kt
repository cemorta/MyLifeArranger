package com.example.mylifearranger.feature_planner.domain.repository

import com.example.mylifearranger.feature_planner.data.data_source.models.TaskWithSubtasks
import com.example.mylifearranger.feature_planner.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    fun getTasks(): Flow<List<Task>>

    fun getTasksByCompletion(isCompleted: Boolean): Flow<List<Task>>

    fun getNoneTasks(): Flow<List<Task>>

    suspend fun getTaskByIdWithSubtasks(id: Int): TaskWithSubtasks

    fun getYearlyTasksForYear(yearStart: Long, yearEnd: Long): Flow<List<Task>>

    fun getMonthlyTasksForMonth(monthStart: Long, monthEnd: Long): Flow<List<Task>>

    fun getTasksForDate(date: String): Flow<List<Task>>

    suspend fun insertTask(task: Task)

    suspend fun deleteTask(task: Task)
    suspend fun updateTaskCompletionById(id: Int, isCompleted: Boolean)
}