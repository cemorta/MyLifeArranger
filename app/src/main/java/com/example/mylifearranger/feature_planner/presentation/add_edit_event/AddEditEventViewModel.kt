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

    private val _eventStartDateTime = mutableStateOf<LocalDateTime>(LocalDateTime.now())
    val eventStartDateTime: State<LocalDateTime> = _eventStartDateTime

    // Create an eventEndDateTime with a default time value of eventStartTime + 1 hour
    private val _eventEndDateTime = mutableStateOf<LocalDateTime>(LocalDateTime.now().plusHours(1))
    val eventEndDateTime: State<LocalDateTime> = _eventEndDateTime

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
                        _eventStartDateTime.value = event.start
                        _eventEndDateTime.value = event.end
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

                val newStartDate = LocalDateTime.of(event.value, _eventStartDateTime.value.toLocalTime())
                _eventStartDateTime.value = newStartDate

                // If the end date is before the start date, set the end date to the start date
                if (_eventEndDateTime.value.isBefore(newStartDate)) {
                    val newEndDate = LocalDateTime.of(event.value, _eventEndDateTime.value.toLocalTime())
                    _eventEndDateTime.value = newEndDate
                }
            }

            is AddEditEventEvent.EnteredEndDate -> {

                val newEndDate = LocalDateTime.of(event.value, _eventEndDateTime.value.toLocalTime())
                _eventEndDateTime.value = newEndDate

                // If the start date is after the end date, set the start date to the end date
                if (_eventStartDateTime.value.isAfter(newEndDate)) {
                    val newStartDate = LocalDateTime.of(event.value, _eventStartDateTime.value.toLocalTime())
                    _eventStartDateTime.value = newStartDate
                }
            }

            is AddEditEventEvent.EnteredStartTime -> {

                val newStartTime = LocalDateTime.of(_eventStartDateTime.value.toLocalDate(), event.value)
                _eventStartDateTime.value = newStartTime

                // If the end time is before the start time, set the end time to the start time
                if (_eventEndDateTime.value.isBefore(newStartTime)) {
                    val newEndTime = LocalDateTime.of(_eventEndDateTime.value.toLocalDate(), event.value)
                    _eventEndDateTime.value = newEndTime
                }
            }

            is AddEditEventEvent.EnteredEndTime -> {

                val newEndTime = LocalDateTime.of(_eventEndDateTime.value.toLocalDate(), event.value)
                _eventEndDateTime.value = newEndTime

                // If the start time is after the end time, set the start time to the end time
                if (_eventStartDateTime.value.isAfter(newEndTime)) {
                    val newStartTime = LocalDateTime.of(_eventStartDateTime.value.toLocalDate(), event.value)
                    _eventStartDateTime.value = newStartTime
                }
            }

            is AddEditEventEvent.SaveEvent -> {
                viewModelScope.launch {
                    try {
                        eventUseCases.addEventUseCase(
                            Event(
                                title = eventTitle.value.text,
                                start = eventStartDateTime.value,
                                end = eventEndDateTime.value,
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