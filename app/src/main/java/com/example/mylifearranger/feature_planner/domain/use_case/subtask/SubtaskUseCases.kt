package com.example.mylifearranger.feature_planner.domain.use_case.subtask

data class SubtaskUseCases(
    val addSubtaskUseCase: AddSubtaskUseCase,
    val deleteSubtaskUseCase: DeleteSubtaskUseCase,
    val getSubtasksForEventIdUseCase: GetSubtasksForEventIdUseCase,
    val getSubtasksForTaskIdUseCase: GetSubtasksForTaskIdUseCase,
)
