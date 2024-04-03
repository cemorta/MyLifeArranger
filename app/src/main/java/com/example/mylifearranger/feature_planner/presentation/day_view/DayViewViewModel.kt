package com.example.mylifearranger.feature_planner.presentation.day_view

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylifearranger.feature_planner.domain.use_case.event.EventUseCases
import com.example.mylifearranger.feature_planner.domain.use_case.plan.PlanUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import toTimestamp
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class DayViewViewModel @Inject constructor(
    private val eventUseCases: EventUseCases,
    private val planUseCases: PlanUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(DayViewState())
    val state: State<DayViewState> = _state

    private var getEventsForDateJob: Job? = null
    private var getEventsJob: Job? = null
    private var getPlanTasksForBetweenTwoDatesJob: Job? = null
    private var date: String? = null

    init {
        // Get selected date from arguments and get events for this date
        savedStateHandle.get<String>("date")?.let {
            date = it
            println("DayViewViewModel: init: it = $it")
            getEventsForDate(it)
            // Get all PlanTasks for the current month
            getPlanTasksForMonth(it)
        }
    }

    fun onEvent(event: DayViewAction) {
        when (event) {
            is DayViewAction.DeleteEvent -> {
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

    private fun getPlanTasksForMonth(date: String) {
        // Find the first and last day of the month for the selected date (YYYY-MM-DD)
        val dateStart = date.substring(0, 8) + "01" + "T00:00:00"
        // Be careful with the last day of the month
        if (date.substring(5, 7) == "02") {
            val year = date.substring(0, 4).toInt()
            val lastDay = if (year % 4 == 0) 29 else 28
            val month = date.substring(5, 7).toInt()
            val dateEnd = date.substring(0, 8) + lastDay.toString() + "T23:59:59"
            // Parse the dateStart and dateEnd to Epoch time

            getPlanTasksForBetweenTwoDatesJob?.cancel()
            getPlanTasksForBetweenTwoDatesJob = planUseCases.getPlanTasksBetweenTwoDatesUseCase(
                LocalDateTime.parse(dateStart).toTimestamp(),
                LocalDateTime.parse(dateEnd).toTimestamp()
            ).onEach { planTasks ->
                _state.value = state.value.copy(planTasks = planTasks)
            }.launchIn(viewModelScope)
        } else {
            val month = date.substring(5, 7).toInt()
            val lastDay = if (month == 4 || month == 6 || month == 9 || month == 11) 30 else 31
            val dateEnd = date.substring(0, 8) + lastDay.toString() + "T23:59:59"
            getPlanTasksForBetweenTwoDatesJob?.cancel()
            getPlanTasksForBetweenTwoDatesJob =
                planUseCases.getPlanTasksBetweenTwoDatesUseCase(
                    LocalDateTime.parse(dateStart).toTimestamp(),
                    LocalDateTime.parse(dateEnd).toTimestamp()
                ).onEach { planTasks ->
                    _state.value = state.value.copy(planTasks = planTasks)
                }.launchIn(viewModelScope)
        }
    }

    fun onScreenDisplayed(newDate: String) {
        if (date != newDate) {
            date = newDate
            getEventsForDate(newDate)
        }
    }
}