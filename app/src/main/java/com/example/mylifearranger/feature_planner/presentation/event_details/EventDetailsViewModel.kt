package com.example.mylifearranger.feature_planner.presentation.event_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylifearranger.feature_planner.domain.use_case.event.EventUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventDetailsViewModel @Inject constructor(
    private val eventUseCases: EventUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(EventDetailsState())
    val state: State<EventDetailsState> = _state

    init {
        savedStateHandle.get<Int>("eventId")?.let { eventId ->
            if (eventId != -1) {
                viewModelScope.launch {
                    eventUseCases.getEventUseCase(eventId).also { event ->
                        _state.value = state.value.copy(event = event)
                    }
                }
            }
        }
    }

    fun onEvent(event: EventDetailsAction) {
        when (event) {
            is EventDetailsAction.DeleteEvent -> {
                viewModelScope.launch {
                    eventUseCases.deleteEventUseCase(event.event)
                }
            }
        }
    }

//    private fun getEventById(eventId: Int) {
//        getEventByIdJob?.cancel()
//        getEventByIdJob = eventUseCases.getEventUseCase(eventId).onEach { event ->
//            _state.value = state.value.copy(event = event)
//        }.launchIn(viewModelScope)
//    }
}