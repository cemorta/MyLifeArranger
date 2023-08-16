package com.example.mylifearranger.feature_planner.presentation.plan_overview

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylifearranger.feature_planner.domain.model.Plan
import com.example.mylifearranger.feature_planner.domain.use_case.plan.PlanUseCases
import com.example.mylifearranger.feature_planner.presentation.add_edit_plan.SharedViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
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

    fun getViewModel(): Plan {
        return sharedViewModel.getSharedState()!!
    }

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

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
    fun onEvent(event: PlanOverviewAction) {
        when (event) {
            is PlanOverviewAction.SavePlan -> {
                viewModelScope.launch {
                    try {
                        planUseCases.addPlanUseCase(
                            sharedViewModel.getSharedState()!!
                        )
                        sharedViewModel.clearSharedState()
                        _eventFlow.emit(UiEvent.SaveEvent)
                    } catch (e: Exception) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Couldn't save event"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String) : UiEvent()
        object SaveEvent : UiEvent()
    }
}