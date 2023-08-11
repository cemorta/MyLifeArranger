package com.example.mylifearranger.feature_planner.domain.use_case

import com.example.mylifearranger.feature_planner.domain.model.Event
import com.example.mylifearranger.feature_planner.domain.model.InvalidEventException
import com.example.mylifearranger.feature_planner.domain.repository.EventRepository

class AddEventUseCase(
    private val eventRepository: EventRepository
) {

    @Throws(InvalidEventException::class)
    suspend operator fun invoke(event: Event) {
        if (event.title.isBlank()) {
            throw InvalidEventException("The title of the event can't be empty.")
        }
        if (event.startTimestamp > event.endTimestamp) {
            throw InvalidEventException("The start time of the event can't be after the end time.")
        }
        if (event.color == 0) {
            throw InvalidEventException("The color of the event can't be empty.")
        }
//        if (event.start.hour == event.end) {
//            throw InvalidEventException("The start time of the event can't be the same as the end time.")
//        }

        eventRepository.insertEvent(event)
    }
}