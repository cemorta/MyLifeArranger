package com.example.mylifearranger.feature_planner.domain.use_case.task

import com.example.mylifearranger.feature_planner.data.data_source.models.TaskWithSubtasks
import com.example.mylifearranger.feature_planner.domain.repository.TaskRepository

class GetTaskWithSubtasksUseCase(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(id: Int): TaskWithSubtasks {
        return taskRepository.getTaskByIdWithSubtasks(id)
    }
}