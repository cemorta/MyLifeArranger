package com.example.mylifearranger.feature_planner.domain.use_case

import com.example.mylifearranger.feature_planner.domain.model.Event
import com.example.mylifearranger.feature_planner.domain.repository.EventRepository

class DeleteEventUseCase(
    private val eventRepository: EventRepository
) {

    suspend operator fun invoke(event: Event) {
        eventRepository.deleteEvent(event)
    }
}