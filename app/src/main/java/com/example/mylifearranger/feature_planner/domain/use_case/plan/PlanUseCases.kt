package com.example.mylifearranger.feature_planner.domain.use_case.plan

data class PlanUseCases(
    val addPlanUseCase: AddPlanUseCase,
    val addPlanTaskUseCase: AddPlanTaskUseCase,
    val addPlanWithTasksUseCase: AddPlanWithTasksUseCase,
    val deletePlanUseCase: DeletePlanUseCase,
    val getPlanWithTasksUseCase: GetPlanWithTasksUseCase,
//    val getPlanUseCase: GetPlanUseCase,
//    val getPlansUseCase: GetPlansUseCase,
//    val updatePlanUseCase: UpdatePlanUseCase
)
