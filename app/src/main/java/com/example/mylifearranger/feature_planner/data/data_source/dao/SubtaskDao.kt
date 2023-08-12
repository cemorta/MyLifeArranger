package com.example.mylifearranger.feature_planner.data.data_source.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mylifearranger.feature_planner.domain.model.Subtask
import kotlinx.coroutines.flow.Flow

@Dao
interface SubtaskDao {

    @Query("SELECT * FROM subtask WHERE assignedTaskId = :id")
    fun getSubtasksForTaskId(id: Int): Flow<List<Subtask>>

    @Query("SELECT * FROM subtask WHERE assignedEventId = :id")
    fun getSubtasksForEventId(id: Int): Flow<List<Subtask>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubtask(subtask: Subtask)

    @Delete
    suspend fun deleteSubtask(subtask: Subtask)
}