package com.example.mylifearranger.feature_planner.presentation.event_details

import com.example.mylifearranger.feature_planner.domain.model.Event

sealed class EventDetailsEvent {
    data class DeleteEvent(val event : Event) : EventDetailsEvent()
}
