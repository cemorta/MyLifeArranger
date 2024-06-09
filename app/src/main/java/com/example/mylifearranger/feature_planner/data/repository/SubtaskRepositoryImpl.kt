package com.example.mylifearranger.feature_planner.data.repository

import com.example.mylifearranger.feature_planner.data.data_source.dao.SubtaskDao
import com.example.mylifearranger.feature_planner.domain.model.Subtask
import com.example.mylifearranger.feature_planner.domain.repository.SubtaskRepository
import kotlinx.coroutines.flow.Flow

class SubtaskRepositoryImpl(
    private val subtaskDao: SubtaskDao
) : SubtaskRepository {
    override fun getSubtasksForTaskId(taskId: Int): Flow<List<Subtask>> {
        return subtaskDao.getSubtasksForTaskId(taskId)
    }

    override fun getSubtasksForEventId(eventId: Int): Flow<List<Subtask>> {
        return subtaskDao.getSubtasksForEventId(eventId)
    }

    override suspend fun insertSubtask(event: Subtask) {
        return subtaskDao.insertSubtask(event)
    }

    override suspend fun insertSubtasks(events: List<Subtask>) {
        return subtaskDao.insertSubtasks(events)
    }

    override suspend fun deleteSubtasks(events: List<Subtask>) {
        return subtaskDao.deleteSubtasks(events)
    }

    override suspend fun deleteSubtask(event: Subtask) {
        return subtaskDao.deleteSubtask(event)
    }
}