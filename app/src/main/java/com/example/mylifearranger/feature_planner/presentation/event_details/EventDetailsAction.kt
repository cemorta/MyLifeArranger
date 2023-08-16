package com.example.mylifearranger.feature_planner.presentation.event_details

import com.example.mylifearranger.feature_planner.domain.model.Event

sealed class EventDetailsAction {
    data class DeleteEvent(val event : Event) : EventDetailsAction()
}
