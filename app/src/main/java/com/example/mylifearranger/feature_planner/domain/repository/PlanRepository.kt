package com.example.mylifearranger.feature_planner.domain.repository

import com.example.mylifearranger.feature_planner.domain.model.Plan
import com.example.mylifearranger.feature_planner.domain.model.PlanTask
import com.example.mylifearranger.feature_planner.domain.model.PlanWithTasks
import kotlinx.coroutines.flow.Flow

interface PlanRepository {

    fun getPlanWithTasks(planId: Int): Flow<PlanWithTasks>

//    suspend fun getEventById(id: Int): Event?
//
//    fun getEventsForDate(date: String): Flow<List<Event>>

    suspend fun insertPlan(plan: Plan) : Long
    suspend fun insertPlanTask(planTask: PlanTask)
    suspend fun insertPlanWithTasks(plan: Plan, planTasks: List<PlanTask>)
    suspend fun deletePlan(plan: Plan)
}