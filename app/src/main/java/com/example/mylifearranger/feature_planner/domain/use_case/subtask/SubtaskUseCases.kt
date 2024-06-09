package com.example.mylifearranger.feature_planner.domain.use_case.subtask

data class SubtaskUseCases(
    val addSubtaskUseCase: AddSubtaskUseCase,
    val addSubtasksUseCase: AddSubtasksUseCase,
    val deleteSubtaskUseCase: DeleteSubtaskUseCase,
    val deleteSubtasksUseCase: DeleteSubtasksUseCase,
    val getSubtasksForEventIdUseCase: GetSubtasksForEventIdUseCase,
    val getSubtasksForTaskIdUseCase: GetSubtasksForTaskIdUseCase,
)
