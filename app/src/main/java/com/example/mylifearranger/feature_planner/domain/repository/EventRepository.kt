package com.example.mylifearranger.feature_planner.domain.repository

import com.example.mylifearranger.feature_planner.domain.model.Event
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface EventRepository {

    fun getEvents(): Flow<List<Event>>

    suspend fun getEventById(id: Int): Event?

    fun getEventsForDate(date: String): Flow<List<Event>>

    suspend fun insertEvent(event: Event)

    suspend fun deleteEvent(event: Event)
}