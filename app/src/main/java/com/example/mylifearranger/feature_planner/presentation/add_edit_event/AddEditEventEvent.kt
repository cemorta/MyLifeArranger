package com.example.mylifearranger.feature_planner.presentation.add_edit_event

import androidx.compose.ui.focus.FocusState
import java.time.LocalDateTime

sealed class AddEditEventEvent {
    data class EnteredTitle(val value: String) : AddEditEventEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditEventEvent()
    data class ChangeColor(val value: Int) : AddEditEventEvent()
    data class EnteredStartDate(val value: LocalDateTime) : AddEditEventEvent()
    data class EnteredEndDate(val value: LocalDateTime) : AddEditEventEvent()
    object SaveEvent : AddEditEventEvent()
}