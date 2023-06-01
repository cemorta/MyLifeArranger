package com.example.mylifearranger.feature_planner.domain.use_case.task

import com.example.mylifearranger.feature_planner.domain.model.Task
import com.example.mylifearranger.feature_planner.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetYearlyTasksForYearUseCase(
    private val taskRepository: TaskRepository
) {

    operator fun invoke(yearStart: Long, yearEnd: Long): Flow<List<Task>> {
        return taskRepository.getYearlyTasksForYear(yearStart, yearEnd)
    }
}