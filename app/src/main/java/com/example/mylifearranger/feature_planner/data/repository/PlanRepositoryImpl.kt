package com.example.mylifearranger.feature_planner.data.repository

import com.example.mylifearranger.feature_planner.data.data_source.dao.PlanDao
import com.example.mylifearranger.feature_planner.domain.model.Plan
import com.example.mylifearranger.feature_planner.domain.model.PlanTask
import com.example.mylifearranger.feature_planner.data.data_source.models.PlanWithTasks
import com.example.mylifearranger.feature_planner.domain.repository.PlanRepository
import kotlinx.coroutines.flow.Flow

class PlanRepositoryImpl(
    private val planDao: PlanDao
) : PlanRepository {

    override suspend fun getPlanWithTasks(planId: Int): PlanWithTasks {
        return planDao.getPlanWithTasks(planId)
    }

    override fun getPlanTasksBetweenTwoDates(dateStart: Long, dateEnd: Long): Flow<List<PlanTask>> {
        return planDao.getPlanTasksBetweenTwoDates(dateStart, dateEnd)
    }

    override fun getPlans(): Flow<List<Plan>> {
        return planDao.getPlans()
    }

    override suspend fun getPlanById(planId: Int): Plan? {
        return planDao.getPlanById(planId)
    }

    override suspend fun insertPlan(plan: Plan) {
        planDao.insertPlan(plan)
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

    override suspend fun updatePlanCompletedAmount(plan: Plan, completedAmount: Int) {
        var amount = completedAmount
        // Need to get planTasks and update completedAmount for each task
        val planWithTasks = planDao.getPlanWithTasks(plan.id!!)
        // Sort the planTasks by plannedTimestamp to get the tasks in the correct order i.e. the earliest task first
        val sortedPlanTasks = planWithTasks.tasks.sortedBy { it.performedDateTimestamp }
        // Make the planTasks mutable so that we can update the isDone for each task
        val mutablePlanTasks = sortedPlanTasks.toMutableList()
        // Update the completedAmount for each task
        for (task in mutablePlanTasks) {
            if (amount >= task.amountToComplete) {
                // Update the task as completed
                task.isDone = true
                // Update the amount left to complete
                amount -= task.amountToComplete
            } else {
                // Update the task as not completed
                task.isDone = false
                break
            }
        }

        // Update the planTasks in the database
        for (task in mutablePlanTasks) {
            planDao.insertPlanTask(task)
        }
        // Update the completedAmount for the plan
        planDao.updatePlanCompletedAmount(plan.id, completedAmount)
    }
}