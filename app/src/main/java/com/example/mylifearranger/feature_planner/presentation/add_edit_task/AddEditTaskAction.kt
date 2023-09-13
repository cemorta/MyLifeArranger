package com.example.mylifearranger.feature_planner.presentation.add_edit_task

import androidx.compose.ui.focus.FocusState
import com.example.mylifearranger.feature_planner.domain.util.TaskType
import java.time.LocalDate
import java.time.LocalTime

sealed class AddEditTaskAction {
    data class EnteredTitle(val value: String) : AddEditTaskAction()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditTaskAction()
    data class EnteredDurationHour(val value: String) : AddEditTaskAction()
    data class EnteredDurationMinute(val value: String) : AddEditTaskAction()
    data class ChangeDurationHourFocus(val focusState: FocusState) : AddEditTaskAction()
    data class ChangeDurationMinuteFocus(val focusState: FocusState) : AddEditTaskAction()
    data class EnteredTaskType(val value: TaskType) : AddEditTaskAction()
    data class EnteredPlannedDate(val value: LocalDate) : AddEditTaskAction()
    data class EnteredPlannedTime(val value: LocalTime) : AddEditTaskAction()
    data class EnteredDueDate(val value: LocalDate) : AddEditTaskAction()
    data class EnteredDueTime(val value: LocalTime) : AddEditTaskAction()

    object SaveTask : AddEditTaskAction()
}