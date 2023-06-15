package com.example.mylifearranger.feature_planner.presentation.plan_overview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.mylifearranger.feature_planner.domain.use_case.plan.PlanUseCases
import com.example.mylifearranger.feature_planner.presentation.add_edit_plan.SharedViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlanOverviewViewModel @Inject constructor(
    private val planUseCases: PlanUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private lateinit var sharedViewModel: SharedViewModel

    fun setViewModel(sharedViewModel: SharedViewModel) {
        this.sharedViewModel = sharedViewModel

        println("d " + sharedViewModel.getSharedState())
    }

//    private val _planTitle = mutableStateOf(
//        PlanTextFieldState(
//            hint = "Enter title"
//        )
//    )
//    val planTitle: State<PlanTextFieldState> = _planTitle
//
//    private val _planType = mutableStateOf(PlanType.TOTAL)
//    val planType: State<PlanType> = _planType
//
//    private val _totalAmount = mutableStateOf<Int?>(null)
//    val totalAmount: State<Int?> = _totalAmount
//
//    private val _unit = mutableStateOf(
//        PlanTextFieldState(
//            hint = "Enter unit"
//        )
//    )
//    val unit: State<PlanTextFieldState> = _unit
//
//    private val _startRange = mutableStateOf<Int?>(null)
//    val startRange: State<Int?> = _startRange
//
//    private val _endRange = mutableStateOf<Int?>(null)
//    val endRange: State<Int?> = _endRange
//
//    private val _days = mutableStateOf<Int?>(null)
//    val days: State<Int?> = _days
//
//    private val _startDateTimestamp = mutableStateOf(LocalDateTime.now().toTimestamp())
//    val startDateTimestamp: State<Long> = _startDateTimestamp
//
//    private val _endDateTimestamp = mutableStateOf(LocalDateTime.now().plusDays(1).toTimestamp())
//    val endDateTimestamp: State<Long> = _endDateTimestamp
//
//    private val _eventFlow = MutableSharedFlow<UiEvent>()
//    val eventFlow = _eventFlow.asSharedFlow()
//
//    private var currentPlanId: Long? = null
//
//    init {
//        savedStateHandle.get<Int>("planId")?.let { planId ->
//            if (planId != -1) {
//                viewModelScope.launch {
//                    planUseCases.getPlanUseCase(planId)?.also { plan ->
//                        currentPlanId = plan.id
//                        _planTitle.value = planTitle.value.copy(
//                            text = plan.title,
//                            isHintVisible = false
//                        )
//                        _planType.value = plan.planType
//                        _days.value = plan.days
//                        _totalAmount.value = plan.totalAmount
//                        _unit.value = unit.value.copy(
//                            text = plan.unit,
//                            isHintVisible = false
//                        )
//                        _startRange.value = plan.startRange
//                        _endRange.value = plan.endRange
//                        _startDateTimestamp.value = plan.startDateTimestamp
//                        _endDateTimestamp.value = plan.endDateTimestamp
//                    }
//                }
//            }
//        }
//    }
//
//    fun onEvent(event: AddEditPlanEvent) {
//        when (event) {
//            is AddEditPlanEvent.EnteredTitle -> {
//                _planTitle.value = planTitle.value.copy(
//                    text = event.value
//                )
//            }
//
//            is AddEditPlanEvent.ChangeTitleFocus -> {
//                _planTitle.value = planTitle.value.copy(
//                    isHintVisible = !event.focusState.isFocused &&
//                            planTitle.value.text.isBlank()
//                )
//            }
//
//            is AddEditPlanEvent.EnteredStartDate -> {
//
//                _startDateTimestamp.value = event.value.toEpochDay()
//
//                // If the end date is before the start date, set the end date to the start date
//                if (_endDateTimestamp.value < _startDateTimestamp.value) {
//                    _endDateTimestamp.value = _startDateTimestamp.value
//                }
//            }
//
//            is AddEditPlanEvent.EnteredEndDate -> {
//
//                _endDateTimestamp.value = event.value.toEpochDay()
//
//                // If the start date is after the end date, set the start date to the end date
//                if (_startDateTimestamp.value > _endDateTimestamp.value) {
//                    _startDateTimestamp.value = _endDateTimestamp.value
//                }
//            }
//
//            is AddEditPlanEvent.SaveEvent -> {
//                viewModelScope.launch {
//                    try {
//                        // Create the plan object and save it to the shared view model
//                        val plan = days.value?.let {
//                            Plan(
//                                title = planTitle.value.text,
//                                planType = planType.value,
//                                days = it,
//                                totalAmount = totalAmount.value,
//                                unit = unit.value.text,
//                                startRange = startRange.value,
//                                endRange = endRange.value,
//                                startDateTimestamp = startDateTimestamp.value,
//                                endDateTimestamp = endDateTimestamp.value
//                            )
//                        }
//                        sharedViewModel.setSharedState(plan!!)
//                        _eventFlow.emit(UiEvent.SaveEvent)
//                    } catch (e: Exception) {
//                        _eventFlow.emit(
//                            UiEvent.ShowSnackbar(
//                                message = e.message ?: "Couldn't save event"
//                            )
//                        )
//                    }
//                }
//            }
//        }
//    }
//
//    sealed class UiEvent {
//        data class ShowSnackbar(val message: String) : UiEvent()
//        object SaveEvent : UiEvent()
//    }
}