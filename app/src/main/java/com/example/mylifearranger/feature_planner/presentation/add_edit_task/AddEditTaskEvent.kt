package com.example.mylifearranger.feature_planner.presentation.add_edit_task

import androidx.compose.ui.focus.FocusState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

sealed class AddEditTaskEvent {
    data class EnteredTitle(val value: String) : AddEditTaskEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditTaskEvent()
//    data class ChangeColor(val value: Int) : AddEditTaskEvent()
//    data class EnteredStartDate(val value: LocalDate) : AddEditTaskEvent()
//    data class EnteredStartTime(val value: LocalTime) : AddEditTaskEvent()
//    data class EnteredEndDate(val value: LocalDate) : AddEditTaskEvent()
//    data class EnteredEndTime(val value: LocalTime) : AddEditTaskEvent()
    object SaveEvent : AddEditTaskEvent()
}