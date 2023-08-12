package com.example.mylifearranger.feature_planner.domain.use_case.subtask

import com.example.mylifearranger.feature_planner.domain.model.Subtask
import com.example.mylifearranger.feature_planner.domain.repository.SubtaskRepository

class DeleteSubtaskUseCase(
    private val subtaskRepository: SubtaskRepository
) {

    suspend operator fun invoke(subtask: Subtask) {
        subtaskRepository.deleteSubtask(subtask)
    }
}