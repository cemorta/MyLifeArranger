package com.example.mylifearranger.feature_planner.presentation.day_view

import com.example.mylifearranger.feature_planner.domain.model.Event

sealed class DayViewAction {
    data class DeleteEvent(val event : Event) : DayViewAction()
}
