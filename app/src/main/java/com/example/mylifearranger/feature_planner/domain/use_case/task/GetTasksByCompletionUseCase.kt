package com.example.mylifearranger.feature_planner.domain.use_case.task

import com.example.mylifearranger.feature_planner.domain.model.Task
import com.example.mylifearranger.feature_planner.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasksByCompletionUseCase(
    private val taskRepository: TaskRepository
) {

    operator fun invoke(isCompleted: Boolean): Flow<List<Task>> {
        return taskRepository.getTasksByCompletion(isCompleted = isCompleted)
    }
}