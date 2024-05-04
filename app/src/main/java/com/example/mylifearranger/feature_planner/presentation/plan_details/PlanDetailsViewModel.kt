package com.example.mylifearranger.feature_planner.presentation.plan_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylifearranger.feature_planner.domain.use_case.plan.PlanUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanDetailsViewModel @Inject constructor(
    private val planUseCases: PlanUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(PlanDetailsState())
    val state: State<PlanDetailsState> = _state

    init {
        savedStateHandle.get<Int>("planId")?.let { planId ->
            if (planId != -1) {
                viewModelScope.launch {
                    planUseCases.getPlanWithTasksUseCase(planId).also {
                        planWithTasks -> _state.value = state.value.copy(planWithTasks = planWithTasks)
                    }
                }
            }
        }
    }

    fun onAction(action: PlanDetailsAction) {
        when (action) {
            is PlanDetailsAction.UpdatePlanCompletedAmount -> {
                viewModelScope.launch {
                    planUseCases.updatePlanCompletedAmountUseCase(action.plan, action.completedAmount)
                    refreshPlanWithTasks()
                }
            }
        }
    }

    private fun refreshPlanWithTasks() {
        viewModelScope.launch {
            planUseCases.getPlanWithTasksUseCase(state.value.planWithTasks?.plan?.id!!).also {
                planWithTasks -> _state.value = state.value.copy(planWithTasks = planWithTasks)
            }
        }
    }
}