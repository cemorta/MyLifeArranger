package com.example.mylifearranger.feature_planner.domain.use_case.task

data class TaskUseCases(
    val getTasksUseCase: GetTasksUseCase,
    val deleteTaskUseCase: DeleteTaskUseCase,
    val addTaskUseCase: AddTaskUseCase,
    val getTaskUseCase: GetTaskWithSubtasksUseCase,
    val getYearlyTasksForYearUseCase: GetYearlyTasksForYearUseCase,
    val getMonthlyTasksForMonthUseCase: GetMonthlyTasksForMonthUseCase,
    val getNoneTasksUseCase: GetNoneTasksUseCase,
    val getDailyTasksForDateUseCase: GetDailyTasksForDateUseCase,
    val updateTaskCompletionUseCase: UpdateTaskCompletionUseCase
)
