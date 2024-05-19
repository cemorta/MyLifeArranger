package com.example.mylifearranger.feature_planner.domain.repository

import com.example.mylifearranger.feature_planner.domain.model.Plan
import com.example.mylifearranger.feature_planner.domain.model.PlanTask
import com.example.mylifearranger.feature_planner.data.data_source.models.PlanWithTasks
import kotlinx.coroutines.flow.Flow

interface PlanRepository {

    suspend fun getPlanWithTasks(planId: Int): PlanWithTasks
    fun getPlanTasksBetweenTwoDates(dateStart: Long, dateEnd: Long): Flow<List<PlanTask>>
    fun getPlans(): Flow<List<Plan>>

    suspend fun getPlanById(planId: Int): Plan?

//    suspend fun getEventById(id: Int): Event?
//
//    fun getEventsForDate(date: String): Flow<List<Event>>

    suspend fun insertPlan(plan: Plan)
    suspend fun insertPlanTask(planTask: PlanTask)
    suspend fun insertPlanWithTasks(plan: Plan, planTasks: List<PlanTask>)
    suspend fun deletePlan(plan: Plan)
    suspend fun updatePlanCompletedAmount(plan: Plan, completedAmount: Int)
    suspend fun updatePlanIsDone(plan: Plan, isDone: Boolean)
}