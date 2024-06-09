package com.example.mylifearranger.feature_planner.domain.repository

import com.example.mylifearranger.feature_planner.domain.model.Subtask
import kotlinx.coroutines.flow.Flow

interface SubtaskRepository {

    fun getSubtasksForTaskId(taskId: Int): Flow<List<Subtask>>

    fun getSubtasksForEventId(eventId: Int): Flow<List<Subtask>>

//    suspend fun getSubtaskById(id: Int): Subtask?

    suspend fun insertSubtask(event: Subtask)

    suspend fun insertSubtasks(events: List<Subtask>)

    suspend fun deleteSubtasks(events: List<Subtask>)

    suspend fun deleteSubtask(event: Subtask)
}