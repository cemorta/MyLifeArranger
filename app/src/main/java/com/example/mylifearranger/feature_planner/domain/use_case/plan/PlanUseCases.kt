package com.example.mylifearranger.feature_planner.domain.use_case.plan

data class PlanUseCases(
    val addPlanUseCase: AddPlanUseCase,
    val addPlanTaskUseCase: AddPlanTaskUseCase,
    val addPlanWithTasksUseCase: AddPlanWithTasksUseCase,
    val deletePlanUseCase: DeletePlanUseCase,
    val getPlanUseCase: GetPlanUseCase,
    val getPlansUseCase: GetPlansUseCase,
    val getPlanWithTasksUseCase: GetPlanWithTasksUseCase,
    val getPlanTasksBetweenTwoDatesUseCase: GetPlanTasksBetweenTwoDatesUseCase,
    val updatePlanCompletedAmountUseCase: UpdatePlanCompletedAmountUseCase,
//    val getPlanUseCase: GetPlanUseCase,
//    val getPlansUseCase: GetPlansUseCase,
//    val updatePlanUseCase: UpdatePlanUseCase
)
