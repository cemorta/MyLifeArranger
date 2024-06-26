package com.example.mylifearranger.feature_planner.presentation.add_edit_plan

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylifearranger.feature_planner.domain.model.Plan
import com.example.mylifearranger.feature_planner.domain.use_case.plan.PlanUseCases
import com.example.mylifearranger.feature_planner.domain.util.PlanType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import toTimestamp
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class AddEditPlanViewModel @Inject constructor(
    private val planUseCases: PlanUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private lateinit var sharedViewModel: SharedViewModel

    fun setViewModel(sharedViewModel: SharedViewModel) {
        this.sharedViewModel = sharedViewModel
    }

    private val _planTitle = mutableStateOf(
        PlanTextFieldState(
            hint = "Enter title"
        )
    )
    val planTitle: State<PlanTextFieldState> = _planTitle

    private val _planType = mutableStateOf(PlanType.TOTAL)
    val planType: State<PlanType> = _planType

    private val _totalAmount = mutableStateOf<String?>(null)
    val totalAmount: State<String?> = _totalAmount

    private val _unit = mutableStateOf(
        PlanTextFieldState(
            hint = "Unit"
        )
    )
    val unit: State<PlanTextFieldState> = _unit

    private val _startRange = mutableStateOf(
        PlanTextFieldState(
            hint = "Start range"
        )
    )
    val startRange: State<PlanTextFieldState> = _startRange

    private val _endRange = mutableStateOf(
        PlanTextFieldState(
            hint = "End range"
        )
    )
    val endRange: State<PlanTextFieldState> = _endRange

    private val _days = mutableStateOf(0)
    val days: State<Int> = _days

    private val _startDateTimestamp = mutableStateOf(LocalDateTime.now().toTimestamp())
    val startDateTimestamp: State<Long> = _startDateTimestamp

    private val _endDateTimestamp = mutableStateOf(LocalDateTime.now().plusDays(1).toTimestamp())
    val endDateTimestamp: State<Long> = _endDateTimestamp

    private val _eventFlow = MutableSharedFlow<UiAction>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentPlanId: Int? = null

    init {
        // print getSharedViewModel
//        println("getSharedViewModel: ${sharedViewModel.getSharedState()}")
        savedStateHandle.get<Int>("planId")?.let { planId ->
            if (planId != -1) {
                viewModelScope.launch {
                    planUseCases.getPlanUseCase(planId)?.also { plan ->
                        currentPlanId = plan.id
                        _planTitle.value = planTitle.value.copy(
                            text = plan.title,
                            isHintVisible = false
                        )
                        _planType.value = plan.planType
                        _days.value = plan.days
                        _totalAmount.value = plan.totalAmount.toString()
                        _unit.value = unit.value.copy(
                            text = plan.unit,
                            isHintVisible = false
                        )
                        _startRange.value =
                            startRange.value.copy(
                                text = plan.startRange.toString(),
                                isHintVisible = false
                            )
                        _endRange.value =
                            endRange.value.copy(
                                text = plan.endRange.toString(),
                                isHintVisible = false
                            )
                        _startDateTimestamp.value = plan.startDateTimestamp
                        _endDateTimestamp.value = plan.endDateTimestamp
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditPlanAction) {
        when (event) {
            is AddEditPlanAction.EnteredTitle -> {
                _planTitle.value = planTitle.value.copy(
                    text = event.value
                )
            }

            is AddEditPlanAction.ChangeTitleFocus -> {
                _planTitle.value = planTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            planTitle.value.text.isBlank()
                )
            }

            is AddEditPlanAction.EnteredTotalAmount -> {
                _totalAmount.value = event.value
            }

            is AddEditPlanAction.EnteredStartDate -> {

                _startDateTimestamp.value =
                    LocalDateTime.of(event.value, LocalTime.MIDNIGHT).toTimestamp()

//                // If the end date is before the start date, set the end date to the start date
//                if (_endDateTimestamp.value < _startDateTimestamp.value) {
//                    _endDateTimestamp.value = _startDateTimestamp.value
//                }
            }

            is AddEditPlanAction.EnteredEndDate -> {

                _endDateTimestamp.value =
                    LocalDateTime.of(event.value, LocalTime.MIDNIGHT).toTimestamp()

//                // If the start date is after the end date, set the start date to the end date
//                if (_startDateTimestamp.value > _endDateTimestamp.value) {
//                    _startDateTimestamp.value = _endDateTimestamp.value
//                }
            }

            is AddEditPlanAction.SavePlan -> {
                viewModelScope.launch {
                    try {
                        // Create the plan object and save it to the shared view model
                        val plan = days.value?.let {
                            Plan(
                                title = planTitle.value.text,
                                planType = planType.value,
                                totalAmount = totalAmount.value?.toIntOrNull(),
                                unit = unit.value.text,
                                startRange = startRange.value.text.toIntOrNull(),
                                endRange = endRange.value.text.toIntOrNull(),
                                days = it,
                                startDateTimestamp = startDateTimestamp.value,
                                endDateTimestamp = endDateTimestamp.value,
                                assignedGoalId = null,
                            )
                        }
                        sharedViewModel.setSharedState(plan!!)
                        println("PlanViewViewModel: init: sharedViewModel.plan.value = ${sharedViewModel.getSharedState()}")
                        sharedViewModel.getSharedState()?.let {
                            println("getSharedViewModel: $it")
                        }
                        _eventFlow.emit(UiAction.SavePlan)
                    } catch (e: Exception) {
                        _eventFlow.emit(
                            UiAction.ShowSnackbar(
                                message = e.message ?: "Couldn't save event"
                            )
                        )
                    }
                }
            }

            is AddEditPlanAction.SwitchDays -> {
                if (days.value and event.value == event.value) {
                    _days.value = days.value and event.value.inv()
                } else {
                    _days.value = days.value or event.value
                }
            }

            is AddEditPlanAction.EnteredEndRange -> {
                _endRange.value = endRange.value.copy(
                    text = event.value
                )
            }

            is AddEditPlanAction.ChangePlanType -> {
                _planType.value = event.value
            }

            is AddEditPlanAction.EnteredStartRange -> {
                _startRange.value = startRange.value.copy(
                    text = event.value
                )
            }

            is AddEditPlanAction.EnteredUnit -> {
                _unit.value = unit.value.copy(
                    text = event.value
                )
            }

            is AddEditPlanAction.ChangeUnitFocus -> {
                _unit.value = unit.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            unit.value.text.isBlank()
                )
            }

            is AddEditPlanAction.ChangeEndRangeFocus -> {
                _endRange.value = endRange.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            endRange.value.text.isBlank()
                )
            }
            is AddEditPlanAction.ChangeStartRangeFocus -> {
                _startRange.value = startRange.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            startRange.value.text.isBlank()
                )
            }
        }
    }

    sealed class UiAction {
        data class ShowSnackbar(val message: String) : UiAction()
        object SavePlan : UiAction()
    }
}