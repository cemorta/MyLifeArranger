package com.example.mylifearranger.feature_planner.presentation.events

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylifearranger.feature_planner.domain.use_case.EventUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val eventUseCases: EventUseCases
) : ViewModel() {

    private val _state = mutableStateOf(EventsState())
    private val state: State<EventsState> = _state

    private var getEventsJob: Job? = null

    fun onEvent(event: EventsEvent) {
        when (event) {
            is EventsEvent.DeleteEvent -> {
                viewModelScope.launch {
                    eventUseCases.deleteEventUseCase(event.event)
                }
            }
        }
    }

    private fun getEvents() {
        getEventsJob?.cancel()
        getEventsJob = eventUseCases.getEventsUseCase().onEach { events ->
            _state.value = state.value.copy(events = events)
        }.launchIn(viewModelScope)
    }
}