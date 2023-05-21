package com.example.mylifearranger.feature_planner.domain.repository

import com.example.mylifearranger.feature_planner.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface EventRepository {

    fun getEvents(): Flow<List<Event>>

    suspend fun getEventById(id: Int): Event?

    suspend fun insertEvent(event: Event)

    suspend fun deleteEvent(event: Event)
}