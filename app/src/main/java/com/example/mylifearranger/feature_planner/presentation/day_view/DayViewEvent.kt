package com.example.mylifearranger.feature_planner.presentation.day_view

import com.example.mylifearranger.feature_planner.domain.model.Event

sealed class DayViewEvent {
    data class DeleteEvent(val event : Event) : DayViewEvent()
}
