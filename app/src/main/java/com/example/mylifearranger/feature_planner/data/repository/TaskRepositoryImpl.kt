package com.example.mylifearranger.feature_planner.data.repository

import com.example.mylifearranger.feature_planner.data.data_source.dao.TaskDao
import com.example.mylifearranger.feature_planner.data.data_source.models.TaskWithSubtasks
import com.example.mylifearranger.feature_planner.domain.model.Task
import com.example.mylifearranger.feature_planner.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val taskDao: TaskDao
) : TaskRepository {

    override fun getTasks(): Flow<List<Task>> {
        return taskDao.getTasks()
    }

    override suspend fun getTaskByIdWithSubtasks(id: Int): TaskWithSubtasks {
        return taskDao.getTaskByIdWithSubtasks(id)
    }

    override fun getYearlyTasksForYear(yearStart: Long, yearEnd: Long): Flow<List<Task>> {
        return taskDao.getYearlyTasksForYear(yearStart, yearEnd)
    }

    override fun getMonthlyTasksForMonth(monthStart: Long, monthEnd: Long): Flow<List<Task>> {
        return taskDao.getMonthlyTasksForMonth(monthStart, monthEnd)
    }

    override fun getNoneTasks(): Flow<List<Task>> {
        return taskDao.getNoneTasks()
    }

    override fun getTasksForDate(date: String): Flow<List<Task>> {
        return taskDao.getTasksForDate(date)
    }

    override suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    override suspend fun updateTaskCompletionById(id: Int, isCompleted: Boolean) {
        taskDao.updateTaskCompletionById(id, isCompleted)
    }
}