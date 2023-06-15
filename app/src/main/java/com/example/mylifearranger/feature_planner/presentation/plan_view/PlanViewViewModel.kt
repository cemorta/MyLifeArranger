package com.example.mylifearranger.feature_planner.presentation.plan_view

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylifearranger.feature_planner.domain.use_case.plan.PlanUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class PlanViewViewModel @Inject constructor(
    private val planUseCase: PlanUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(PlanViewState())
    val state: State<PlanViewState> = _state

    private var getPlansJob: Job? = null

    init {
        getPlans()
    }

    private fun getPlans() {
        getPlansJob?.cancel()
        getPlansJob = planUseCase.getPlansUseCase().onEach { plans ->
            _state.value = state.value.copy(plans = plans)
        }.launchIn(viewModelScope)
    }
}