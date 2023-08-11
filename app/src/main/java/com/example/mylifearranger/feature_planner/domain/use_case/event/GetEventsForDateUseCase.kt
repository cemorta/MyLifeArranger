package com.example.mylifearranger.feature_planner.domain.use_case.event

import com.example.mylifearranger.feature_planner.domain.model.Event
import com.example.mylifearranger.feature_planner.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class GetEventsForDateUseCase(
    private val eventRepository: EventRepository
) {

    operator fun invoke(date: String): Flow<List<Event>> {
        return eventRepository.getEventsForDate(date)
    }
}