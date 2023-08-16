package com.example.mylifearranger.feature_planner.presentation.add_edit_event

import androidx.compose.ui.focus.FocusState
import java.time.LocalDate
import java.time.LocalTime

sealed class AddEditEventAction {
    data class EnteredTitle(val value: String) : AddEditEventAction()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditEventAction()
    data class ChangeColor(val value: Int) : AddEditEventAction()
    data class EnteredStartDate(val value: LocalDate) : AddEditEventAction()
    data class EnteredStartTime(val value: LocalTime) : AddEditEventAction()
    data class EnteredEndDate(val value: LocalDate) : AddEditEventAction()
    data class EnteredEndTime(val value: LocalTime) : AddEditEventAction()
    object SaveEvent : AddEditEventAction()
}