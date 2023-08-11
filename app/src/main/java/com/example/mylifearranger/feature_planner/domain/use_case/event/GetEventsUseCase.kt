package com.example.mylifearranger.feature_planner.domain.use_case

import com.example.mylifearranger.feature_planner.domain.model.Event
import com.example.mylifearranger.feature_planner.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow

class GetEventsUseCase(
    private val eventRepository: EventRepository
) {

    operator fun invoke(): Flow<List<Event>> {
        return eventRepository.getEvents()
    }
}