package com.example.mylifearranger.feature_planner.presentation.add_edit_task

import androidx.compose.ui.focus.FocusState

sealed class AddEditTaskAction {
    data class EnteredTitle(val value: String) : AddEditTaskAction()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditTaskAction()
//    data class ChangeColor(val value: Int) : AddEditTaskEvent()
//    data class EnteredStartDate(val value: LocalDate) : AddEditTaskEvent()
//    data class EnteredStartTime(val value: LocalTime) : AddEditTaskEvent()
//    data class EnteredEndDate(val value: LocalDate) : AddEditTaskEvent()
//    data class EnteredEndTime(val value: LocalTime) : AddEditTaskEvent()
    object SaveTask : AddEditTaskAction()
}