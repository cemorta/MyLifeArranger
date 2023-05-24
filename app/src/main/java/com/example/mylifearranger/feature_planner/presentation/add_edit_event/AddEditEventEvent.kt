package com.example.mylifearranger.feature_planner.presentation.add_edit_event

import androidx.compose.ui.focus.FocusState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

sealed class AddEditEventEvent {
    data class EnteredTitle(val value: String) : AddEditEventEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditEventEvent()
    data class ChangeColor(val value: Int) : AddEditEventEvent()
    data class EnteredStartDate(val value: LocalDate) : AddEditEventEvent()
    data class EnteredStartTime(val value: LocalTime) : AddEditEventEvent()
    data class EnteredEndDate(val value: LocalDate) : AddEditEventEvent()
    data class EnteredEndTime(val value: LocalTime) : AddEditEventEvent()
    object SaveEvent : AddEditEventEvent()
}