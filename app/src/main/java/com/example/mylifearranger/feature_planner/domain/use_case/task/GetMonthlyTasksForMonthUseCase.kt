package com.example.mylifearranger.feature_planner.domain.use_case.task

import com.example.mylifearranger.feature_planner.domain.model.Task
import com.example.mylifearranger.feature_planner.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetMonthlyTasksForMonthUseCase(
    private val taskRepository: TaskRepository
) {

    operator fun invoke(monthStart: Long, monthEnd: Long): Flow<List<Task>> {
        return taskRepository.getMonthlyTasksForMonth(monthStart, monthEnd)
    }
}