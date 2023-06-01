package com.example.mylifearranger.feature_planner.domain.use_case.task

import com.example.mylifearranger.feature_planner.domain.model.Task
import com.example.mylifearranger.feature_planner.domain.repository.TaskRepository

class DeleteTaskUseCase(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(task: Task) {
        taskRepository.deleteTask(task)
    }
}