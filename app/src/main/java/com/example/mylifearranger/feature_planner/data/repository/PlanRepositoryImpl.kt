package com.example.mylifearranger.feature_planner.data.repository

import com.example.mylifearranger.feature_planner.data.data_source.PlanDao
import com.example.mylifearranger.feature_planner.domain.model.Plan
import com.example.mylifearranger.feature_planner.domain.model.PlanTask
import com.example.mylifearranger.feature_planner.domain.model.PlanWithTasks
import com.example.mylifearranger.feature_planner.domain.repository.PlanRepository
import kotlinx.coroutines.flow.Flow

class PlanRepositoryImpl(
    private val planDao: PlanDao
) : PlanRepository {

    override fun getPlanWithTasks(planId: Int): Flow<PlanWithTasks> {
        return planDao.getPlanWithTasks(planId)
    }

    override suspend fun insertPlan(plan: Plan): Int {
        return planDao.insertPlan(plan)
    }

    override suspend fun insertPlanTask(planTask: PlanTask) {
        return planDao.insertPlanTask(planTask)
    }

    override suspend fun insertPlanWithTasks(plan: Plan, planTasks: List<PlanTask>) {
        return planDao.insertPlanWithTasks(plan, planTasks)
    }

    override suspend fun deletePlan(plan: Plan) {
        planDao.deletePlan(plan)
    }
}