package com.example.mylifearranger.feature_planner.domain.use_case

import com.example.mylifearranger.feature_planner.domain.model.Event
import com.example.mylifearranger.feature_planner.domain.repository.EventRepository
import kotlinx.coroutines.flow.Flow

class GetEventUseCase(
    private val eventRepository: EventRepository
) {

    suspend operator fun invoke(id: Int): Event? {
        return eventRepository.getEventById(id)
    }
}