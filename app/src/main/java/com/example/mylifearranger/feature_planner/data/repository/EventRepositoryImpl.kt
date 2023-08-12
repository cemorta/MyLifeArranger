package com.example.mylifearranger.feature_planner.data.repository

import com.example.mylifearranger.feature_planner.data.data_source.dao.EventDao
import com.example.mylifearranger.feature_planner.domain.model.Event
import com.example.mylifearranger.feature_planner.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow

class EventRepositoryImpl(
    private val eventDao: EventDao
) : EventRepository {

    override fun getEvents(): Flow<List<Event>> {
        return eventDao.getEvents()
    }

    override suspend fun getEventById(id: Int): Event? {
        return eventDao.getEventById(id)
    }

    override fun getEventsForDate(date: String): Flow<List<Event>> {
        return eventDao.getEventsForDate(date)
    }

    override suspend fun insertEvent(event: Event) {
        eventDao.insertEvent(event)
    }

    override suspend fun deleteEvent(event: Event) {
        eventDao.deleteEvent(event)
    }
}