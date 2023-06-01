package com.example.mylifearranger.feature_planner.domain.use_case.task

import com.example.mylifearranger.feature_planner.domain.model.Task
import com.example.mylifearranger.feature_planner.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasksForDateUseCase(
    private val taskRepository: TaskRepository
) {

    operator fun invoke(date: String): Flow<List<Task>> {
        return taskRepository.getTasksForDate(date)
    }
}