package com.example.mylifearranger.feature_planner.domain.use_case.task

import com.example.mylifearranger.feature_planner.domain.model.Task
import com.example.mylifearranger.feature_planner.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasksUseCase(
    private val taskRepository: TaskRepository
) {

    operator fun invoke(): Flow<List<Task>> {
        return taskRepository.getTasks()
    }
}