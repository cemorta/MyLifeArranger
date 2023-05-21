package com.example.mylifearranger.feature_planner.domain.use_case

import com.example.mylifearranger.feature_planner.domain.model.Event
import com.example.mylifearranger.feature_planner.domain.model.InvalidEventException
import com.example.mylifearranger.feature_planner.domain.repository.EventRepository

class AddEventUseCase(
    private val eventRepository: EventRepository
) {

    @Throws(InvalidEventException::class)
    suspend operator fun invoke(event: Event) {
        if(event.title.isBlank()) {
            throw InvalidEventException("The title of the event can't be empty.")
        }
        if(event.start.isAfter(event.end)) {
            throw InvalidEventException("The start time of the event can't be after the end time.")
        }
        if(event.start == event.end) {
            throw InvalidEventException("The start time of the event can't be the same as the end time.")
        }
        if(event.start == null) {
            throw InvalidEventException("The start time of the event can't be null.")
        }
        if(event.end == null) {
            throw InvalidEventException("The end time of the event can't be null.")
        }

        eventRepository.insertEvent(event)
    }
}