package com.example.mylifearranger.feature_planner.domain.use_case.subtask

import com.example.mylifearranger.feature_planner.domain.model.Subtask
import com.example.mylifearranger.feature_planner.domain.repository.SubtaskRepository
import kotlinx.coroutines.flow.Flow

class GetSubtasksForTaskIdUseCase(
    private val subtaskRepository: SubtaskRepository
) {

    operator fun invoke(taskId: Int): Flow<List<Subtask>> {
        return subtaskRepository.getSubtasksForTaskId(taskId)
    }
}