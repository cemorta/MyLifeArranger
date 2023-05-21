package com.example.mylifearranger.feature_planner.presentation.events

import com.example.mylifearranger.feature_planner.domain.model.Event

data class EventsState(
    val events: List<Event> = emptyList(),
)
