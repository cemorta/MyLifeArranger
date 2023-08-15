package com.example.mylifearranger.feature_planner.presentation.day_view

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylifearranger.feature_planner.domain.use_case.event.EventUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DayViewViewModel @Inject constructor(
    private val eventUseCases: EventUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(DayViewState())
    val state: State<DayViewState> = _state

    private var getEventsForDateJob: Job? = null
    private var getEventsJob: Job? = null
    private var date: String? = null

    init {
        // Get selected date from arguments and get events for this date
        savedStateHandle.get<String>("date")?.let {
            date = it
            println("DayViewViewModel: init: it = $it")
            getEventsForDate(it)
        }
    }

    fun onEvent(event: DayViewEvent) {
        when (event) {
            is DayViewEvent.DeleteEvent -> {
                viewModelScope.launch {
                    eventUseCases.deleteEventUseCase(event.event)
                }
            }
        }
    }

    private fun getEventsForDate(date: String) {
        println("DayViewViewModel: getEventsForDate: date = $date")
        getEventsForDateJob?.cancel()
        getEventsForDateJob = eventUseCases.getEventsForDateUseCase(date).onEach { events ->
            _state.value = state.value.copy(events = events)
        }.launchIn(viewModelScope)
    }

    private fun getEvents() {
        getEventsJob?.cancel()
        getEventsJob = eventUseCases.getEventsUseCase().onEach { events ->
            _state.value = state.value.copy(events = events)
        }.launchIn(viewModelScope)
    }

    fun onScreenDisplayed(newDate: String) {
        if (date != newDate) {
            date = newDate
            getEventsForDate(newDate)
        }
    }
}