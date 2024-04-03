package com.example.mylifearranger.feature_planner.data.data_source.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.mylifearranger.feature_planner.domain.model.Plan
import com.example.mylifearranger.feature_planner.domain.model.PlanTask
import com.example.mylifearranger.feature_planner.data.data_source.models.PlanWithTasks
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanDao {
    @Transaction
    @Query("SELECT * FROM plan WHERE id = :planId")
    fun getPlanWithTasks(planId: Int): Flow<PlanWithTasks>

    @Transaction
    @Query("SELECT * FROM PlanTask WHERE performedDateTimestamp >= :dateStart AND performedDateTimestamp < :dateEnd")
    fun getPlanTasksBetweenTwoDates(dateStart: Long, dateEnd: Long): Flow<List<PlanTask>>

    @Query("SELECT * FROM plan")
    fun getPlans(): Flow<List<Plan>>

    @Query("SELECT * FROM plan WHERE id = :id")
    suspend fun getPlanById(id: Int): Plan?

//    @Query("SELECT * FROM task WHERE id = :id")
//    suspend fun getTaskById(id: Int): Task?
//
//    @Query("SELECT * FROM task WHERE taskType = 'YEARLY' AND plannedTimestamp >= :yearStart AND plannedTimestamp < :yearEnd")
//    fun getYearlyTasksForYear(yearStart: Long, yearEnd: Long): Flow<List<Task>>
//
//    @Query("SELECT * FROM task WHERE taskType = 'MONTHLY' AND plannedTimestamp >= :monthStart AND plannedTimestamp < :monthEnd")
//    fun getMonthlyTasksForMonth(monthStart: Long, monthEnd: Long): Flow<List<Task>>
//
//    @Query("SELECT * FROM task WHERE taskType = 'DAILY' AND date(plannedTimestamp / 1000, 'unixepoch') = :date")
//    fun getTasksForDate(date: String): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlan(plan: Plan) : Long

    @Insert
    suspend fun insertPlanTask(planTask: PlanTask)

    @Transaction
    suspend fun insertPlanWithTasks(plan: Plan, planTasks: List<PlanTask>) {
        val planId = insertPlan(plan)
        val tasksWithPlanId = planTasks.map { it.copy(assignedPlanId = planId.toInt()) }
        tasksWithPlanId.forEach { insertPlanTask(it) }
    }

    @Delete
    suspend fun deletePlan(plan: Plan)
}