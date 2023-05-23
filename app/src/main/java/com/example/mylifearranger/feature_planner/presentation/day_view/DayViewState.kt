package com.example.mylifearranger.feature_planner.presentation.day_view

import com.example.mylifearranger.feature_planner.domain.model.Event

data class DayViewState(
    val events: List<Event> = emptyList(),
)
