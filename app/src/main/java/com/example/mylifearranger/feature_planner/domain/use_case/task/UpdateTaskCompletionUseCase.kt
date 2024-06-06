package com.example.mylifearranger.feature_planner.domain.use_case.task

import com.example.mylifearranger.feature_planner.domain.model.Task
import com.example.mylifearranger.feature_planner.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class UpdateTaskCompletionUseCase(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(id: Int, isCompleted: Boolean) {
        taskRepository.updateTaskCompletionById(id, isCompleted)
    }
}