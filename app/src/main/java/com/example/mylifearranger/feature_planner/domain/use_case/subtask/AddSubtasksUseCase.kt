package com.example.mylifearranger.feature_planner.domain.use_case.subtask

import com.example.mylifearranger.feature_planner.domain.model.Subtask
import com.example.mylifearranger.feature_planner.domain.repository.SubtaskRepository

class AddSubtasksUseCase(
    private val subtaskRepository: SubtaskRepository
) {

//    @Throws(InvalidSubtaskException::class)
    suspend operator fun invoke(subtask: List<Subtask>) {
//        if (subtask.title.isBlank()) {
//            throw InvalidSubtaskException("The title of the subtask can't be empty.")
//        }
//        if (subtask.startTimestamp > subtask.endTimestamp) {
//            throw InvalidSubtaskException("The start time of the subtask can't be after the end time.")
//        }
//        if (subtask.color == 0) {
//            throw InvalidSubtaskException("The color of the subtask can't be empty.")
//        }
//        if (subtask.start.hour == subtask.end) {
//            throw InvalidSubtaskException("The start time of the subtask can't be the same as the end time.")
//        }

        subtaskRepository.insertSubtasks(subtask)
    }
}