package com.example.mylifearranger.feature_planner.presentation.add_edit_event

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylifearranger.feature_planner.domain.model.Event
import com.example.mylifearranger.feature_planner.domain.use_case.EventUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddEditEventViewModel @Inject constructor(
    private val eventUseCases: EventUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _eventTitle = mutableStateOf(
        EventTextFieldState(
            hint = "Enter title"
        )
    )
    val eventTitle: State<EventTextFieldState> = _eventTitle

    private val _eventColor = mutableStateOf<Int>(Event.eventColors.random().toArgb())
    val eventColor: State<Int> = _eventColor

    private val _eventStartDate = mutableStateOf<LocalDateTime>(LocalDateTime.now())
    val eventStartDate: State<LocalDateTime> = _eventStartDate

    // Create an eventEndDate with a default value of eventStartDate + 1 hour
    private val _eventEndDate = mutableStateOf<LocalDateTime>(LocalDateTime.now().plusHours(1))
    val eventEndDate: State<LocalDateTime> = _eventEndDate

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentEventId: Int? = null

    init {
        savedStateHandle.get<Int>("eventId")?.let { eventId ->
            if (eventId != -1) {
                viewModelScope.launch {
                    eventUseCases.getEventUseCase(eventId)?.also { event ->
                        currentEventId = event.id
                        _eventTitle.value = eventTitle.value.copy(
                            text = event.title,
                            isHintVisible = false
                        )
                        _eventColor.value = event.color
                        _eventStartDate.value = event.start
                        _eventEndDate.value = event.end
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditEventEvent) {
        when (event) {
            is AddEditEventEvent.EnteredTitle -> {
                _eventTitle.value = eventTitle.value.copy(
                    text = event.value
                )
            }

            is AddEditEventEvent.ChangeTitleFocus -> {
                _eventTitle.value = eventTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused &&
                            eventTitle.value.text.isBlank()
                )
            }

            is AddEditEventEvent.ChangeColor -> {
                _eventColor.value = event.value
            }

            is AddEditEventEvent.EnteredStartDate -> {
                _eventStartDate.value = event.value
            }

            is AddEditEventEvent.EnteredEndDate -> {
                _eventEndDate.value = event.value
            }

            is AddEditEventEvent.SaveEvent -> {
                viewModelScope.launch {
                    try {
                        eventUseCases.addEventUseCase(
                            Event(
                                title = eventTitle.value.text,
                                start = eventStartDate.value,
                                end = eventEndDate.value,
                                color = eventColor.value,
                                isDone = false,
                                isAllDay = false,
                                id = currentEventId,
                            )
                        )
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