package com.example.mylifearranger.feature_planner.presentation.add_edit_plan

import androidx.compose.ui.focus.FocusState
import com.example.mylifearranger.feature_planner.domain.util.PlanType
import java.time.LocalDate

sealed class AddEditPlanEvent {
    data class EnteredTitle(val value: String) : AddEditPlanEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditPlanEvent()
    data class EnteredStartDate(val value: LocalDate) : AddEditPlanEvent()
    data class EnteredEndDate(val value: LocalDate) : AddEditPlanEvent()
    data class EnteredTotalAmount(val value: Int) : AddEditPlanEvent()
    data class EnteredUnit(val value: String) : AddEditPlanEvent()
    data class EnteredStartRange(val value: Int) : AddEditPlanEvent()
    data class EnteredEndRange(val value: Int) : AddEditPlanEvent()
    data class SwitchDays(val value: Int) : AddEditPlanEvent()
    data class ChangePlanType(val value: PlanType) : AddEditPlanEvent()
    object SaveEvent : AddEditPlanEvent()
}