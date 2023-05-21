package com.example.mylifearranger.feature_planner.presentation.events

import com.example.mylifearranger.feature_planner.domain.model.Event

sealed class EventsEvent {
    data class DeleteEvent(val event : Event) : EventsEvent()
}
