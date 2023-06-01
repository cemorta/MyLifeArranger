package com.example.mylifearranger.feature_planner.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mylifearranger.feature_planner.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getTasks(): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTaskById(id: Int): Task?

    @Query("SELECT * FROM task WHERE taskType = 'YEARLY' AND plannedTimestamp >= :yearStart AND plannedTimestamp < :yearEnd")
    fun getYearlyTasksForYear(yearStart: Long, yearEnd: Long): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE taskType = 'MONTHLY' AND plannedTimestamp >= :monthStart AND plannedTimestamp < :monthEnd")
    fun getMonthlyTasksForMonth(monthStart: Long, monthEnd: Long): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE taskType = 'DAILY' AND date(plannedTimestamp / 1000, 'unixepoch') = :date")
    fun getTasksForDate(date: String): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}