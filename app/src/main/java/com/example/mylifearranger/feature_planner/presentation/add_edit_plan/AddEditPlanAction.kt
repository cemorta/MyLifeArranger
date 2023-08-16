package com.example.mylifearranger.feature_planner.presentation.add_edit_plan

import androidx.compose.ui.focus.FocusState
import com.example.mylifearranger.feature_planner.domain.util.PlanType
import java.time.LocalDate

sealed class AddEditPlanAction {
    data class EnteredTitle(val value: String) : AddEditPlanAction()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditPlanAction()
    data class EnteredStartDate(val value: LocalDate) : AddEditPlanAction()
    data class EnteredEndDate(val value: LocalDate) : AddEditPlanAction()
    data class EnteredTotalAmount(val value: Int) : AddEditPlanAction()
    data class EnteredUnit(val value: String) : AddEditPlanAction()
    data class EnteredStartRange(val value: Int) : AddEditPlanAction()
    data class EnteredEndRange(val value: Int) : AddEditPlanAction()
    data class SwitchDays(val value: Int) : AddEditPlanAction()
    data class ChangePlanType(val value: PlanType) : AddEditPlanAction()
    object SavePlan : AddEditPlanAction()
}